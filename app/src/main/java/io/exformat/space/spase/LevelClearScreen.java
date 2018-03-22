package io.exformat.space.spase;


import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.Game;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
import io.exformat.space.model.Models;
import io.exformat.space.model.Textures;
import io.exformat.space.spase.settings.SettingsModels;

public class LevelClearScreen extends Screen {

    private GLGraphics glGraphics;

    public LevelClearScreen(Game game) {
        super(game);
        glGraphics = ((GLGame) game).getGLGraphics();

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {

        GL10 gl = glGraphics.getGL();

        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, Assets.displayWidth, 0, Assets.displayHeight, 1, -1);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        Textures.levelClearBackgroundTexture.bind();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glScalef(SettingsModels.scaleX,SettingsModels.scaleY,0);
        Models.levelClearBackgroundVertices.draw(GL10.GL_TRIANGLES, 0, 6);


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
}
