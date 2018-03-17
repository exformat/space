package io.exformat.space.model;

/**
 * Created by mamy on 08.03.18.
 */

public class Rocket {

    private static double DIRECTION = 0;
    private static double x,y,z,
                          velocityX,
                          velocityY,
                          velocityZ,
                          dryMass,fuelMass,powerTrust,fuelOut;

    public Rocket() {
    }

    public Rocket(double x, double y, double z,
                  double velocityX,
                  double velocityY,
                  double velocityZ,
                  double dryMass, double fuelMass, double powerTrust, double fuelOut) {


    }

    public static double getDIRECTION() {
        return DIRECTION;
    }

    public static void setDIRECTION(double DIRECTION) {
        Rocket.DIRECTION = DIRECTION;
    }

    public static double getX() {
        return x;
    }

    public static void setX(double x) {
        Rocket.x = x;
    }

    public static double getY() {
        return y;
    }

    public static void setY(double y) {
        Rocket.y = y;
    }

    public static double getZ() {
        return z;
    }

    public static void setZ(double z) {
        Rocket.z = z;
    }

    public static double getVelocityX() {
        return velocityX;
    }

    public static void setVelocityX(double velocityX) {
        Rocket.velocityX = velocityX;
    }

    public static double getVelocityY() {
        return velocityY;
    }

    public static void setVelocityY(double velocityY) {
        Rocket.velocityY = velocityY;
    }

    public static double getVelocityZ() {
        return velocityZ;
    }

    public static void setVelocityZ(double velocityZ) {
        Rocket.velocityZ = velocityZ;
    }

    public static double getDryMass() {
        return dryMass;
    }

    public static void setDryMass(double dryMass) {
        Rocket.dryMass = dryMass;
    }

    public static double getFuelMass() {
        return fuelMass;
    }

    public static void setFuelMass(double fuelMass) {
        Rocket.fuelMass = fuelMass;
    }

    public static double getPowerTrust() {
        return powerTrust;
    }

    public static void setPowerTrust(double powerTrust) {
        Rocket.powerTrust = powerTrust;
    }

    public static double getFuelOut() {
        return fuelOut;
    }

    public static void setFuelOut(double fuelOut) {
        Rocket.fuelOut = fuelOut;
    }
}
