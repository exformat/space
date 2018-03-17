package io.exformat.space.model;

public class Star {

    private static final int WHITE_DWARF = 1;
    private int type,x,y,z,radius;

    public Star(int x, int y, int z, int raius, int type){
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = raius;
        this.type = type;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public static int getWhiteDwarf() {
        return WHITE_DWARF;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
