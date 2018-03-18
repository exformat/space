package io.exformat.space.spase;

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
import io.exformat.space.model.MassObject;
import io.exformat.space.model.Models;
import io.exformat.space.model.Textures;


public class SpaceOpenGL extends Screen{

        private GLGraphics glGraphics;
        private FPSCounter fps = new FPSCounter();

        private double STEP = 0.01;

        private int touchDownX;
        private int touchDownY;

        private int touchDraggedX;
        private int touchDraggedY;

        private boolean trust = false;
        private boolean crash = false;

        private float angle;

        private float scale = 0.1f;

        FlyObject flyObject = new FlyObject();

        CalculateCoordinate calculateCoordinate = new CalculateCoordinate();
        CalculateDirect calculateDirect = new CalculateDirect();

        //сразу инициализируем массив при старте этого класса
        //инициализация идет через вложенный класс
        ArrayList<MassObject> massObjects = new AddMassObject().getMassObjects();

        public SpaceOpenGL(Game game) {

            super(game);
            glGraphics = ((GLGame)game).getGLGraphics();
        }

        @Override
        public void update(float deltaTime) {


            if (trust){
                scale += 0.1f;
            }
            isCrash();
            control();
        }

        @Override
        public void present(float deltaTime) {

            fps.logFrame();

            GL10 gl = glGraphics.getGL();

            if (!crash) {
                gl.glClearColor(0, 0, 0, 0);
            }
            else {
                gl.glClearColor(1, 0, 0, 0);

            }
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glOrthof(0, 1920, 0, 1080, 1, -1);
            gl.glEnable(GL10.GL_TEXTURE_2D);
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);


            Textures.starTexture.bind();
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            Models.starVertices.draw(GL10.GL_TRIANGLES, 0, 6);

            Textures.fuelCountTexture.bind();
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();

            gl.glScalef(2 ,scale,0);

            Models.fuelCountVertices.draw(GL10.GL_TRIANGLES, 0 , 6);

            if(!trust){

                Textures.rocketTexture.bind();

            }else {

                Textures.rocketTrustTexture.bind();
            }

            gl.glMatrixMode(GL10.GL_MODELVIEW);

            gl.glLoadIdentity();

            gl.glTranslatef((float) flyObject.getX(), (float) flyObject.getY(), 0);

            gl.glRotatef(angle, 0, 0, 1);
            Models.rocketVertices.draw(GL10.GL_TRIANGLES, 0, 6);

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

        private void control(){

            calculateCoordinate.calculate(massObjects,flyObject,STEP);

            List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

            int len = touchEvents.size();

            for (int i = 0; i < len; i++) {

                Input.TouchEvent event = touchEvents.get(i);

                //первое касание записываем координаты
                if (event.type == Input.TouchEvent.TOUCH_DOWN){

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

                if (event.type == Input.TouchEvent.TOUCH_UP){

                    //ракета без огонька
                    trust = false;
                }
            }
        }

        private void isCrash(){

            for (MassObject massObject: massObjects){

                double distance = calculateCoordinate.calculateRadius(flyObject,massObject);

                distance = distance - massObject.getRadius() - flyObject.getRadius();

                if (distance < 0){

                    crash = true;
                    break;
                }
                else {
                    crash = false;
                }
            }
        }

        private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height){

        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1){
            return true;
        }
        else {
            return false;
        }
    }


    private class AddMassObject{

            ArrayList<MassObject> massObjects = new ArrayList<>();

            public ArrayList<MassObject> getMassObjects(){

                massObjects.add(new MassObject(Assets.displayWidth / 2,
                                               Assets.displayHeight / 2,
                                               0,
                                               200000,
                                               54));

                return this.massObjects;
            }
        }
    }

