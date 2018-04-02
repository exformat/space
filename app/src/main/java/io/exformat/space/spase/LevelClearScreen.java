package io.exformat.space.spase;


import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.Game;
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

    //private int thisLevel = Levels.getLevelNumber();

    private Levels levels = new Levels();

    private int touchDownX;
    private int touchDownY;

    private int touchDraggedX;
    private int touchDraggedY;

    private boolean startTouched = false;

    private boolean nextLevel = false;
    private boolean restart = false;


    public LevelClearScreen(Game game) {
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

        //gl.glClearColor(0, 0, 0, 0);
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
        gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleX,0);
        Models.backgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //draw rocket===============================================================================
        Textures.levelClearRocketTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef((float)rocket.getX(), (float)rocket.getY(),0);
        gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleX,0);
        Models.levelClearRocketVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //==========================================================================================
        update(deltaTime);
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

                //высчитываем новое направление
                if (touchDraggedX > touchDownX){

                    rocket.setX(touchDraggedX);
                    nextLevel = true;
                }else {

                    rocket.setX(touchDraggedX);
                    restart = true;
                }
            }

            if (event.type == Input.TouchEvent.TOUCH_UP){

                if (touchDraggedX > touchDownX){
                    rocket.setvX(12);
                }else {
                    rocket.setvX(-12);
                }
            }
        }

        if (startTouched)
        rocket.setX(rocket.getX() + rocket.getvX());
    }

    private void choiceLevel(){

        //load restart level==========================================
        if (restart){

            if (rocket.getX() < -SettingsModels.displayWidth){

                Levels.starCoinUpCount = 0;
                levels.choiceLevel(Levels.getThisLevel());

                //reload fuel count
                GameModels.fuelCountVertices = GameModels.fuelCountReloadVertices;
                SettingsModels.fuelCountTranslateX = 390 * SettingsModels.scaleX;

                game.setScreen(new SpaceOpenGL(game));
            }
        }

        //load next level============================================
        if (nextLevel){

            if (rocket.getX() > SettingsModels.displayWidth * 2){

                if (Levels.getThisLevel() != Levels.MAX_LEVEL) {

                    Levels.starCoinUpCount = 0;
                    levels.choiceLevel(Levels.getThisLevel() + 1);

                    //reload fuel count
                    GameModels.fuelCountVertices = GameModels.fuelCountReloadVertices;
                    SettingsModels.fuelCountTranslateX = 390 * SettingsModels.scaleX;

                    game.setScreen(new SpaceOpenGL(game));
                }
            }
        }

        //load levels menu
        //===========================================================
    }
}
