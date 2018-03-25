package io.exformat.space.model;


import io.exformat.space.spase.settings.SettingsModels;

public class LevelClearRocket {

    private double x = -SettingsModels.displayHeight;
    private double y = SettingsModels.displayHeight / 3;

    private double vY = 10;

    public LevelClearRocket(){

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getvX() {
        return vY;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }
}
