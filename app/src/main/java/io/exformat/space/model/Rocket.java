package io.exformat.space.model;

import io.exformat.space.framework.game.objects.FlyObject;

public class Rocket extends FlyObject {

    private int healthPoints;

    private float dryMass;

    private float fuelMass;
    private float fuelOut;

    private float powerTrust;

    private boolean trust;

    //==========================================================================================
    public Rocket() {
    }

    public Rocket(float x, float y, float powerTrust){

        defaultInitialise();
        setX(x);
        setY(y);
        this.powerTrust = powerTrust;
    }

    public Rocket(float x, float y){

        defaultInitialise();
        setX(x);
        setY(y);
    }

    public Rocket(int healthPoints, float dryMass, float fuelMass, float fuelOut, float powerTrust) {

        this.healthPoints = healthPoints;
        this.dryMass = dryMass;
        this.fuelMass = fuelMass;
        this.fuelOut = fuelOut;
        this.powerTrust = powerTrust;
    }

    //==========================================================================================
    @Override
    public void defaultInitialise() {

        super.defaultInitialise();

        this.healthPoints = 100;
        this.dryMass = 10;
        this.fuelMass = 100;
        this.fuelOut = 0.1f;
        this.powerTrust = 100;
    }

    //==========================================================================================

    @Override
    public float getMass() {
        return super.getMass() + this.fuelMass;
    }

    public boolean isTrust() {
        return trust;
    }

    public void setTrust(boolean trust) {
        this.trust = trust;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public float getDryMass() {
        return dryMass;
    }

    public void setDryMass(float dryMass) {
        this.dryMass = dryMass;
    }

    public float getFuelMass() {
        return fuelMass;
    }

    public void setFuelMass(float fuelMass) {
        this.fuelMass = fuelMass;
    }

    public float getFuelOut() {
        return fuelOut;
    }

    public void setFuelOut(float fuelOut) {
        this.fuelOut = fuelOut;
    }

    public float getPowerTrust() {
        return powerTrust;
    }

    public void setPowerTrust(float powerTrust) {
        this.powerTrust = powerTrust;
    }
}
