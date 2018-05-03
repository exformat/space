package io.exformat.space.spase;



import java.util.ArrayList;

import io.exformat.space.framework.game.objects.FlyObject;
import io.exformat.space.framework.game.objects.VectorXYZ;
import io.exformat.space.model.Bomb;
import io.exformat.space.model.PackageLevel;
import io.exformat.space.model.Level;
import io.exformat.space.model.Rocket;
import io.exformat.space.model.Textures;

public class Levels {

    public static int starCoinUpCount = 0;
    public static boolean crash;
    public static boolean fuelOut;
    public static boolean inInfinity;

    public static Level level;
    public static ArrayList<Level> levels = new ArrayList<>();

    //===========================================================================
    private ArrayList<PackageLevel> packageLevels = new ArrayList<>();
    public static PackageLevel packageLevel;

    public void initialPackageLevels(){

        packageLevels.add(new PackageLevel(initialisationOpenSpaceLevels(), Textures.openSpaceLevelsTexture));
        packageLevels.add(new PackageLevel(initialisationMoonLevels(),Textures.moonLevelsTexture));
    }

    private ArrayList<Level> initialisationOpenSpaceLevels() {

        levels = new ArrayList<>();

        levels.add(constructorOpenSpaceLevel0());
        levels.add(constructorOpenSpaceLevel1());
        levels.add(constructorOpenSpaceLevel2());
        levels.add(constructorOpenSpaceLevel3());
        levels.add(constructorOpenSpaceLevel4());

        return levels;
    }
    private ArrayList<Level> initialisationMoonLevels() {

        levels = new ArrayList<>();

        levels.add(constructorMoonLevel0());
        levels.add(constructorMoonLevel1());

        return levels;
    }

    public ArrayList<PackageLevel> getPackageLevels() {
        return packageLevels;
    }

    //============================================================================

    private Level constructorOpenSpaceLevel0() {

        Level level = new Level();

        ArrayList<VectorXYZ> starCoins = new ArrayList<>();

        //расставляем звёзды
        starCoins.add(new VectorXYZ(650, 540));
        starCoins.add(new VectorXYZ(950, 540));
        starCoins.add(new VectorXYZ(1250,540));

        level.setBombs(new ArrayList<Bomb>());
        level.setRocket(new Rocket(300,540));
        level.setFinish(new VectorXYZ(1720,540));
        level.setStarCoins(starCoins);

        level.setLevelNumber(0);

        return level;
    }
    private Level constructorOpenSpaceLevel1() {

        Level level = new Level();

        ArrayList<VectorXYZ> starCoins = new ArrayList<>();
        ArrayList<Bomb> bombs = new ArrayList<>();

        //расставляем звёзды
        starCoins.add(new VectorXYZ(650, 540));
        starCoins.add(new VectorXYZ(950, 850));
        starCoins.add(new VectorXYZ(1250, 540));

        bombs.add(new Bomb(960,540,50));

        level.setRocket(new Rocket(300,540));
        level.setFinish(new VectorXYZ(1720,540));
        level.setStarCoins(starCoins);
        level.setBombs(bombs);

        level.setLevelNumber(1);

        return level;
    }
    private Level constructorOpenSpaceLevel2() {

        Level level = new Level();

        ArrayList<VectorXYZ> starCoins = new ArrayList<>();
        ArrayList<Bomb> bombs = new ArrayList<>();

        //расставляем звёзды
        starCoins.add(new VectorXYZ(500, 540));
        starCoins.add(new VectorXYZ(960, 540));
        starCoins.add(new VectorXYZ(1420,540));

        bombs.add(new Bomb(730,540,50));
        bombs.add(new Bomb(1190,540,50));

        level.setRocket(new Rocket(300,540));
        level.setFinish(new VectorXYZ(1720,540));
        level.setStarCoins(starCoins);
        level.setBombs(bombs);

        level.setLevelNumber(2);

        return level;
    }
    private Level constructorOpenSpaceLevel3() {

        Level level = new Level();

        ArrayList<VectorXYZ> starCoins = new ArrayList<>();
        ArrayList<Bomb> bombs = new ArrayList<>();

        //расставляем звёзды
        starCoins.add(new VectorXYZ(500, 540));
        starCoins.add(new VectorXYZ(960, 540));
        starCoins.add(new VectorXYZ(1720,540));

        bombs.add(new Bomb(680, 740, 50));
        bombs.add(new Bomb(680, 340, 50));
        bombs.add(new Bomb(1140, 740, 50));
        bombs.add(new Bomb(1140, 340, 50));
        bombs.add(new Bomb(1500, 540, 50));

        level.setRocket(new Rocket(200,540));
        level.setFinish(new VectorXYZ(1720,200));
        level.setStarCoins(starCoins);
        level.setBombs(bombs);
        level = new Level();

        level.setLevelNumber(3);

        return level;
    }
    private Level constructorOpenSpaceLevel4() {

        Level level = new Level();

        ArrayList<VectorXYZ> starCoins = new ArrayList<>();
        ArrayList<Bomb> bombs = new ArrayList<>();

        //расставляем звёзды
        starCoins.add(new VectorXYZ(255, 170));
        starCoins.add(new VectorXYZ(575, 170));
        starCoins.add(new VectorXYZ(1520,540));

        bombs.add(new Bomb(1160, 540, 50));
        bombs.add(new Bomb(100, 340, 50));
        bombs.add(new Bomb(450, 340, 50));
        bombs.add(new Bomb(800, 340, 50));

        level.setRocket(new Rocket(200,540));
        level.setFinish(new VectorXYZ(1720,540));
        level.setStarCoins(starCoins);
        level.setBombs(bombs);

        level.setLevelNumber(4);

        return level;
    }

    private Level constructorMoonLevel0() {

        Level level = new Level();

        ArrayList<VectorXYZ> starCoins = new ArrayList<>();
        ArrayList<FlyObject> massObjects = new ArrayList<>();
        ArrayList<Bomb> bombs = new ArrayList<>();

        //расставляем звёзды
        starCoins.add(new VectorXYZ(225, 170));
        starCoins.add(new VectorXYZ(575, 170));
        starCoins.add(new VectorXYZ(1520, 540));

        massObjects.add(new FlyObject(
                960,
                -173700000,
                Math.round(7.3477 * 10E22),
                173700000));

        level.setRocket(new Rocket(200, 540, 500));
        level.setMassObjects(massObjects);
        level.setFinish(new VectorXYZ(1720,540));
        level.setStarCoins(starCoins);

        level.setLevelNumber(0);

        return level;
    }
    private Level constructorMoonLevel1() {

        Level level = new Level();

        ArrayList<VectorXYZ> starCoins = new ArrayList<>();
        ArrayList<FlyObject> massObjects = new ArrayList<>();
        ArrayList<Bomb> bombs = new ArrayList<>();

        massObjects.add(new FlyObject(
                960,
                -173700000,
                Math.round(7.3477 * 10E22),
                30));

        //расставляем звёзды
        starCoins.add(new VectorXYZ(225, 170));
        starCoins.add(new VectorXYZ(575, 170));
        starCoins.add(new VectorXYZ(1520, 540));

        level.setRocket(new Rocket(200, 540, 500));
        level.setMassObjects(massObjects);
        level.setFinish(new VectorXYZ(1720, 540));
        level.setStarCoins(starCoins);
        level.setBombs(bombs);

        level.setLevelNumber(1);

        return level;
    }

    public Level getLevel(int indexLevel) {

        return levels.get(indexLevel);
    }
}
