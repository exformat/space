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
import io.exformat.space.model.models.modelsFHD.GameModels;
import io.exformat.space.model.models.modelsFHD.MainMenuModels;
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

        //Assets.displayWidth = 1024;
        //Assets.displayHeight = 768;

        Log.d("height: ", "" + Assets.displayHeight);
        Log.d("width:  ", "" + Assets.displayWidth);


        Settings settings = new Settings();

        settings.calculateModels();

        Log.d("scale x: ", "" + SettingsModels.scaleX);



        loadLevelClearModels();
        loadGameModels();
        loadMainMenuModels();

        loadLevelClearTextures();
        loadGameTextures();
        loadMainMenuTextures();

        //game.setScreen(new SpaceOpenGL(game));
        game.setScreen(new MainMenuScreen(game));
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

    //=============================================
    private void loadGameModels(){


        /*
        //load fuel out signal============================================
        Models.fueloutSignalVertices = new Vertices(glGraphics,
                4,12,false,true);
        Models.fueloutSignalVertices.setVertices(new float[]{
                -25 + SettingsModels.fuelOutSignalX, -25 + SettingsModels.fuelOutSignalY, 0, 1,
                 25 + SettingsModels.fuelOutSignalX, -25 + SettingsModels.fuelOutSignalY, 1, 1,
                 25 + SettingsModels.fuelOutSignalX,  25 + SettingsModels.fuelOutSignalY, 1, 0,
                -25 + SettingsModels.fuelOutSignalX,  25 + SettingsModels.fuelOutSignalY, 0, 0}, 0, 16);
        Models.fueloutSignalVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);
        */

        //load star coin=================================================
        Models.starCoinVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.starCoinVertices.setVertices(GameModels.starCoinVertices, 0, 16);
        Models.starCoinVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load count fuel=================================================
        Models.fuelCountVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.fuelCountVertices.setVertices(GameModels.fuelCountVertices, 0, 16);
        Models.fuelCountVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load bag fuel===================================================
        Models.fuelBagVertices = new Vertices(glGraphics, 4,12, false, true);
        Models.fuelBagVertices.setVertices(GameModels.fuelBagVertices, 0, 16);
        Models.fuelBagVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load rocket model=================================================
        Models.rocketVertices = new Vertices(glGraphics, 4, 12, false, true);
        Models.rocketVertices.setVertices(GameModels.rocketVertices, 0, 16);
        Models.rocketVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load star model=================================================
        Models.starVertices = new Vertices(glGraphics, 4, 12, false, true);
        Models.starVertices.setVertices(GameModels.starVertices, 0, 16);
        Models.starVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load finish model===============================================
        Models.finishModel = new Vertices(glGraphics, 4,12, false,true);
        Models.finishModel.setVertices(GameModels.finishVertices,0,16);
        Models.finishModel.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load bomb model===============================================
        Models.bombVertices = new Vertices(glGraphics, 4,12, false,true);
        Models.bombVertices.setVertices(GameModels.bombVertices,0,16);
        Models.bombVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load bomb model===============================================
        Models.bombBackgroundVertices = new Vertices(glGraphics, 4,12, false,true);
        Models.bombBackgroundVertices.setVertices(GameModels.bombBackgroundVertices,0,16);
        Models.bombBackgroundVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //==============================================
    }
    private void loadGameTextures(){

        Textures.gameBackgroundTexture = new Texture((GLGame) game, "background.png");

        Textures.crashTexture = new Texture((GLGame) game, "crash.png");

        Textures.bombTexture = new Texture((GLGame) game, "gameScreen/bombTextures/bomb.png");
        Textures.bombActivateTexture = new Texture((GLGame) game, "gameScreen/bombTextures/bomb_activated.png");
        Textures.bombNotActivateTexture = new Texture((GLGame) game, "gameScreen/bombTextures/bomb_not_activated.png");

        Textures.starCoinTexture = new Texture((GLGame) game, "star_coin.png");

        Textures.rocketTexture      = new Texture((GLGame) game, "rocket.png");
        Textures.rocketTrustTexture = new Texture((GLGame) game, "rocket_trust.png");

        Textures.starTexture        = new Texture((GLGame) game, "star.png");

        Textures.fuelBagTexture     = new Texture((GLGame) game, "fuel_bag.png");
        Textures.fuelCountTexture   = new Texture((GLGame) game, "fuel_count.png");

        Textures.fuelOutSignalTexture  = new Texture((GLGame) game, "signal_fuel_out.png");
        Textures.fuelOutSignal2Texture = new Texture((GLGame) game, "signal_fuel_out_2.png");

        Textures.finishTexture = new Texture((GLGame) game, "finish.png");

        Textures.levelClearTexture = new Texture((GLGame) game, "level_clear.png");

    }

    //=============================================
    private void loadMainMenuModels(){

        //load rocket levels=================================================
        Models.rocketLevelsVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.rocketLevelsVertices.setVertices(MainMenuModels.rocketLevelsVertices, 0, 16);
        Models.rocketLevelsVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load button sound=================================================
        Models.buttonSoundVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.buttonSoundVertices.setVertices(MainMenuModels.buttonSoundVertices, 0, 16);
        Models.buttonSoundVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

    }
    private void loadMainMenuTextures(){

        Textures.mainMenuBackroundTexture = new Texture((GLGame) game, "mainMenuScreen/background.png");
        Textures.rocketLevelsTexture = new Texture((GLGame) game, "mainMenuScreen/rocket_levels.png");
        Textures.soundOnTexture = new Texture((GLGame) game, "sound_on.png");
        Textures.soundOffTexture = new Texture((GLGame) game, "sound_off.png");
    }


    //=============================================
    private void loadLevelClearModels(){

        //load level clear background model=================================================
        Models.backgroundVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.backgroundVertices.setVertices(GameModels.levelClearBackgroundVertices, 0, 16);
        Models.backgroundVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load level clear rocket model=====================================================
        Models.levelClearRocketVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.levelClearRocketVertices.setVertices(GameModels.levelClearRocketVertices, 0, 16);
        Models.levelClearRocketVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

    }
    private void loadLevelClearTextures(){

        Textures.levelClearBackgroundTexture = new Texture((GLGame) game, "level_clear_background.png");
        Textures.levelClearRocketTexture = new Texture((GLGame) game, "level_clear_rocket.png");
    }
}
