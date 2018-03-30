package io.exformat.space.spase;


import java.util.ArrayList;

import io.exformat.space.model.FlyObject;
import io.exformat.space.model.MassObject;
import io.exformat.space.model.StarCoin;
import io.exformat.space.spase.settings.SettingsModels;

public class Levels {


    private static ArrayList<MassObject> massObjects;

    private static ArrayList<FlyObject> bombs;
    private static ArrayList<FlyObject> asteroids;

    private static ArrayList<StarCoin> starCoins;

    private static FlyObject flyObject;

    private static int thisLevel = 0;

    public static int MAX_LEVEL = 4;

    public static int starCoinUpCount = 0;

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
            case 2:{
                thisLevel = levelNumber;
                level2();
                break;
            }
            case 3:{
                thisLevel = levelNumber;
                level3();
                break;
            }
            case 4:{
                thisLevel = levelNumber;
                level4();
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
    public static ArrayList<FlyObject> getBombs() {
        return bombs;
    }
    public static ArrayList<FlyObject> getAsteroids() {
        return asteroids;
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


        //расставляем звёзды
        starCoins = new ArrayList<>();
        starCoins.add(new StarCoin(star1X, star1Y));
        starCoins.add(new StarCoin(star2X, star2Y));
        starCoins.add(new StarCoin(star3X, star3Y));

        bombs = new ArrayList<>();
    }

    private void level1(){

        star1X = 650 * SettingsModels.scaleX;
        star1Y = 540 * SettingsModels.scaleX;
        star2X = 950 * SettingsModels.scaleX;
        star2Y = 850 * SettingsModels.scaleX;
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

        //расставляем бомбы
        bombs = new ArrayList<>();
        bombs.add(new FlyObject(960,540,0,0,0,0, 50));
    }

    private void level2(){

        star1X = 500 * SettingsModels.scaleX;
        star1Y = 540 * SettingsModels.scaleX;

        star2X = 960 * SettingsModels.scaleX;
        star2Y = 540 * SettingsModels.scaleX;

        star3X = 1420 * SettingsModels.scaleX;
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

        //расставляем бомбы
        bombs = new ArrayList<>();
        bombs.add(new FlyObject(730,540,0,0,0,0, 50));
        bombs.add(new FlyObject(1190,540,0,0,0,0, 50));
    }

    private void level3(){

        star1X = 500 * SettingsModels.scaleX;
        star1Y = 540 * SettingsModels.scaleX;

        star2X = 960 * SettingsModels.scaleX;
        star2Y = 540 * SettingsModels.scaleX;

        star3X = 1720 * SettingsModels.scaleX;
        star3Y = 540 * SettingsModels.scaleX;

        finishX = 1720 * SettingsModels.scaleX;
        finishY = 200 * SettingsModels.scaleX;

        flyObject = new FlyObject();
        flyObject.setX(200 * SettingsModels.scaleX);
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

        //расставляем бомбы
        bombs = new ArrayList<>();
        bombs.add(new FlyObject(680,740,0,0,0,0, 50));
        bombs.add(new FlyObject(680,340,0,0,0,0, 50));
        bombs.add(new FlyObject(1140,740,0,0,0,0, 50));
        bombs.add(new FlyObject(1140,340,0,0,0,0, 50));
        bombs.add(new FlyObject(1500,540,0,0,0,0, 50));


        //расставляем астероиды
        asteroids = new ArrayList<>();
    }

    private void level4(){

        star1X = 225 * SettingsModels.scaleX;
        star1Y = 170 * SettingsModels.scaleX;

        star2X = 575 * SettingsModels.scaleX;
        star2Y = 170 * SettingsModels.scaleX;

        star3X = 1520 * SettingsModels.scaleX;
        star3Y = 540 * SettingsModels.scaleX;

        finishX = 1720 * SettingsModels.scaleX;
        finishY = 540 * SettingsModels.scaleX;

        flyObject = new FlyObject();
        flyObject.setX(200 * SettingsModels.scaleX);
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

        //расставляем бомбы
        bombs = new ArrayList<>();
        bombs.add(new FlyObject(1160,540,0,0,0,0, 50));
        bombs.add(new FlyObject(100,340,0,0,0,0, 50));
        bombs.add(new FlyObject(450,340,0,0,0,0, 50));
        bombs.add(new FlyObject(800,340,0,0,0,0, 50));


        //расставляем астероиды
        asteroids = new ArrayList<>();
    }

}
