package io.exformat.space.spase;


import java.util.ArrayList;

import io.exformat.space.model.FlyObject;
import io.exformat.space.model.MassObject;
import io.exformat.space.model.StarCoin;
import io.exformat.space.spase.settings.SettingsModels;

public class Levels {


    private static ArrayList<MassObject> massObjects;

    private static ArrayList<StarCoin> starCoins;

    private static FlyObject flyObject;

    private static int thisLevel = 0;

    public static int MAX_LEVEL = 1;

    private static float finishX;
    private static float finishY;

    private float star1X;
    private float star1Y;
    private float star2X;
    private float star2Y;
    private float star3X;
    private float star3Y;

    public void choiceLevel(int levelNumber){

        switch (levelNumber){

            case 0:{

                thisLevel = levelNumber;
                level0();
                break;
            }
            case 1:{

                thisLevel = levelNumber;
                level1();
                break;
            }
        }
    }

    //getters=====================================================================
    public static ArrayList<MassObject> getMassObjects() {
        return massObjects;
    }
    public static ArrayList<StarCoin> getStarCoins() {
        return starCoins;
    }

    public static FlyObject getFlyObject() {
        return flyObject;
    }

    public static float getFinishX() {
        return finishX;
    }
    public static float getFinishY() {
        return finishY;
    }

    public static int getThisLevel() {
        return thisLevel;
    }

    //levels=====================================================================
    private void level0(){

        star1X = 650 * SettingsModels.scaleX;
        star1Y = 540 * SettingsModels.scaleX;
        star2X = 950 * SettingsModels.scaleX;
        star2Y = 540 * SettingsModels.scaleX;
        star3X = 1250 * SettingsModels.scaleX;
        star3Y = 540 * SettingsModels.scaleX;

        finishX = 1720 * SettingsModels.scaleX;
        finishY = 540 * SettingsModels.scaleX;

        flyObject = new FlyObject();
        flyObject.setX(300 * SettingsModels.scaleX);
        flyObject.setY(540 * SettingsModels.scaleX);

        massObjects = new ArrayList<>();
        massObjects.add(new MassObject(
                10000 * SettingsModels.scaleX,
                10000 * SettingsModels.scaleX,
                0,
                Math.round(20 * SettingsModels.scaleX),
                30));


        starCoins = new ArrayList<>();
        starCoins.add(new StarCoin(star1X, star1Y));
        starCoins.add(new StarCoin(star2X, star2Y));
        starCoins.add(new StarCoin(star3X, star3Y));
    }

    private void level1(){

        star1X = 540 * SettingsModels.scaleX;
        star1Y = 640 * SettingsModels.scaleX;
        star2X = 360 * SettingsModels.scaleX;
        star2Y = 960 * SettingsModels.scaleX;
        star3X = 1344 * SettingsModels.scaleX;
        star3Y = 540 * SettingsModels.scaleX;

        finishX = 300 * SettingsModels.scaleX;
        finishY = 540 * SettingsModels.scaleX;

        flyObject = new FlyObject();
        flyObject.setX(100 * SettingsModels.scaleX);
        flyObject.setY(100 * SettingsModels.scaleX);


        massObjects = new ArrayList<>();
        massObjects.add(new MassObject(
                960 * SettingsModels.scaleX,
                540 * SettingsModels.scaleX,
                0,
                Math.round(200000 * SettingsModels.scaleX),
                30));


        starCoins = new ArrayList<>();
        starCoins.add(new StarCoin(star1X, star1Y));
        starCoins.add(new StarCoin(star2X, star2Y));
        starCoins.add(new StarCoin(star3X, star3Y));
    }
}
