package io.exformat.space.model;

public class MassObject {

    private double x,y,z;
    private long mass;
    private double radius;

    public MassObject(double x, double y, double z, long mass, double radius) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.mass = mass;
        this.radius = radius;
    }

    public MassObject() {
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public long getMass() {
        return mass;
    }

    public void setMass(long mass) {
        this.mass = mass;
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
