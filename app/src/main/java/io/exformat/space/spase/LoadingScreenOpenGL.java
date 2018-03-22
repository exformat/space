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
import io.exformat.space.model.models.modelsFHD.ModelsFHD;
import io.exformat.space.spase.settings.SettingsModels;

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


        Settings settings = new Settings();

        settings.calculateModels();

        loadTextures();

        loadModels();

        new Levels().choiceLevel(0);

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


        //load fuel out signal============================================
        Models.fueloutSignalVertices = new Vertices(glGraphics,
                4,12,false,true);
        Models.fueloutSignalVertices.setVertices(new float[]{
                -25 + SettingsModels.fuelOutSignalX, -25 + SettingsModels.fuelOutSignalY, 0, 1,
                 25 + SettingsModels.fuelOutSignalX, -25 + SettingsModels.fuelOutSignalY, 1, 1,
                 25 + SettingsModels.fuelOutSignalX,  25 + SettingsModels.fuelOutSignalY, 1, 0,
                -25 + SettingsModels.fuelOutSignalX,  25 + SettingsModels.fuelOutSignalY, 0, 0}, 0, 16);
        Models.fueloutSignalVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load count fuel=================================================
        Models.fuelCountVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.fuelCountVertices.setVertices(ModelsFHD.fuelCountVertices, 0, 16);
        Models.fuelCountVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load bag fuel===================================================
        Models.fuelBagVertices = new Vertices(glGraphics, 4,12, false, true);
        Models.fuelBagVertices.setVertices(ModelsFHD.fuelBagVertices, 0, 16);
        Models.fuelBagVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load rocket model=================================================
        Models.rocketVertices = new Vertices(glGraphics, 4, 12, false, true);
        Models.rocketVertices.setVertices(ModelsFHD.rocketVertices, 0, 16);
        Models.rocketVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load star model=================================================
        Models.starVertices = new Vertices(glGraphics, 4, 12, false, true);
        Models.starVertices.setVertices(ModelsFHD.starVertices, 0, 16);
        Models.starVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load finish model===============================================
        Models.finishModel = new Vertices(glGraphics, 4,12, false,true);
        Models.finishModel.setVertices(ModelsFHD.finishVertices,0,16);
        Models.finishModel.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load clear level model==========================================
        loadLevelClearModels();

        //==============================================
    }

    private void loadTextures(){

        Textures.rocketTexture      = new Texture((GLGame) game, "rocket.png");
        Textures.rocketTrustTexture = new Texture((GLGame) game, "rocket_trust.png");

        Textures.starTexture        = new Texture((GLGame) game, "star.png");

        Textures.fuelBagTexture     = new Texture((GLGame) game, "fuel_bag.png");
        Textures.fuelCountTexture   = new Texture((GLGame) game, "fuel_count.png");

        Textures.fuelOutSignalTexture  = new Texture((GLGame) game, "signal_fuel_out.png");
        Textures.fuelOutSignal2Texture = new Texture((GLGame) game, "signal_fuel_out_2.png");

        Textures.finishTexture = new Texture((GLGame) game, "finish.png");

        Textures.levelClearTexture = new Texture((GLGame) game, "level_clear.png");

        loadLevelClearTextures();
    }

    //=============================================
    private void loadLevelClearModels(){

        //load level clear background model=================================================
        Models.levelClearBackgroundVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.levelClearBackgroundVertices.setVertices(ModelsFHD.levelClearBackgroundVertices, 0, 16);
        Models.levelClearBackgroundVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load level clear rocket model=====================================================
        Models.levelClearRocketVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.levelClearRocketVertices.setVertices(ModelsFHD.levelClearRocketVertices, 0, 16);
        Models.levelClearRocketVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

    }

    private void loadLevelClearTextures(){

        Textures.levelClearBackgroundTexture = new Texture((GLGame) game, "level_clear_background.png");
        Textures.levelClearRocketTexture = new Texture((GLGame) game, "level_clear_rocket.png");
    }
}
