package io.exformat.space.spase;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.Input;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.game.math.MyMath;
import io.exformat.space.framework.game.math.interfaces.IMyMath;
import io.exformat.space.framework.game.objects.FlyObject;
import io.exformat.space.framework.game.objects.VectorXYZ;
import io.exformat.space.framework.game.physics.Direct;
import io.exformat.space.framework.game.physics.Gravity;
import io.exformat.space.framework.game.physics.interfaces.IDirect;
import io.exformat.space.framework.game.physics.interfaces.IGravity;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
import io.exformat.space.framework.openGL.FPSCounter;
import io.exformat.space.model.Bomb;
import io.exformat.space.model.Models;
import io.exformat.space.model.Rocket;
import io.exformat.space.model.Textures;
import io.exformat.space.spase.settings.SettingsModels;


public class GameScreen extends Screen {

    private GLGraphics glGraphics;
    private FPSCounter fps = new FPSCounter();

    private LoadingModelsAndTextures reloadTextures = new LoadingModelsAndTextures();

    private final float STEP = 0.01f;

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




    private IGravity gravity = new Gravity();
    private IDirect direct = new Direct();
    private IMyMath myMath = new MyMath();

    //инициализируем ракету, массивные объекты, звёзды и бомбы
    private Rocket rocket = Levels.level.getRocket();
    private ArrayList<FlyObject> massObjects = Levels.level.getMassObjects();
    private ArrayList<VectorXYZ>     starCoins = Levels.level.getStarCoins();
    private ArrayList<Bomb>        bombs = Levels.level.getBombs();

    public GameScreen(io.exformat.space.framework.Game game) {

        super(game);
        glGraphics = ((GLGame) game).getGLGraphics();
    }

    @Override
    public void update(float deltaTime) {


        angleRotate += rocket.getAngleSpeedXY() * STEP;
        rocket.setAngleDirectXY(angleRotate);



        //rotate finish image
        angleFinish += -0.1f;

        fuelOut = rocket.getFuelMass() > 0.01;

        //перерисовываем количество топлива
        if (fuelOut) {

            if (trust) {
                //высчитываем новое направление
                direct.calculateImpulseDirect(rocket, 0, angle, 0);

                rocket.setAngleSpeedXY(0);
                angleRotate = angle;

                SettingsModels.fuelCountTranslateX -= (rocket.getFuelOut() * (7.15f));
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
        if (rocket.getHealthPoints() <= 0){

            Levels.crash = true;
            game.setScreen(new LevelClearScreen(game));
        }

        //если вылетели далеко за пределы экрана перезагружаем уровень
        if (rocket.getX() > SettingsModels.displayHeight * 2 || rocket.getY() > SettingsModels.displayWidth * 2 ||
            rocket.getX() < -SettingsModels.displayHeight || rocket.getY() < -SettingsModels.displayWidth){

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
        for (VectorXYZ starCoin: starCoins) {

            Textures.starCoinTexture.bind();
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glTranslatef(starCoin.getX(), starCoin.getY(), 0);
            gl.glRotatef(angleFinish,0,0,1);
            Models.starCoinVertices.draw(GL10.GL_TRIANGLES, 0, 6);
        }

        //draw bomb============================================================
        if (!bombs.isEmpty()) {

            for (Bomb bomb : bombs) {

                if (bomb.isBombExplosive()){

                    Textures.bombExplosiveTexture.bind();
                    gl.glMatrixMode(GL10.GL_MODELVIEW);
                    gl.glLoadIdentity();
                    gl.glTranslatef(bomb.getX(), bomb.getY(), 0);
                    Models.bombExplosiveVertices.draw(GL10.GL_TRIANGLES,0,6);
                }

                if (bomb.isBombActivated()) {
                    Textures.bombActivateTexture.bind();
                } else {
                    Textures.bombNotActivateTexture.bind();
                }
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glTranslatef(bomb.getX(), bomb.getY(), bomb.getZ());
                gl.glRotatef(bomb.getAngleDirectXY(), 0, 0, 1);
                Models.bombBackgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);

                Textures.bombTexture.bind();
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glTranslatef(bomb.getX(), bomb.getY(), bomb.getZ());
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
        gl.glTranslatef(Levels.level.getFinish().getX(), Levels.level.getFinish().getY(), 0);
        gl.glRotatef(angleFinish,0,0,1);
        Models.finishModel.draw(GL10.GL_TRIANGLES, 0, 6);

        //draw mass objects==================================================
        if (massObjects.isEmpty()) {
            for (FlyObject massObject : massObjects) {

                Textures.starTexture.bind();
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glTranslatef(massObject.getX(), massObject.getY(), 0);
                Models.starVertices.draw(GL10.GL_TRIANGLES, 0, 6);
            }
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
        gl.glTranslatef(rocket.getX(), rocket.getY(), 0);
        gl.glRotatef(rocket.getAngleDirectXY(), 0,0,1);
        gl.glRotatef(angleRotate, 0,0,1);
        Models.rocketVertices.draw(GL10.GL_TRIANGLES, 0, 6);

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
    //TODO ну его нафик, потом доделаю...
    /*
    private void azazaza(){
        Log.d("rocket angle", "" + rocket.getAngleDirectXY());

        if (rocket.getY() <= 100){

            if (Math.round(rocket.getVx()) != 0){

                if (rocket.getVx() > 0) {

                    rocket.setVx(rocket.getVx() - 3);
                }
                else {

                    rocket.setVx(rocket.getVx() + 3);
                }
            }


            if (rocket.getAngleDirectXY() < 375 && rocket.getAngleDirectXY() > 345){

                Log.d("rocket angle", "" + rocket.getAngleDirectXY());

                rocket.setAngleDirectXY(0);
            }
            else {

                if (rocket.getAngleDirectXY() > 375){

                    if (Math.round(rocket.getAngleDirectXY()) != 450) {
                        rocket.setAngleSpeedXY(160);
                    }
                    else{
                        rocket.setAngleSpeedXY(0);
                    }
                }
                else {

                    if (Math.round(rocket.getAngleDirectXY()) != 270) {

                        rocket.setAngleSpeedXY(-160);
                    }
                    else{
                        rocket.setAngleSpeedXY(0);
                    }
                }
            }

            rocket.setVy(0);
            rocket.setY(100);
        }

    }
    private void drawBombFragment(FlyObject bomb, GL10 gl){

        if (bomb.getDrawBombExplosiveTick() == 1) {

            bombFragments.add(new Vector3(bomb.getX(), bomb.getY(), bomb.getZ(), 5, 5, 0));
            bombFragments.add(new Vector3(bomb.getX(), bomb.getY(), bomb.getZ(), 5, 5, 0));
            bombFragments.add(new Vector3(bomb.getX(), bomb.getY(), bomb.getZ(), 5, 5, 0));
            bombFragments.add(new Vector3(bomb.getX(), bomb.getY(), bomb.getZ(), 5, 5, 0));

            bomb.setBombFragments(bombFragments);
            bombFragments = new ArrayList<>();
        }


        Textures.bombFragmentsAtlasTexture.bind();
        int a = 100;

        for (int i = 0; i < 4; i++){


        }

        for (float[] floats: BombModels.arrayBombFragmentVertices){

            a += 100;
            Models.bombFragmentVertices.setVertices(floats, 0, 16);

            Models.bombFragmentVertices.bind();
            gl.glLoadIdentity();
            gl.glTranslatef(a + 400,500,0);
            Models.bombFragmentVertices.modelDraw(GL10.GL_TRIANGLES, 0, 6);
            Models.bombFragmentVertices.unbind();
        }

    }
    */

    private void control() {

        if (!massObjects.isEmpty()){
            gravity.calculateInertial(rocket, STEP);
        }
        else {
            gravity.calculateGravity(massObjects, rocket, STEP);
        }

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

                angle = myMath.calculateAngle(touchDownX, touchDownY,
                                              touchDraggedX, touchDraggedY);

                angle += 180;

                rocket.setAngleDirectXY(angle);

                //рисуем огонёк у ракеты
                if (rocket.getFuelMass() > 0.01) {
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

        for (FlyObject massObject : massObjects) {

            float distance = myMath.calculateDistance(rocket, massObject);

            distance = distance - massObject.getRadius() - rocket.getRadius();

            if (Math.round(distance) <= 0) {

                rocket.setVelocityX(0);
                rocket.setVelocityY(0);

                crash = true;
                break;
            } else {
                crash = false;
            }
        }
    }

    //проверяем не собрана ли звезда
    private void isStarCoinUp(){

        for (VectorXYZ starCoin: starCoins) {

            float radius = (float) Math.sqrt(Math.pow(rocket.getX() - starCoin.getX(), 2) +
                    Math.pow(rocket.getY() - starCoin.getY(), 2));

            if (radius <= 15) {

                starCoin.setX(-10000);
                starCoin.setY(-10000);
                starCoinsUp++;
            }
        }
    }

    private void isFinish() {

        double radius = Math.sqrt(Math.pow(rocket.getX() - Levels.level.getFinish().getX(), 2) +
                                  Math.pow(rocket.getY() - Levels.level.getFinish().getY(), 2));

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


        float bombAngle;

        for (Bomb bomb : bombs) {

            if (bomb.getZ() == 0) {
                double radius = Math.sqrt(Math.pow(rocket.getX() - bomb.getX(), 2) +
                                          Math.pow(rocket.getY() - bomb.getY(), 2));

                if (bomb.isBombActivated()) {

                    bombAngle = -myMath.calculateAngle(
                            bomb.getX(), bomb.getY(),
                            rocket.getX(), rocket.getY());

                    bomb.setAngleDirectXY(bombAngle);
                    direct.calculateImpulseDirect(bomb, bomb.getPowerTrust(), bombAngle, 0);

                    if (massObjects.isEmpty()) {
                        gravity.calculateInertial(bomb, STEP);
                    }
                    else {
                        gravity.calculateGravity(massObjects, bomb, STEP);
                    }
                }

                if (radius <= 150) {

                    bomb.setBombActivated(true);
                }
                if (radius <= 50) {

                    bomb.setZ(1000000000);

                    bomb.setBombExplosive(true);
                    drawExplosive = true;

                    //при взрыве бомбы придаём ускорение ракете от взрыва
                    direct.calculateImpulseDirect(rocket, 300,
                            180 + myMath.calculateAngle(bomb.getX(), bomb.getY(),
                                                     rocket.getX(), rocket.getY()) ,0);

                    rocket.setHealthPoints(rocket.getHealthPoints() - 10);
                }
            }

            if (bomb.isBombExplosive()){

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

