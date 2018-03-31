package io.exformat.space.spase;

import android.util.Log;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.Game;
import io.exformat.space.framework.Input;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
import io.exformat.space.model.Models;
import io.exformat.space.model.Textures;
import io.exformat.space.spase.settings.SettingsModels;


public class MainMenuScreen extends Screen {

    private GLGraphics glGraphics;

    private boolean sound = false;//TODO прикрутить запись настроек

    private int touchDownX;
    private int touchDownY;

    private int touchUpX;
    private int touchUpY;

    private int touchDraggedX;
    private int touchDraggedY;

    public MainMenuScreen(Game game) {
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
        Textures.mainMenuBackgroundTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.displayWidth_05,SettingsModels.displayHeight_05,0);
        gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleX,0);
        Models.backgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //draw levels===========================================================================
        Textures.rocketLevelsTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(SettingsModels.displayWidth_05,SettingsModels.displayHeight_05,0);
        gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleX,0);
        Models.rocketLevelsVertices.draw(GL10.GL_TRIANGLES, 0, 6);

        //draw sound===========================================================================
        if (sound){
            Textures.soundOnTexture.bind();
        }
        else {
            Textures.soundOffTexture.bind();
        }
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(1820 * SettingsModels.scaleX,980 * SettingsModels.scaleX,0);
        gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleX,0);
        Models.buttonSoundVertices.draw(GL10.GL_TRIANGLES, 0, 6);

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

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {

            Input.TouchEvent event = touchEvents.get(i);

            //первое касание записываем координаты
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {

                touchDownX = event.x;
                touchDownY = event.y;

                Log.d("touch down x: ", "" + touchDownX);
                Log.d("touch down y: ", "" + touchDownY);

            }

            if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {

                touchDraggedX = event.x;
                touchDraggedY = event.y;

            }

            if (event.type == Input.TouchEvent.TOUCH_UP) {

                touchUpX = event.x;
                touchUpY = event.y;

                Log.d("touch up x: ", "" + touchUpX);
                Log.d("touch up y: ", "" + touchUpY);

                if (touchUpX > 1800 * SettingsModels.scaleX && touchUpY < 250 * SettingsModels.scaleX){

                    sound = !sound;
                    Log.d("sound: ", "" + sound);

                }

                if (touchUpX > 704 * SettingsModels.scaleX && touchUpX < 1216 * SettingsModels.scaleX &&
                    touchUpY > 248 * SettingsModels.scaleX && touchUpY < 796 * SettingsModels.scaleX){

                    new Levels().choiceLevel(0);
                    //game.setScreen(new SpaceOpenGL(game));
                    game.setScreen(new ChoiceLevelScreen(game));
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
