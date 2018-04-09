package io.exformat.space.spase;


import io.exformat.space.framework.Game;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
import io.exformat.space.framework.openGL.Texture;
import io.exformat.space.framework.openGL.Vertices;
import io.exformat.space.model.Models;
import io.exformat.space.model.Textures;
import io.exformat.space.model.models.modelsFHD.ChoiceLevelModels;
import io.exformat.space.model.models.modelsFHD.GameModels;
import io.exformat.space.model.models.modelsFHD.LevelClearModels;
import io.exformat.space.model.models.modelsFHD.MainMenuModels;
import io.exformat.space.model.models.modelsFHD.gameModels.BombModels;

public class LoadingModelsAndTextures {

    public void loadModelsAndTextures(GLGraphics glGraphics,Game game){

        loadGameModels(glGraphics);
        loadGameTextures(game);

        loadLevelClearModels(glGraphics);
        loadLevelClearTextures(game);

        loadMainMenuModels(glGraphics);
        loadMainMenuTextures(game);
    }

    //=============================================
    private void loadGameModels(GLGraphics glGraphics){

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

        //load bomb background model===============================================
        Models.bombBackgroundVertices = new Vertices(glGraphics, 4,12, false,true);
        Models.bombBackgroundVertices.setVertices(GameModels.bombBackgroundVertices,0,16);
        Models.bombBackgroundVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load bomb explosive model===============================================
        Models.bombExplosiveVertices = new Vertices(glGraphics, 4,12, false,true);
        Models.bombExplosiveVertices.setVertices(GameModels.bombExplosiveVertices,0,16);
        Models.bombExplosiveVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        Models.bombFragmentsAtlasVertices = new Vertices(glGraphics,4,12,false,true);
        BombModels bombModels = new BombModels();
        bombModels.addInArrayBombFragmentVertices();
        Models.bombFragmentsAtlasVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);
        //==============================================
    }
    public void loadGameTextures(Game game){

        Textures.gameBackgroundTexture = new Texture((GLGame) game, "background.png");

        Textures.crashTexture = new Texture((GLGame) game, "crash.png");

        Textures.bombTexture = new Texture((GLGame) game, "gameScreen/bombTextures/bomb.png");
        Textures.bombActivateTexture = new Texture((GLGame) game, "gameScreen/bombTextures/bomb_activated.png");
        Textures.bombNotActivateTexture = new Texture((GLGame) game, "gameScreen/bombTextures/bomb_not_activated.png");
        Textures.bombExplosiveTexture = new Texture((GLGame) game, "gameScreen/bombTextures/bomb_explosive.png");
        Textures.bombFragmentsAtlasTexture = new Texture((GLGame) game, "gameScreen/bombTextures/bomb_fragments_atlas.png");

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
    private void loadMainMenuModels(GLGraphics glGraphics){

        //load open space package levels=================================================
        Models.openSpaceLevelsVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.openSpaceLevelsVertices.setVertices(MainMenuModels.vertices512x512, 0, 16);
        Models.openSpaceLevelsVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load open space package levels=================================================
        Models.moonLevelsVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.moonLevelsVertices.setVertices(MainMenuModels.vertices512x512, 0, 16);
        Models.moonLevelsVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load choice number level frame=================================================
        Models.choiceNumberLevelFrameVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.choiceNumberLevelFrameVertices.setVertices(MainMenuModels.vertices512x512, 0, 16);
        Models.choiceNumberLevelFrameVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load numeral font================================================
        Models.numeralFontVertices = new Vertices(glGraphics,4,12,false,true);
        //вершины загружаем при отрисовке, криво да, но как умею
        //записываем в массив вершины цифр шрифта
        ChoiceLevelModels choiceLevelModels = new ChoiceLevelModels();
        choiceLevelModels.addInArrayNumeralFontVertices();

        Models.numeralFontVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load button sound=================================================
        Models.buttonSoundVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.buttonSoundVertices.setVertices(MainMenuModels.buttonSoundVertices, 0, 16);
        Models.buttonSoundVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

    }
    public void loadMainMenuTextures(Game game){

        Textures.mainMenuBackgroundTexture = new Texture((GLGame) game, "mainMenuScreen/background.png");

        Textures.openSpaceLevelsTexture = new Texture((GLGame) game, "mainMenuScreen/rocket_levels.png");
        Textures.moonLevelsTexture = new Texture((GLGame) game, "mainMenuScreen/rocket_levels.png");

        Textures.soundOnTexture = new Texture((GLGame) game, "sound_on.png");
        Textures.soundOffTexture = new Texture((GLGame) game, "sound_off.png");

        Textures.choiceLevelBackgroundTexture = new Texture((GLGame) game, "mainMenuScreen/choiceLevelScreen/choice_level_background.png");
        Textures.choiceLevelFrameTexture = new Texture((GLGame) game, "mainMenuScreen/choiceLevelScreen/choice_level_frame.png");
        Textures.choiceLevelNumberTexture = new Texture((GLGame) game, "mainMenuScreen/choiceLevelScreen/choice_number_level_frame.png");
        Textures.numeralFontTexture = new Texture(((GLGame)game), "font/numeral_font.png");
    }

    //=============================================
    private void loadLevelClearModels(GLGraphics glGraphics){

        //load level clear star model=================================================
        Models.levelClearStarVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.levelClearStarVertices.setVertices(LevelClearModels.levelClearStarVertices, 0, 16);
        Models.levelClearStarVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load level clear crash message model=================================================
        Models.levelClearCrashMessageVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.levelClearCrashMessageVertices.setVertices(LevelClearModels.levelClearCrashMessageVertices, 0, 16);
        Models.levelClearCrashMessageVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load level clear background model=================================================
        Models.backgroundVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.backgroundVertices.setVertices(GameModels.levelClearBackgroundVertices, 0, 16);
        Models.backgroundVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

        //load level clear rocket model=====================================================
        Models.levelClearRocketVertices = new Vertices(glGraphics, 4,12,false,true);
        Models.levelClearRocketVertices.setVertices(GameModels.levelClearRocketVertices, 0, 16);
        Models.levelClearRocketVertices.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);

    }
    public void loadLevelClearTextures(Game game){

        Textures.levelClearCrashTexture = new Texture((GLGame) game, "level clear screen/crash.png");
        Textures.levelClearStarTexture = new Texture((GLGame) game, "level clear screen/star.png");
        Textures.levelClearFlyInInfinityTexture = new Texture((GLGame) game, "level clear screen/fly_in_infinity.png");
        Textures.levelClearFuelOutTexture = new Texture((GLGame) game, "level clear screen/fuel_out.png");


        Textures.levelClearBackgroundTexture = new Texture((GLGame) game, "level_clear_background.png");
        Textures.levelClearRocketTexture = new Texture((GLGame) game, "level_clear_rocket.png");
    }
}
