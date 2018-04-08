package io.exformat.space.spase;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.calculate.CalculateCoordinate;
import io.exformat.space.calculate.CalculateDirect;
import io.exformat.space.framework.Input;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
import io.exformat.space.framework.openGL.FPSCounter;
import io.exformat.space.model.FlyObject;
import io.exformat.space.model.MassObject;
import io.exformat.space.model.Models;
import io.exformat.space.model.StarCoin;
import io.exformat.space.model.Textures;
import io.exformat.space.model.Vector3;
import io.exformat.space.model.models.modelsFHD.gameModels.BombModels;
import io.exformat.space.spase.settings.SettingsModels;


public class GameScreen extends Screen {

    private GLGraphics glGraphics;
    private FPSCounter fps = new FPSCounter();

    private LoadingModelsAndTextures reloadTextures = new LoadingModelsAndTextures();

    private double STEP = 0.01;

    private int touchDownX;
    private int touchDownY;

    private int touchDraggedX;
    private int touchDraggedY;

    private boolean trust = false;
    private boolean fuelOut = false;

    private boolean crash = false;
    private boolean finish = false;

    private int tick = 0;

    private int finishDate = 0;

    private float angle;
    private float angleRotate = 0;

    private float angleFinish = 0;

    private int starCoinsUp = 0;

    private boolean drawExplosive = false;
    private int explosiveTick = 0;




    private CalculateCoordinate calculateCoordinate = new CalculateCoordinate();
    private CalculateDirect calculateDirect = new CalculateDirect();

    //инициализируем ракету, массивные объекты, звёзды и бомбы
    private FlyObject               flyObject = Levels.level.getFlyObject();
    private ArrayList<MassObject> massObjects = Levels.level.getMassObjects();
    private ArrayList<StarCoin>     starCoins = Levels.level.getStarCoins();
    private ArrayList<FlyObject>        bombs = Levels.level.getBombs();
    private ArrayList<Vector3> bombFragments = new ArrayList<>();


    public GameScreen(io.exformat.space.framework.Game game) {

        super(game);
        glGraphics = ((GLGame) game).getGLGraphics();
    }

    @Override
    public void update(float deltaTime) {

        angleRotate += flyObject.getAngleSpeedXY() * STEP;

        //rotate finish image
        angleFinish += -0.1f;

        fuelOut = flyObject.getFuelMass() > 0.01;

        //перерисовываем количество топлива
        if (fuelOut) {

            if (trust) {
                //высчитываем новое направление
                calculateDirect.calculateDirection(flyObject, 0, angle, 0);

                flyObject.setAngleSpeedXY(0);
                angleRotate = angle;

                SettingsModels.fuelCountTranslateX -= (flyObject.getFuelOut() * (7.15f));
            }
        } else {
            trust = false;
            tick++;
        }


        if (!bombs.isEmpty()) {
            isBombActivated();
        }
        isStarCoinUp();
        isFinish();
        isCrash();
        control();

        //если натикало больше 500 в финишном поле
        //то загружаем экран со статистикой и выбором уровня
        if (finishDate >= 500){

            Levels.starCoinUpCount = starCoinsUp;
            Levels.crash = false;
            Levels.fuelOut = false;
            Levels.inInfinity = false;

            game.setScreen(new LevelClearScreen(game));
        }

        //крушение или взрыв бомбы
        if (flyObject.getHealthPoints() <= 0){

            Levels.crash = true;
            game.setScreen(new LevelClearScreen(game));
        }

        //если вылетели далеко за пределы экрана перезагружаем уровень
        if (flyObject.getX() > SettingsModels.displayHeight * 2 || flyObject.getY() > SettingsModels.displayWidth * 2 ||
            flyObject.getX() < -SettingsModels.displayHeight || flyObject.getY() < -SettingsModels.displayWidth){

            Levels.inInfinity = true;
            game.setScreen(new LevelClearScreen(game));
        }

        //если натикало больше 1000 после окончания топлива
        //то загружаем экран со статистикой и выбором уровня
        if (tick > 1000){

            Levels.fuelOut = true;
            game.setScreen(new LevelClearScreen(game));
        }


    }

    @Override
    public void present(float deltaTime) {

        fps.logFrame();

        GL10 gl = glGraphics.getGL();

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, Assets.displayWidth, 0, Assets.displayHeight, 1, -1);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        //draw background===========================================================================
        Textures.gameBackgroundTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.displayWidth_05,SettingsModels.displayHeight_05,0);
        Models.backgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //draw star coins============================================================
        for (StarCoin starCoin: starCoins) {

            Textures.starCoinTexture.bind();
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glTranslatef(starCoin.getStarCoinX(), starCoin.getStarCoinY(), 0);
            gl.glRotatef(angleFinish,0,0,1);
            Models.starCoinVertices.draw(GL10.GL_TRIANGLES, 0, 6);
        }

        //draw bomb============================================================
        if (!bombs.isEmpty()) {

            for (FlyObject bomb : bombs) {

                if (bomb.getBombExplosive()){

                    Textures.bombExplosiveTexture.bind();
                    gl.glMatrixMode(GL10.GL_MODELVIEW);
                    gl.glLoadIdentity();
                    gl.glTranslatef((float) bomb.getX(), (float) bomb.getY(), 0);
                    Models.bombExplosiveVertices.draw(GL10.GL_TRIANGLES,0,6);
                }

                if (bomb.getBombActivated()) {
                    Textures.bombActivateTexture.bind();
                } else {
                    Textures.bombNotActivateTexture.bind();
                }
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glTranslatef((float) bomb.getX(), (float) bomb.getY(), (float) bomb.getZ());
                gl.glRotatef(bomb.getAngleDirectXY(), 0, 0, 1);
                Models.bombBackgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);

                Textures.bombTexture.bind();
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glTranslatef((float) bomb.getX(), (float) bomb.getY(), (float) bomb.getZ());
                gl.glRotatef(bomb.getAngleDirectXY(), 0, 0, 1);
                Models.bombVertices.draw(GL10.GL_TRIANGLES, 0, 6);
            }
        }

        //draw crash texture===========================================================================
        if (crash) {

            Textures.crashTexture.bind();
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glTranslatef(SettingsModels.displayWidth_05,SettingsModels.displayHeight_05,0);
            Models.backgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);
        }

        //draw finish=================================================
        Textures.finishTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(Levels.level.getFinishX(), Levels.level.getFinishY(), 0);
        gl.glRotatef(angleFinish,0,0,1);
        Models.finishModel.draw(GL10.GL_TRIANGLES, 0, 6);

        //draw mass objects==================================================
        for (MassObject massObject: massObjects) {

            Textures.starTexture.bind();
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glTranslatef((float)massObject.getX(), (float)massObject.getY(), 0);
            Models.starVertices.draw(GL10.GL_TRIANGLES, 0, 6);
        }

        //==================================================
        Textures.fuelCountTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.fuelCountTranslateX, SettingsModels.fuelCountTranslateY, 0);
        Models.fuelCountVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //==================================================
        Textures.fuelBagTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.fuelBagTranslateX, SettingsModels.fuelBagTranslateY, 0);
        Models.fuelBagVertices.draw(GL10.GL_TRIANGLES, 0, 6);


        //==================================================
        if (trust) {

            Textures.rocketTrustTexture.bind();

        } else {

            Textures.rocketTexture.bind();
        }

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef((float) flyObject.getX(), (float) flyObject.getY(), 0);
        gl.glRotatef(angleRotate, 0,0,1);
        Models.rocketVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //drawBombFragment(gl);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

        reloadTextures.loadGameTextures(game);
    }

    @Override
    public void dispose() {

    }

    //=========================================================================
    private void drawBombFragment(FlyObject bomb, GL10 gl){

        bombFragments.add(new Vector3(bomb.getX(), bomb.getY(), bomb.getZ(),5, 5, 0));
        bombFragments.add(new Vector3(bomb.getX(), bomb.getY(), bomb.getZ(),5, 5, 0));
        bombFragments.add(new Vector3(bomb.getX(), bomb.getY(), bomb.getZ(),5, 5, 0));
        bombFragments.add(new Vector3(bomb.getX(), bomb.getY(), bomb.getZ(),5, 5, 0));



        Textures.bombFragmentsAtlasTexture.bind();
        int i = 100;
        for (float[] floats: BombModels.arrayBombFragmentVertices){

            i += 100;
            Models.bombFragmentVertices.setVertices(floats, 0, 16);

            Models.bombFragmentVertices.bind();
            gl.glLoadIdentity();
            gl.glTranslatef(i + 400,500,0);
            Models.bombFragmentVertices.modelDraw(GL10.GL_TRIANGLES, 0, 6);
            Models.bombFragmentVertices.unbind();
        }
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

                angle += 180;

                flyObject.setAngleDirectXY(angle);

                //рисуем огонёк у ракеты
                if (flyObject.getFuelMass() > 0.01) {
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

    //проверяем не собрана ли звезда
    private void isStarCoinUp(){

        for (StarCoin starCoin: starCoins) {

            double radius = Math.sqrt(Math.pow(flyObject.getX() - starCoin.getStarCoinX(), 2) +
                    Math.pow(flyObject.getY() - starCoin.getStarCoinY(), 2));

            if (radius <= starCoin.getRadius()) {

                starCoin.setStarCoinX(-10000);
                starCoin.setStarCoinY(-10000);
                starCoinsUp++;
            }
        }
    }

    private void isFinish() {

        double radius = Math.sqrt(Math.pow(flyObject.getX() - Levels.level.getFinishX(), 2) +
                                  Math.pow(flyObject.getY() - Levels.level.getFinishY(), 2));

        if (radius <= 100) {

            finishDate++;
            finish = true;
        }
        else {

            finishDate = 0;
            finish = false;
        }
    }

    private void isBombActivated(){


        double bombAngle;

        for (FlyObject bomb : bombs) {

            if (bomb.getZ() == 0) {
                double radius = Math.sqrt(Math.pow(flyObject.getX() - bomb.getX(), 2) +
                        Math.pow(flyObject.getY() - bomb.getY(), 2));

                if (bomb.getBombActivated()) {

                    bombAngle = -calculateDirect.getAngle(
                            (float) bomb.getX(), (float) bomb.getY(),
                            (float) flyObject.getX(), (float) flyObject.getY());

                    bomb.setAngleDirectXY((float) bombAngle);
                    calculateDirect.calculateDirection(bomb, 0, bombAngle, 0);
                    calculateCoordinate.calculate(massObjects, bomb, STEP);
                }

                if (radius <= 150) {

                    bomb.setBombActivated(true);
                }
                if (radius <= 50) {

                    bomb.setZ(10000);

                    bomb.setBombExplosive(true);
                    drawExplosive = true;

                    //при взрыве бомбы придаём ускорение ракете от взрыва
                    calculateDirect.calculateDirection(flyObject, 300,
                            180 + calculateDirect.getAngle((float)bomb.getX(),(float)bomb.getY(),
                                                     (float)flyObject.getX(),(float)flyObject.getY()) ,0);

                    flyObject.setHealthPoints(flyObject.getHealthPoints() - 10);
                }
            }

            if (bomb.getBombExplosive()){

                explosiveTick++;
                bomb.setDrawBombExplosiveTick(bomb.getDrawBombExplosiveTick() + 1);

                if (bomb.getDrawBombExplosiveTick() == 100){

                    bomb.setDrawBombExplosiveTick(0);
                    bomb.setBombExplosive(false);

                    drawExplosive = false;
                    explosiveTick = 0;
                }
            }
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

