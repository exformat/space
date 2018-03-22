package io.exformat.space.spase;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.calculate.CalculateCoordinate;
import io.exformat.space.calculate.CalculateDirect;
import io.exformat.space.framework.Game;
import io.exformat.space.framework.Input;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
import io.exformat.space.framework.openGL.FPSCounter;
import io.exformat.space.model.FlyObject;
import io.exformat.space.model.FuelCountModel;
import io.exformat.space.model.MassObject;
import io.exformat.space.model.Models;
import io.exformat.space.model.Textures;
import io.exformat.space.spase.settings.SettingsModels;


public class SpaceOpenGL extends Screen {

    private GLGraphics glGraphics;
    private FPSCounter fps = new FPSCounter();

    private FuelCountModel fuelCountModel = new FuelCountModel();

    private double STEP = 0.01;

    private int touchDownX;
    private int touchDownY;

    private int touchDraggedX;
    private int touchDraggedY;

    private boolean trust = false;
    private boolean fuelOut = false;
    private boolean fuelOutSignal = false;
    private boolean crash = false;

    private int finishDate = 0;
    private boolean finish = false;

    private float angle;


    private FlyObject flyObject = new FlyObject();

    private CalculateCoordinate calculateCoordinate = new CalculateCoordinate();
    private CalculateDirect calculateDirect = new CalculateDirect();

    //сразу инициализируем массив при старте этого класса
    //инициализация идет через вложенный класс
    private ArrayList<MassObject> massObjects = Levels.getMassObjects();

    public SpaceOpenGL(Game game) {

        super(game);
        glGraphics = ((GLGame) game).getGLGraphics();
        flyObject.setX(150);
        flyObject.setY(150);
    }

    @Override
    public void update(float deltaTime) {


        fuelOut = flyObject.getFuelMass() > 0.01;

        //fuelOutSignal = flyObject.getFuelMass() <= 10;

        if (fuelOut) {

            if (trust) {
                fuelCountModel.reloadModel(fuelCountModel.getHeightFuelCountModel() - (flyObject.getFuelOut() * (7.55f * SettingsModels.scaleX) / 2));
            }
        } else {
            trust = false;

        }


        isFinish();
        isCrash();
        control();

        if (finishDate >= 500){

            game.setScreen(new LevelClearScreen(game));
        }
    }

    @Override
    public void present(float deltaTime) {

        fps.logFrame();

        GL10 gl = glGraphics.getGL();

        if (!crash) {
            gl.glClearColor(0, 0, 0, 0);
        } else {
            gl.glClearColor(1, 0, 0, 0);
        }

        if (finish){
            gl.glClearColor(0, 0, 1, 0);
        }

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, Assets.displayWidth, 0, Assets.displayHeight, 1, -1);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        //=================================================
        Textures.finishTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(Levels.getFinishX(), Levels.getFinishY(), 0);
        gl.glScalef(SettingsModels.scaleX, SettingsModels.scaleY, 0);
        Models.finishModel.draw(GL10.GL_TRIANGLES, 0, 6);

        //==================================================
        Textures.starTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.displayWidth_05, SettingsModels.displayHeight_05, 0);
        gl.glScalef(SettingsModels.scaleX, SettingsModels.scaleY, 0);
        Models.starVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //==================================================
        Textures.fuelCountTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatex(SettingsModels.fuelCountModelHeightMin, SettingsModels.fuelCountModelHeightTHIS, 0);
        gl.glScalef(SettingsModels.scaleX, SettingsModels.scaleY, 0);
        Models.fuelCountVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //==================================================
        Textures.fuelBagTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.fuelBagTranslateX, SettingsModels.fuelBagTranslateY, 0);
        gl.glScalef(SettingsModels.scaleX, SettingsModels.scaleY, 0);
        Models.fuelBagVertices.draw(GL10.GL_TRIANGLES, 0, 6);

            /*
            //==================================================
            if (!fuelOutSignal) {

                Textures.fuelOutSignalTexture.bind();
            }else {

                Textures.fuelOutSignal2Texture.bind();
            }
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleY,0);
            Models.fueloutSignalVertices.draw(GL10.GL_TRIANGLES, 0 , 6);
            */

        //==================================================
        if (trust) {

            Textures.rocketTrustTexture.bind();

        } else {

            Textures.rocketTexture.bind();
        }

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef((float) flyObject.getX(), (float) flyObject.getY(), 0);
        gl.glRotatef(angle, 0, 0, 1);
        gl.glScalef(SettingsModels.scaleX, SettingsModels.scaleY, 0);
        Models.rocketVertices.draw(GL10.GL_TRIANGLES, 0, 6);


        //==================================================
        update(deltaTime);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private void control() {

        calculateCoordinate.calculate(massObjects, flyObject, STEP);

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {

            Input.TouchEvent event = touchEvents.get(i);

            //первое касание записываем координаты
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {

                touchDownX = event.x;
                touchDownY = event.y;
            }

            //передвижение считаем
            //записываем координаты
            //считаем угол от первого касания
            if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {

                touchDraggedX = event.x;
                touchDraggedY = event.y;

                angle = calculateDirect.getAngle(touchDownX, touchDownY,
                        touchDraggedX, touchDraggedY);

                //высчитываем новое направление
                calculateDirect.calculateDirection(flyObject, 1, angle, 0);

                //рисуем огонёк у ракеты
                if (flyObject.getFuelMass() > 0) {
                    trust = true;
                }
            }

            if (event.type == Input.TouchEvent.TOUCH_UP) {

                //ракета без огонька
                trust = false;
            }
        }
    }

    private void isCrash() {

        for (MassObject massObject : massObjects) {

            double distance = calculateCoordinate.calculateRadius(flyObject, massObject);

            distance = distance - massObject.getRadius() - flyObject.getRadius();

            if (distance < 0) {

                crash = true;
                break;
            } else {
                crash = false;
            }
        }
    }

    private void isFinish() {

        double radius = Math.sqrt(Math.pow(flyObject.getX() - Levels.getFinishX(), 2) +
                                  Math.pow(flyObject.getY() - Levels.getFinishY(), 2));

        if (radius <= 100) {

            finishDate++;
            finish = true;
        }
        else {

            finishDate = 0;
            finish = false;
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {

        if (event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1) {
            return true;
        } else {
            return false;
        }
    }


}

