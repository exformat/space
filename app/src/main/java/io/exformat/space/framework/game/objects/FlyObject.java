package io.exformat.space.framework.game.objects;

public class FlyObject extends VectorXYZ {

    private float mass;
    private float radius;

    private float angleDirectXY;
    private float angleDirectYZ;

    private float angleSpeedXY;
    private float angleSpeedYZ;

    private VectorXYZ oldPosition;

    public FlyObject() {
    }

    public FlyObject(float x, float y, float mass, float radius){

        defaultInitialise();

        setX(x);
        setY(y);
        this.mass = mass;
        this.radius = radius;
    }

    public FlyObject(float mass, float radius) {

        defaultInitialise();
        this.mass = mass;
        this.radius = radius;
    }

    @Override
    public void defaultInitialise() {

        super.defaultInitialise();

        this.mass = 10;
        this.radius = 10;
        this.angleDirectXY = 0;
        this.angleDirectYZ = 0;
        this.angleSpeedXY = 0;
        this.angleSpeedYZ = 0;
    }

    public VectorXYZ getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(VectorXYZ oldPosition) {
        this.oldPosition = oldPosition;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getAngleDirectXY() {
        return angleDirectXY;
    }

    public void setAngleDirectXY(float angleDirectXY) {
        this.angleDirectXY = angleDirectXY;
    }

    public float getAngleDirectYZ() {
        return angleDirectYZ;
    }

    public void setAngleDirectYZ(float angleDirectYZ) {
        this.angleDirectYZ = angleDirectYZ;
    }

    public float getAngleSpeedXY() {
        return angleSpeedXY;
    }

    public void setAngleSpeedXY(float angleSpeedXY) {
        this.angleSpeedXY = angleSpeedXY;
    }

    public float getAngleSpeedYZ() {
        return angleSpeedYZ;
    }

    public void setAngleSpeedYZ(float angleSpeedYZ) {
        this.angleSpeedYZ = angleSpeedYZ;
    }
}
