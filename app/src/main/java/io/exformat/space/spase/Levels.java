package io.exformat.space.spase;



import java.util.ArrayList;

import io.exformat.space.model.FlyObject;
import io.exformat.space.model.PackageLevel;
import io.exformat.space.model.Level;
import io.exformat.space.model.MassObject;
import io.exformat.space.model.StarCoin;
import io.exformat.space.model.Textures;
import io.exformat.space.model.Vector3;

public class Levels {

    public static int starCoinUpCount = 0;
    public static boolean crash;
    public static boolean fuelOut;
    public static boolean inInfinity;

    public static Level level;
    public static ArrayList<Level> levels = new ArrayList<>();

    private int levelCount = 0;


    private static ArrayList<MassObject> massObjects;
    private static ArrayList<FlyObject> bombs;
    private static ArrayList<FlyObject> asteroids;
    private static ArrayList<StarCoin> starCoins;
    private static FlyObject flyObject;

    //===========================================================================
    private ArrayList<PackageLevel> packageLevels = new ArrayList<>();
    public static PackageLevel packageLevel;

    public void initialPackageLevels(){

        packageLevels.add(new PackageLevel(initialisationOpenSpaceLevels(), Textures.openSpaceLevelsTexture));
        packageLevels.add(new PackageLevel(initialisationMoonLevels(),Textures.moonLevelsTexture));
    }

    private ArrayList<Level> initialisationOpenSpaceLevels() {

        levels = new ArrayList<>();

        levels.add(addOpenSpaceLevel0());
        levels.add(addOpenSpaceLevel1());
        levels.add(addOpenSpaceLevel2());
        levels.add(addOpenSpaceLevel3());
        levels.add(addOpenSpaceLevel4());

        return levels;
    }
    private ArrayList<Level> initialisationMoonLevels() {

        levels = new ArrayList<>();

        levels.add(addMoonLevel0());
        levels.add(addMoonLevel1());

        return levels;
    }

    public ArrayList<PackageLevel> getPackageLevels() {
        return packageLevels;
    }

    //============================================================================

    private Level addOpenSpaceLevel0() {

        level = new Level();

        massObjects = new ArrayList<>();
        starCoins = new ArrayList<>();

        massObjects.add(new MassObject(
                10000,
                10000,
                0,
                Math.round(20),
                30));

        //расставляем звёзды
        starCoins.add(new StarCoin(650, 540));
        starCoins.add(new StarCoin(950, 540));
        starCoins.add(new StarCoin(1250, 540));

        level.setFlyObject(new FlyObject(300, 540, 0, 0, 0, 0, 100));
        level.setMassObjects(massObjects);
        level.setFinishX(1720);
        level.setFinishY(540);
        level.setStarCoins(starCoins);
        //level.setBombs(bombs);

        level.setLevelNumber(0);

        return level;
    }
    private Level addOpenSpaceLevel1() {

        level = new Level();

        massObjects = new ArrayList<>();
        starCoins = new ArrayList<>();
        bombs = new ArrayList<>();

        massObjects.add(new MassObject(
                10000,
                10000,
                0,
                Math.round(20),
                30));

        //расставляем звёзды
        starCoins.add(new StarCoin(650, 540));
        starCoins.add(new StarCoin(950, 850));
        starCoins.add(new StarCoin(1250, 540));

        bombs.add(new FlyObject(960, 540, 0, 0, 0, 0, 50));

        level.setFlyObject(new FlyObject(300, 540, 0, 0, 0, 0, 100));
        level.setMassObjects(massObjects);
        level.setFinishX(1720);
        level.setFinishY(540);
        level.setStarCoins(starCoins);
        level.setBombs(bombs);

        level.setLevelNumber(1);

        return level;
    }
    private Level addOpenSpaceLevel2() {

        level = new Level();

        massObjects = new ArrayList<>();
        starCoins = new ArrayList<>();
        bombs = new ArrayList<>();

        massObjects.add(new MassObject(
                10000,
                10000,
                0,
                Math.round(20),
                30));

        //расставляем звёзды
        starCoins.add(new StarCoin(500, 540));
        starCoins.add(new StarCoin(960, 540));
        starCoins.add(new StarCoin(1420, 540));

        bombs.add(new FlyObject(730, 540, 0, 0, 0, 0, 50));
        bombs.add(new FlyObject(1190, 540, 0, 0, 0, 0, 50));

        level.setFlyObject(new FlyObject(300, 540, 0, 0, 0, 0, 100));
        level.setMassObjects(massObjects);
        level.setFinishX(1720);
        level.setFinishY(540);
        level.setStarCoins(starCoins);
        level.setBombs(bombs);

        level.setLevelNumber(2);

        return level;
    }
    private Level addOpenSpaceLevel3() {

        level = new Level();

        massObjects = new ArrayList<>();
        starCoins = new ArrayList<>();
        bombs = new ArrayList<>();

        massObjects.add(new MassObject(
                10000,
                10000,
                0,
                Math.round(20),
                30));

        //расставляем звёзды
        starCoins.add(new StarCoin(500, 540));
        starCoins.add(new StarCoin(960, 540));
        starCoins.add(new StarCoin(1720, 540));

        bombs.add(new FlyObject(680, 740, 0, 0, 0, 0, 50));
        bombs.add(new FlyObject(680, 340, 0, 0, 0, 0, 50));
        bombs.add(new FlyObject(1140, 740, 0, 0, 0, 0, 50));
        bombs.add(new FlyObject(1140, 340, 0, 0, 0, 0, 50));
        bombs.add(new FlyObject(1500, 540, 0, 0, 0, 0, 50));

        level.setFlyObject(new FlyObject(200, 540, 0, 0, 0, 0, 100));
        level.setMassObjects(massObjects);
        level.setFinishX(1720);
        level.setFinishY(200);
        level.setStarCoins(starCoins);
        level.setBombs(bombs);


        level.setLevelNumber(3);

        return level;
    }
    private Level addOpenSpaceLevel4() {

        level = new Level();

        massObjects = new ArrayList<>();
        starCoins = new ArrayList<>();
        bombs = new ArrayList<>();

        massObjects.add(new MassObject(
                10000,
                10000,
                0,
                Math.round(20),
                30));

        bombs.add(new FlyObject(1160, 540, 0, 0, 0, 0, 50));
        bombs.add(new FlyObject(100, 340, 0, 0, 0, 0, 50));
        bombs.add(new FlyObject(450, 340, 0, 0, 0, 0, 50));
        bombs.add(new FlyObject(800, 340, 0, 0, 0, 0, 50));

        //расставляем звёзды
        starCoins.add(new StarCoin(225, 170));
        starCoins.add(new StarCoin(575, 170));
        starCoins.add(new StarCoin(1520, 540));

        level.setFlyObject(new FlyObject(200, 540, 0, 0, 0, 0, 100));
        level.setMassObjects(massObjects);
        level.setFinishX(1720);
        level.setFinishY(540);
        level.setStarCoins(starCoins);
        level.setBombs(bombs);

        level.setLevelNumber(4);

        return level;
    }

    private Level addMoonLevel0() {

        level = new Level();

        massObjects = new ArrayList<>();
        starCoins = new ArrayList<>();
        bombs = new ArrayList<>();

        massObjects.add(new MassObject(
                960,
                -173700000,
                0,
                Math.round(7.3477 * 10E22),
                173700000));

        //расставляем звёзды
        starCoins.add(new StarCoin(225, 170));
        starCoins.add(new StarCoin(575, 170));
        starCoins.add(new StarCoin(1520, 540));

        level.setFlyObject(new FlyObject(200, 540, 0, 0, 0, 0, 500));
        level.setMassObjects(massObjects);
        level.setFinishX(1720);
        level.setFinishY(540);
        level.setStarCoins(starCoins);
        //level.setBombs(bombs);

        level.setLevelNumber(0);

        return level;
    }
    private Level addMoonLevel1() {

        level = new Level();

        massObjects = new ArrayList<>();
        starCoins = new ArrayList<>();
        bombs = new ArrayList<>();

        massObjects.add(new MassObject(
                960,
                -173700000,
                0,
                Math.round(7.3477 * 10E22),
                30));

        //расставляем звёзды
        starCoins.add(new StarCoin(225, 170));
        starCoins.add(new StarCoin(575, 170));
        starCoins.add(new StarCoin(1520, 540));

        level.setFlyObject(new FlyObject(200, 540, 0, 0, 0, 0, 500));
        level.setMassObjects(massObjects);
        level.setFinishX(1720);
        level.setFinishY(540);
        level.setStarCoins(starCoins);
        //level.setBombs(bombs);

        level.setLevelNumber(1);

        return level;
    }






    public void oldInitialisationOpenSpaceLevels() {

        levels.add(addOpenSpaceLevel0());
        levels.add(addOpenSpaceLevel1());
        levels.add(addOpenSpaceLevel2());
        levels.add(addOpenSpaceLevel3());
        levels.add(addOpenSpaceLevel4());

        calculateTranslateNumberLevelFrame();
    }

    public Level getLevel(int indexLevel) {

        return levels.get(indexLevel);
    }

    private void calculateTranslateNumberLevelFrame() {

        int levelFrameTranslateX = 510;
        int levelFrameTranslateY = 590;

        for (Level level : levels) {

            level.setVector(new Vector3((double)levelFrameTranslateX, (double)levelFrameTranslateY));

            level.setTranslateX(levelFrameTranslateX);
            level.setTranslateY(levelFrameTranslateY);

            if (levelCount < 7) {

                levelCount++;
            } else {

                levelCount = 0;
            }

            if (levelCount < 4) {

                levelFrameTranslateX += 300;
                levelFrameTranslateY = 590;
            } else {

                if (levelCount == 4) {
                    levelFrameTranslateX -= 1200;
                }
                levelFrameTranslateX += 300;
                levelFrameTranslateY = 255;
            }
        }
    }



}
