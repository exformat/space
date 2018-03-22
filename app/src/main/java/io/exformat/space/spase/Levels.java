package io.exformat.space.spase;


import java.util.ArrayList;

import io.exformat.space.model.MassObject;

public class Levels {


    private static ArrayList<MassObject> massObjects = new ArrayList<>();
    private static float finishX;
    private static float finishY;

    public void choiceLevel(int levelNumber){

        switch (levelNumber){

            case 0:{

                level0();
                break;
            }
            case 1:{

                break;
            }
        }
    }

    public static ArrayList<MassObject> getMassObjects() {
        return massObjects;
    }

    public static float getFinishX() {
        return finishX;
    }
    public static float getFinishY() {
        return finishY;
    }


    private void level0(){

        massObjects.add(new MassObject(
                Assets.displayWidth / 2,
                Assets.displayHeight / 2,
                0,
                2000000,
                54));

        finishX = Assets.displayWidth - ((Assets.displayWidth / 10 / 2) * 1.6f);
        finishY = Assets.displayHeight / 10 / 2;
    }
}
