package io.exformat.space.model;

public class StarCoin {

    private float starCoinX;
    private float starCoinY;

    private double radius = 25;

    public StarCoin(float starCoinX, float starCoinY) {
        this.starCoinX = starCoinX;
        this.starCoinY = starCoinY;
    }

    public StarCoin() {
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public float getStarCoinX() {
        return starCoinX;
    }

    public void setStarCoinX(float starCoinX) {
        this.starCoinX = starCoinX;
    }

    public float getStarCoinY() {
        return starCoinY;
    }

    public void setStarCoinY(float starCoinY) {
        this.starCoinY = starCoinY;
    }
}
