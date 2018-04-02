package io.exformat.space.spase;


import android.util.Log;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.Game;
import io.exformat.space.framework.Input;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
import io.exformat.space.framework.openGL.FPSCounter;
import io.exformat.space.framework.openGL.Texture;
import io.exformat.space.framework.openGL.Vertices;
import io.exformat.space.model.Level;
import io.exformat.space.model.Models;
import io.exformat.space.model.Textures;
import io.exformat.space.model.models.modelsFHD.ChoiceLevelModels;
import io.exformat.space.spase.settings.SettingsModels;

public class ChoiceLevelScreen extends Screen {


    private GLGraphics glGraphics;
    private LoadingModelsAndTextures reloadTextures = new LoadingModelsAndTextures();


    private int levelFrameTranslateX = 510;
    private int levelFrameTranslateY = 590;

    private int levelCount = 0;
    private int listLevels = 0;


    public ChoiceLevelScreen(Game game) {
        super(game);
        glGraphics = ((GLGame) game).getGLGraphics();
    }

    @Override
    public void update(float deltaTime) {

        control();
    }

    @Override
    public void present(float deltaTime) {

        //fps.logFrame();

        GL10 gl = glGraphics.getGL();

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, Assets.displayWidth, 0, Assets.displayHeight, 1, -1);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);


        //draw background===========================================================================
        Textures.choiceLevelBackgroundTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.displayWidth_05,SettingsModels.displayHeight_05,0);
        gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleX,0);
        Models.backgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);


        for (Level level: Levels.levels) {

            Textures.choiceLevelNumberTexture.bind();
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();

            /*
            if (level.getTranslateY() == 255){

                level.setTranslateX(level.getTranslateX() - 10);

                if (level.getTranslateX() == 520){

                }
                //Log.d("level translate x: ","" + level.getTranslateX());
            }
            */

            gl.glTranslatef(level.getTranslateX(), level.getTranslateY(), 0);

            gl.glScalef(SettingsModels.scaleX, SettingsModels.scaleX, 0);
            Models.choiceNumberLevelFrameVertices.draw(GL10.GL_TRIANGLES, 0, 6);

            drawNumeralVertices(level, gl);


            Log.d("level translate x: ","" + level.getTranslateX());

            translateNumberLevelFrame();
            Log.d("level translate x: ","" + level.getTranslateX());

            if (level.getTranslateX() == 520){
                level.getTranslateX();
                break;
            }
        }

        //draw choice level frame===========================================================================
        Textures.choiceLevelFrameTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.displayWidth_05,SettingsModels.displayHeight_05,0);
        gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleX,0);
        Models.backgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

        reloadTextures.loadMainMenuTextures(game);
    }

    @Override
    public void dispose() {

    }

    private void drawNumeralVertices(Level level, GL10 gl){

        Textures.numeralFontTexture.bind();

        if (level.getLevelNumber() < 10) {

            Models.numeralFontVertices.setVertices(ChoiceLevelModels.arrayNumeralFontVertices.get(level.getLevelNumber() + 1), 0, 16);

            Models.numeralFontVertices.bind();
            gl.glLoadIdentity();
            gl.glTranslatef(level.getTranslateX(), level.getTranslateY() - 55, 0);
            //gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleX,0);
            Models.numeralFontVertices.draw2(GL10.GL_TRIANGLES, 0, 6);
            Models.numeralFontVertices.unbind();
        }
        else {

            String num = level.getLevelNumber() + "";

            for (int i = 0; i < num.length(); i++){

                Models.numeralFontVertices.setVertices(ChoiceLevelModels.arrayNumeralFontVertices.get(Character.getNumericValue(num.charAt(i))), 0, 16);

                Models.numeralFontVertices.bind();

                gl.glLoadIdentity();

                if (i == 0) {

                    gl.glTranslatef(level.getTranslateX() - 50, level.getTranslateY() - 55, 0);
                }
                else {
                    gl.glTranslatef(level.getTranslateX() + 50, level.getTranslateY() - 55, 0);
                }
                Models.numeralFontVertices.draw2(GL10.GL_TRIANGLES, 0, 6);
                Models.numeralFontVertices.unbind();
            }
        }
    }

    //TODO есть баг в отображении нижнего ряда иконок уровней, смещены вправо пикселей на 10
    private void translateNumberLevelFrame(){

        if (levelCount <= 7) {

            levelCount++;
        }else {

            levelCount = 0;
        }

        if (levelFrameTranslateX <= 1400){

            levelFrameTranslateX += 300;
        }else {

            levelFrameTranslateX = 510;
        }

        if (levelCount < 3){

            levelFrameTranslateY = 590;
        }else {

            levelFrameTranslateY = 255;
        }
    }

    private void control(){

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {

            Input.TouchEvent event = touchEvents.get(i);

            //первое касание записываем координаты
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {

                int touchDownX = event.x;
                int touchDownY = event.y;
            }

            if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {

                int touchDraggedX = event.x;
                int touchDraggedY = event.y;
            }

            if (event.type == Input.TouchEvent.TOUCH_UP) {

                int touchUpX = event.x;
                int touchUpY = event.y;

                choiceListLevels(event);
                choiceLevel(event);
            }
        }
    }

    private void choiceListLevels(Input.TouchEvent event){

        //левая кнопка выбора списка уровней
        //смещает список уровней влево
        if (listLevels < Levels.levels.size() / 8) {

            if (inBounds(event, 0,0,300,700)){

                listLevels++;
                for (Level level : Levels.levels) {

                    level.setTranslateX(level.getTranslateX() - 1210);
                }
            }
        }

        //правая кнопка выбора списка уровней
        //смещает список уровней вправо
        if (listLevels > 0) {

            if (inBounds(event, 1600,0,300,700)){

                listLevels--;
                for (Level level : Levels.levels) {

                    level.setTranslateX(level.getTranslateX() + 1210);
                }
            }
        }
    }

    private void choiceLevel(Input.TouchEvent event){

        int x;
        int y;
        int width;
        int height;

        //если нажатие в поле отображения списка уровней
        if (inBounds(event, 350,70,1240,710)){

            for(Level level: Levels.levels){

                x = (int)level.getTranslateX() - 130;
                y = (int)level.getTranslateY() - 150;
                width = (int)level.getTranslateX() + 130;
                height = (int)level.getTranslateY() + 150;

                if (inBounds(event,x,y,width,height)){

                    Levels.level = level;
                    Log.d("level number: ", level.getLevelNumber() + "");
                    game.setScreen(new SpaceOpenGL(game));

                    //TODO magic
                    //ниибу почему но если насильно не остановить цикл
                    //то пытаются загрузиться еще несколько уровней
                    break;
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
