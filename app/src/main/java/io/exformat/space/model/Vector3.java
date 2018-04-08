package io.exformat.space.model;


public class Vector3 {

    private double x;
    private double y;
    private double z;

    private double xV;
    private double yV;
    private double zV;

    public Vector3() {
    }

    public Vector3(double x, double y, double z, double xV, double yV, double zV) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xV = xV;
        this.yV = yV;
        this.zV = zV;
    }

    public double getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public double getxV() {
        return xV;
    }

    public void setxV(float xV) {
        this.xV = xV;
    }

    public double getyV() {
        return yV;
    }

    public void setyV(float yV) {
        this.yV = yV;
    }

    public double getzV() {
        return zV;
    }

    public void setzV(float zV) {
        this.zV = zV;
    }
}
