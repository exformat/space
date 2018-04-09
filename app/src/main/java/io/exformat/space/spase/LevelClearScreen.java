package io.exformat.space.spase;




import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.Input;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;

import io.exformat.space.model.LevelClearRocket;
import io.exformat.space.model.Models;
import io.exformat.space.model.Textures;
import io.exformat.space.model.models.modelsFHD.GameModels;

import io.exformat.space.spase.settings.SettingsModels;

public class LevelClearScreen extends Screen {

    private GLGraphics glGraphics;
    private LoadingModelsAndTextures reloadTextures = new LoadingModelsAndTextures();
    private LevelClearRocket rocket = new LevelClearRocket();
    private Levels levels = new Levels();



    private int touchDownX;
    private int touchDownY;

    private int touchDraggedX;
    private int touchDraggedY;

    private boolean startTouched = false;

    private boolean nextLevel = false;
    private boolean restart = false;


    public LevelClearScreen(io.exformat.space.framework.Game game) {
        super(game);
        glGraphics = ((GLGame) game).getGLGraphics();

    }

    @Override
    public void update(float deltaTime) {

        controlRocket();
        choiceLevel();
    }

    @Override
    public void present(float deltaTime) {

        GL10 gl = glGraphics.getGL();

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, Assets.displayWidth, 0, Assets.displayHeight, 1, -1);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        //draw background===========================================================================
        Textures.levelClearBackgroundTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.displayWidth_05,SettingsModels.displayHeight_05,0);
        Models.backgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //draw rocket===============================================================================
        Textures.levelClearRocketTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef((float)rocket.getX(), (float)rocket.getY(),0);
        Models.levelClearRocketVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        drawStarsOrCrashMessages(gl);
        //==========================================================================================
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

        reloadTextures.loadLevelClearTextures(game);
    }

    @Override
    public void dispose() {

    }

    //==============================================================================================

    private void drawStarsOrCrashMessages(GL10 gl){

        int inc = 760;


        if (!Levels.crash && !Levels.inInfinity && !Levels.fuelOut && Levels.starCoinUpCount > 0) {

            Textures.levelClearStarTexture.bind();

            for (int i = 0; i < Levels.starCoinUpCount; i++) {

                inc += 100;

                gl.glMatrixMode(GL10.GL_MODELVIEW);
                gl.glLoadIdentity();
                gl.glTranslatef(inc, 900, 0);
                Models.levelClearStarVertices.draw(GL10.GL_TRIANGLES, 0, 6);
            }
        }
        else {

            if (Levels.crash){

                Textures.levelClearCrashTexture.bind();
            }
            if (Levels.fuelOut){

                Textures.levelClearFuelOutTexture.bind();
            }
            if (Levels.inInfinity){

                Textures.levelClearFlyInInfinityTexture.bind();
            }
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glTranslatef((float)rocket.getX() + 100, (float)rocket.getY(), 0);
            Models.levelClearCrashMessageVertices.draw(GL10.GL_TRIANGLES, 0, 6);
        }
    }
    //==============================================================================================
    private void controlRocket(){

        if (!startTouched) {

            if (rocket.getX() >= SettingsModels.displayWidth_05) {

                rocket.setvX(0);
                startTouched = true;
            } else {
                rocket.setX(rocket.getX() + rocket.getvX());
            }
        }

        //===================================================================
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {

            Input.TouchEvent event = touchEvents.get(i);

            //первое касание записываем координаты
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {

                touchDownX = event.x;
                touchDownY = event.y;
            }

            if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {

                touchDraggedX = event.x;
                touchDraggedY = event.y;

                //йоббаный пиздуц... самому глаза ломает эта ветвистость волосатая
                //высчитываем новое направление
                if (touchDraggedX > touchDownX){

                    if (!Levels.crash) {
                        rocket.setX(touchDraggedX);
                        nextLevel = true;
                    }
                    else {
                        rocket.setvX(0);
                        rocket.setX(SettingsModels.displayWidth_05);
                    }
                }else {

                    rocket.setX(touchDraggedX);
                    restart = true;
                }
            }

            if (event.type == Input.TouchEvent.TOUCH_UP){

                if (touchDraggedX > touchDownX){

                    if (!Levels.crash) {
                        rocket.setvX(24);
                    }
                    else {
                        rocket.setvX(0);
                    }
                }else {
                    rocket.setvX(-24);
                }
            }
        }

        if (startTouched)
        rocket.setX(rocket.getX() + rocket.getvX());
    }

    private void choiceLevel(){

        int levelNumber;


        //load restart level==========================================
        if (restart){

            if (rocket.getX() < -SettingsModels.displayWidth){

                Levels.starCoinUpCount = 0;

                //reload levels
                //нужно для восстановления объектов, бомбы, звёзды и т.д.
                levelNumber = Levels.level.getLevelNumber();

                Levels.levels = new ArrayList<>();
                levels.oldInitialisationOpenSpaceLevels();
                Levels.level = levels.getLevel(levelNumber);

                //reload fuel count
                GameModels.fuelCountVertices = GameModels.fuelCountReloadVertices;
                SettingsModels.fuelCountTranslateX = 390;

                game.setScreen(new GameScreen(game));
            }
        }

        //load next level============================================
        if (nextLevel){

            if (rocket.getX() > SettingsModels.displayWidth * 2){

                if (Levels.level.getLevelNumber() < Levels.levels.size() - 1) {

                    levelNumber = Levels.level.getLevelNumber();

                    //reload levels
                    Levels.levels = new ArrayList<>();
                    levels.oldInitialisationOpenSpaceLevels();

                    Levels.starCoinUpCount = 0;
                    Levels.level = levels.getLevel(levelNumber + 1);

                    //reload fuel count
                    GameModels.fuelCountVertices = GameModels.fuelCountReloadVertices;
                    SettingsModels.fuelCountTranslateX = 390;

                    game.setScreen(new GameScreen(game));
                }
            }
        }

        //load levels menu
        //===========================================================
    }
}
