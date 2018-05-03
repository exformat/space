package io.exformat.space.model;

import java.util.ArrayList;

import io.exformat.space.framework.game.objects.VectorXYZ;

public class Bomb extends Rocket {


    private boolean bombActivated = false;
    private boolean bombExplosive = false;
    private int drawBombExplosiveTick;
    private ArrayList<VectorXYZ> bombFragments = new ArrayList<>();

    public Bomb() {
    }

    public Bomb(float x, float y, float powerTrust) {
        super(x, y, powerTrust);
    }

    //==========================================================================================
    public boolean isBombActivated() {
        return bombActivated;
    }

    public void setBombActivated(boolean bombActivated) {
        this.bombActivated = bombActivated;
    }

    public boolean isBombExplosive() {
        return bombExplosive;
    }

    public void setBombExplosive(boolean bombExplosive) {
        this.bombExplosive = bombExplosive;
    }

    public int getDrawBombExplosiveTick() {
        return drawBombExplosiveTick;
    }

    public void setDrawBombExplosiveTick(int drawBombExplosiveTick) {
        this.drawBombExplosiveTick = drawBombExplosiveTick;
    }

    public ArrayList<VectorXYZ> getBombFragments() {
        return bombFragments;
    }

    public void setBombFragments(ArrayList<VectorXYZ> bombFragments) {
        this.bombFragments = bombFragments;
    }
}
