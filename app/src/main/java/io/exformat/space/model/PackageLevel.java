package io.exformat.space.model;


import android.util.Log;

import java.util.ArrayList;

import io.exformat.space.framework.openGL.Texture;

public class PackageLevel {

    private Vector3 vector = new Vector3();

    //текстурку иконки пакета уровней хранить буду тут, ну а чего?))
    private Texture iconPackageLevelsTexture;

    private int starCoinUpCount = 0;
    private boolean crash;
    private boolean fuelOut;
    private boolean inInfinity;//вылет за пределы игрового поля

    private Level level;
    private ArrayList<Level> levels = new ArrayList<>();

    private int levelCount = 0;



    public PackageLevel() {
    }

    //в конструкторе инициализируем массив с уровнями
    public PackageLevel(ArrayList<Level> levels, Texture texture) {

        this.levels = levels;
        this.iconPackageLevelsTexture = texture;

        initialisationLevels(levels);
    }


//==============================================================================
    private void initialisationLevels(ArrayList<Level> levels) {

        this.levels = levels;

        calculateTranslateNumberLevelFrame();
    }

    private void calculateTranslateNumberLevelFrame() {

        int levelIconTranslateX = 510;
        int levelIconTranslateY = 590;


        for (Level level : levels) {

            Log.d("level", "" + level.getTranslateX());
            level.setVector(new Vector3((double)levelIconTranslateX,(double)levelIconTranslateY));

            if (levelCount < 7) {

                levelCount++;
            } else {

                levelCount = 0;
            }

            if (levelCount < 4) {

                levelIconTranslateX += 300;
                levelIconTranslateY = 590;
            } else {

                if (levelCount == 4) {
                    levelIconTranslateX -= 1200;
                }
                levelIconTranslateX += 300;
                levelIconTranslateY = 255;
            }
        }
    }
//==============================================================================


    public Texture getIconPackageLevelsTexture() {
        return iconPackageLevelsTexture;
    }

    public void setIconPackageLevelsTexture(Texture iconPackageLevelsTexture) {
        this.iconPackageLevelsTexture = iconPackageLevelsTexture;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public Vector3 getVector() {
        return vector;
    }

    public void setVector(Vector3 vector) {
        this.vector = vector;
    }

    public int getStarCoinUpCount() {
        return starCoinUpCount;
    }

    public void setStarCoinUpCount(int starCoinUpCount) {
        this.starCoinUpCount = starCoinUpCount;
    }

    public boolean isCrash() {
        return crash;
    }

    public void setCrash(boolean crash) {
        this.crash = crash;
    }

    public boolean isFuelOut() {
        return fuelOut;
    }

    public void setFuelOut(boolean fuelOut) {
        this.fuelOut = fuelOut;
    }

    public boolean isInInfinity() {
        return inInfinity;
    }

    public void setInInfinity(boolean inInfinity) {
        this.inInfinity = inInfinity;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }
}
