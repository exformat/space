package io.exformat.space.model.models.modelsFHD;


import java.util.ArrayList;

public class ChoiceLevelModels {

    //1 горизонталь 2 вертикаль от центра изображения

    public static ArrayList<float[]> arrayNumeralFontVertices = new ArrayList<>();

    private float[] oneNumeralFontVertices = {

            -50f, -100f,  0.0f, 1.0f,
             50f, -100f,  0.099f, 1.0f,
             50f,  100f,  0.099f, 0.0f,
            -50f,  100f,  0.0f, 0.0f
    };

    private float[] twoNumeralFontVertices = {

            -50f, -100f,  0.099f, 1.0f,
             50f, -100f,  0.198f, 1.0f,
             50f,  100f,  0.198f, 0.0f,
            -50f,  100f,  0.099f, 0.0f
    };

    private float[] threeNumeralFontVertices = {

            -50f, -100f,  0.198f, 1.0f,
             50f, -100f,  0.295f, 1.0f,
             50f,  100f,  0.295f, 0.0f,
            -50f,  100f,  0.198f, 0.0f
    };

    private float[] fourNumeralFontVertices = {

            -50f, -100f,  0.295f, 1.0f,
             50f, -100f,  0.391f, 1.0f,
             50f,  100f,  0.391f, 0.0f,
            -50f,  100f,  0.295f, 0.0f
    };

    private float[] fiveNumeralFontVertices = {

            -50f, -100f,  0.391f, 1.0f,
             50f, -100f,  0.489f, 1.0f,
             50f,  100f,  0.489f, 0.0f,
            -50f,  100f,  0.391f, 0.0f
    };

    private float[] sixNumeralFontVertices = {

            -50f, -100f,  0.489f, 1.0f,
             50f, -100f,  0.586f, 1.0f,
             50f,  100f,  0.586f, 0.0f,
            -50f,  100f,  0.489f, 0.0f
    };

    private float[] sevenNumeralFontVertices = {

            -50f, -100f,  0.586f, 1.0f,
             50f, -100f,  0.684f, 1.0f,
             50f,  100f,  0.684f, 0.0f,
            -50f,  100f,  0.586f, 0.0f
    };

    private float[] eightNumeralFontVertices = {

            -50f, -100f,  0.684f, 1.0f,
             50f, -100f,  0.783f, 1.0f,
             50f,  100f,  0.783f, 0.0f,
            -50f,  100f,  0.684f, 0.0f
    };

    private float[] nineNumeralFontVertices = {

            -50f, -100f,  0.783f, 1.0f,
             50f, -100f,  0.88f,  1.0f,
             50f,  100f,  0.88f,  0.0f,
            -50f,  100f,  0.783f, 0.0f
    };

    private float[] zeroNumeralFontVertices = {

            -50f, -100f,  0.88f,  1.0f,
             50f, -100f,  0.978f, 1.0f,
             50f,  100f,  0.978f, 0.0f,
            -50f,  100f,  0.88f,  0.0f
    };

    public void addInArrayNumeralFontVertices(){

        arrayNumeralFontVertices.add(zeroNumeralFontVertices);
        arrayNumeralFontVertices.add(oneNumeralFontVertices);
        arrayNumeralFontVertices.add(twoNumeralFontVertices);
        arrayNumeralFontVertices.add(threeNumeralFontVertices);
        arrayNumeralFontVertices.add(fourNumeralFontVertices);
        arrayNumeralFontVertices.add(fiveNumeralFontVertices);
        arrayNumeralFontVertices.add(sixNumeralFontVertices);
        arrayNumeralFontVertices.add(sevenNumeralFontVertices);
        arrayNumeralFontVertices.add(eightNumeralFontVertices);
        arrayNumeralFontVertices.add(nineNumeralFontVertices);
    }
}
