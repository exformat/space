package io.exformat.space.model;


import java.util.ArrayList;

public class Level {

    private Vector3 vector = new Vector3();

    private float translateX;
    private float translateY;

    private ArrayList<MassObject> massObjects = new ArrayList<>();
    private ArrayList<FlyObject>  bombs = new ArrayList<>();
    private ArrayList<FlyObject>  asteroids = new ArrayList<>();
    private ArrayList<StarCoin>   starCoins = new ArrayList<>();

    private FlyObject flyObject = new FlyObject();

    private float finishX = 0;
    private float finishY = 0;

    private int levelNumber = 0;


    public Level() {
    }


//======================================================================================
    public Vector3 getVector() {
        return vector;
    }

    public void setVector(Vector3 vector) {
        this.vector = vector;
    }

    public float getTranslateX() {
        return translateX;
    }

    public void setTranslateX(float translateX) {
        this.translateX = translateX;
    }

    public float getTranslateY() {
        return translateY;
    }

    public void setTranslateY(float translateY) {
        this.translateY = translateY;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public ArrayList<MassObject> getMassObjects() {
        return massObjects;
    }

    public void setMassObjects(ArrayList<MassObject> massObjects) {
        this.massObjects = massObjects;
    }

    public ArrayList<FlyObject> getBombs() {
        return bombs;
    }

    public void setBombs(ArrayList<FlyObject> bombs) {
        this.bombs = bombs;
    }

    public ArrayList<FlyObject> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(ArrayList<FlyObject> asteroids) {
        this.asteroids = asteroids;
    }

    public ArrayList<StarCoin> getStarCoins() {
        return starCoins;
    }

    public void setStarCoins(ArrayList<StarCoin> starCoins) {
        this.starCoins = starCoins;
    }

    public FlyObject getFlyObject() {
        return flyObject;
    }

    public void setFlyObject(FlyObject flyObject) {
        this.flyObject = flyObject;
    }

    public float getFinishX() {
        return finishX;
    }

    public void setFinishX(float finishX) {
        this.finishX = finishX;
    }

    public float getFinishY() {
        return finishY;
    }

    public void setFinishY(float finishY) {
        this.finishY = finishY;
    }
}
