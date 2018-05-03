package io.exformat.space.framework.game.objects;

public class VectorXYZ {

    private float x;
    private float y;
    private float z;

    private float velocityX;
    private float velocityY;
    private float velocityZ;

    //==========================================================================================
    public VectorXYZ() {
    }

    public VectorXYZ(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public VectorXYZ(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public VectorXYZ(float x, float y, float z,
                     float velocityX,
                     float velocityY,
                     float velocityZ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    //==========================================================================================
    public void defaultInitialise(){

        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.velocityX = 0;
        this.velocityY = 0;
        this.velocityZ = 0;
    }

    //==========================================================================================
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public float getVelocityZ() {
        return velocityZ;
    }

    public void setVelocityZ(float velocityZ) {
        this.velocityZ = velocityZ;
    }
}
