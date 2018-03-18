package io.exformat.space.spase;


import android.util.Log;

import io.exformat.space.framework.Game;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
import io.exformat.space.framework.openGL.Texture;
import io.exformat.space.framework.openGL.Vertices;
import io.exformat.space.model.Models;
import io.exformat.space.model.Textures;

public class LoadingScreenOpenGL extends Screen {


    private GLGraphics glGraphics;


    public LoadingScreenOpenGL(Game game) {
        super(game);

        glGraphics = ((GLGame)game).getGLGraphics();
    }

    @Override
    public void update(float deltaTime) {

        Assets.displayWidth = glGraphics.getWidth();
        Assets.displayHeight = glGraphics.getHeight();

        Log.d("height: ", "" + Assets.displayHeight);
        Log.d("width:  ", "" + Assets.displayWidth);

        loadTextures();

        loadModels();

        game.setScreen(new SpaceOpenGL(game));
    }

    @Override
    public void present(float deltaTime) {

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

    private void loadModels(){

        int x = Assets.displayWidth / 2;
        int y = Assets.displayHeight / 2;

        int xx = Assets.displayWidth / 10 / 2;


        Models.fuelCountVertices = new Vertices(glGraphics,
                4,12, false, true);

        Models.fuelCountVertices.setVertices(new float[]{
                -54+xx, -400+y, 0, 1,
                 54+xx, -400+y, 1, 1,
                 54+xx,  400+y, 1, 0,
                -54+xx,  400+y, 0, 0}, 0, 16);

        Models.fuelCountVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load rocket model
        Models.rocketVertices = new Vertices(glGraphics,
                4, 12, false, true);

        Models.rocketVertices.setVertices(new float[]{-50, -50, 0, 1,
                                                       50, -50, 1, 1,
                                                       50,  50, 1, 0,
                                                      -50,  50, 0, 0}, 0, 16);

        Models.rocketVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load star model
        Models.starVertices = new Vertices(glGraphics,
                4, 12, false, true);

        Models.starVertices.setVertices(new float[]{-50+x, -50+y, 0, 1,
                                                     50+x, -50+y, 1, 1,
                                                     50+x,  50+y, 1, 0,
                                                    -50+x,  50+y, 0, 0},
                                                0, 16);

        Models.starVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);
    }

    private void loadTextures(){

        Textures.rocketTexture      = new Texture((GLGame) game, "rocket.png");
        Textures.rocketTrustTexture = new Texture((GLGame) game, "rocket_trust.png");

        Textures.starTexture        = new Texture((GLGame) game, "star.png");
        Textures.fuelCountTexture = new Texture((GLGame) game, "fuel.png");
    }
}
