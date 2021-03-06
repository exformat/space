package io.exformat.space.spase;


import android.util.Log;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.Input;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
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

    private int listLevels = 0;


    public ChoiceLevelScreen(io.exformat.space.framework.Game game) {
        super(game);
        glGraphics = ((GLGame) game).getGLGraphics();
    }

    @Override
    public void update(float deltaTime) {

        control();
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
        Textures.choiceLevelBackgroundTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.displayWidth_05,SettingsModels.displayHeight_05,0);
        Models.backgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);


        for (Level level: Levels.packageLevel.getLevels()) {

            draw(gl, Textures.choiceLevelNumberTexture,
                     Models.choiceNumberLevelFrameVertices,
                     level.getVector().getX(),
                     level.getVector().getY(), 0);

            drawNumeralVertices(level,gl);
        }

        /*/draw choice level frame===========================================================================
        Textures.choiceLevelFrameTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.displayWidth_05,SettingsModels.displayHeight_05,0);
        Models.backgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);
        */
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

                Log.d("touch x: ", event.x + "");
                Log.d("touch y: ", event.y + "");

                choiceListLevels(event);
                choiceLevel(event);
            }
        }
    }

    private void choiceListLevels(Input.TouchEvent event){

        //левая кнопка выбора списка уровней
        //смещает список уровней влево
        if (listLevels < Levels.packageLevel.getLevels().size() / 8) {

            if (inBounds(event, 0,0,300,700)){

                listLevels++;

                for (Level level : Levels.packageLevel.getLevels()) {

                    level.getVector().setX((float) level.getVector().getX() - 1210);
                }
            }
        }

        //правая кнопка выбора списка уровней
        //смещает список уровней вправо
        if (listLevels > 0) {

            if (inBounds(event, 1600,0,300,700)){

                listLevels--;

                for (Level level : Levels.packageLevel.getLevels()) {

                    level.getVector().setX((float) level.getVector().getX() + 1210);
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
        if (inBounds(event, 350,70,1240,700)){

            for(Level level: Levels.packageLevel.getLevels()){

                x = (int)level.getVector().getX() - 130;
                y = (int)level.getVector().getY() - 150;
                width  = (int)level.getVector().getX() + 130;
                height = (int)level.getVector().getY() + 150;

                if (inBounds(event,x,y,width,height)){

                    Levels.level = level;

                    game.setScreen(new GameScreen(game));
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {

        if (event.x > x && event.x < x + width - 1 &&
            1080 - event.y > y && 1080 - event.y < y + height - 1) {
            return true;
        } else {
            return false;
        }
    }

    //рисует цифры на иконке уровня, не больше 99 уровней..
    private void drawNumeralVertices(Level level, GL10 gl){

        Textures.numeralFontTexture.bind();

        if (level.getLevelNumber() < 10) {

            Models.numeralFontVertices.setVertices(ChoiceLevelModels.arrayNumeralFontVertices.get(level.getLevelNumber() + 1), 0, 16);

            Models.numeralFontVertices.bind();
            gl.glLoadIdentity();
            gl.glTranslatef(level.getVector().getX(),
                    level.getVector().getY() - 55, 0);
            Models.numeralFontVertices.modelDraw(GL10.GL_TRIANGLES, 0, 6);
            Models.numeralFontVertices.unbind();
        }
        else {

            String num = level.getLevelNumber() + "";

            for (int i = 0; i < num.length(); i++){

                Models.numeralFontVertices.setVertices(ChoiceLevelModels.arrayNumeralFontVertices.get(Character.getNumericValue(num.charAt(i))), 0, 16);

                Models.numeralFontVertices.bind();

                gl.glLoadIdentity();

                if (i == 0) {

                    gl.glTranslatef(level.getVector().getX() - 50,
                                   level.getVector().getY() - 55, 0);
                }
                else {
                    gl.glTranslatef(level.getVector().getX() + 50,
                                   level.getVector().getY() - 55, 0);
                }
                Models.numeralFontVertices.modelDraw(GL10.GL_TRIANGLES, 0, 6);
                Models.numeralFontVertices.unbind();
            }
        }
    }

    private void draw(GL10 gl,
                      Texture texture,
                      Vertices model,
                      float translateX,
                      float translateY,
                      float rotateAngle){

        texture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(translateX,translateY,0);
        gl.glRotatef(rotateAngle,0,0,1);
        model.draw(GL10.GL_TRIANGLES, 0, 6);
    }
}
