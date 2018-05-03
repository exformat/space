package io.exformat.space.model;


import java.util.ArrayList;

import io.exformat.space.framework.game.objects.VectorXYZ;
import io.exformat.space.framework.game.objects.FlyObject;

public class Level {

    private VectorXYZ vector = new VectorXYZ();

    private ArrayList<FlyObject> massObjects = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private ArrayList<FlyObject> asteroids = new ArrayList<>();
    private ArrayList<VectorXYZ>  starCoins = new ArrayList<>();

    private Rocket rocket = new Rocket();

    private VectorXYZ finish;

    private int levelNumber = 0;

    public Level() {
    }


//======================================================================================

    public VectorXYZ getVector() {
        return vector;
    }

    public VectorXYZ getFinish() {
        return finish;
    }

    public void setFinish(VectorXYZ finish) {
        this.finish = finish;
    }

    public void setVector(VectorXYZ vector) {
        this.vector = vector;
    }

    public ArrayList<FlyObject> getMassObjects() {
        return massObjects;
    }

    public void setMassObjects(ArrayList<FlyObject> massObjects) {
        this.massObjects = massObjects;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public void setBombs(ArrayList<Bomb> bombs) {
        this.bombs = bombs;
    }

    public ArrayList<FlyObject> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(ArrayList<FlyObject> asteroids) {
        this.asteroids = asteroids;
    }

    public ArrayList<VectorXYZ> getStarCoins() {
        return starCoins;
    }

    public void setStarCoins(ArrayList<VectorXYZ> starCoins) {
        this.starCoins = starCoins;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}
