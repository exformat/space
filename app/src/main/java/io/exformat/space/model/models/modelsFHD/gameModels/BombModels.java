package io.exformat.space.model.models.modelsFHD.gameModels;


import java.util.ArrayList;

public class BombModels {

    public static float[] bombVertices = {

            -64, -64, 0, 1,
             64, -64, 1, 1,
             64,  64, 1, 0,
            -64,  64, 0, 0
    };
    public static float[] bombExplosiveVertices = {

            -128, -128, 0, 1,
             128, -128, 1, 1,
             128,  128, 1, 0,
            -128,  128, 0, 0
    };

    public static float[] bombBackgroundVertices = {

            -128, -128, 0, 1,
             128, -128, 1, 1,
             128,  128, 1, 0,
            -128,  128, 0, 0
    };

    public static ArrayList<float[]> arrayBombFragmentVertices = new ArrayList<>();

    public void addInArrayBombFragmentVertices(){

        arrayBombFragmentVertices.add(oneBombFragmentVertices);
        arrayBombFragmentVertices.add(twoBombFragmentVertices);
        arrayBombFragmentVertices.add(threeBombFragmentVertices);
        arrayBombFragmentVertices.add(fourBombFragmentVertices);
    }

    public static float[] oneBombFragmentVertices = {

            -64, -64, 0f, 0.5f,
             64, -64, 0.5f, 0.5f,
             64,  64, 0.5f, 0,
            -64,  64, 0, 0
    };

    public static float[] twoBombFragmentVertices = {

            -64, -64, 0.5f, 0.5f,
             64, -64, 1,    0.5f,
             64,  64, 1,    0,
            -64,  64, 0.5f, 0
    };

    public static float[] threeBombFragmentVertices = {

            -64, -64, 0,    1,
             64, -64, 0.5f, 1,
             64,  64, 0.5f, 0.5f,
            -64,  64, 0,    0.5f
    };

    public static float[] fourBombFragmentVertices = {

            -64, -64, 0.5f, 1,
             64, -64, 1,    1,
             64,  64, 1,    0.5f,
            -64,  64, 0.5f, 0.5f
    };
}
