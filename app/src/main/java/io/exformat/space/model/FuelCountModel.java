package io.exformat.space.model;


import io.exformat.space.spase.Assets;
import io.exformat.space.spase.settings.SettingsModels;

public class FuelCountModel {

    private int y = Assets.displayHeight / 2;

    private int x = Assets.displayWidth / 10 / 2;

    private float heightFuelCountModel = SettingsModels.fuelCountModelHeightTHIS;

    private void loadFuelCountModel(){

        if (heightFuelCountModel >= SettingsModels.fuelCountModelHeightMin) {

            //load count fuel model=================================================
            Models.fuelCountVertices.setVertices(new float[]{
                    -54 + x, -370 + y, 0, 1,
                     54 + x, -370 + y, 1, 1,
                     54 + x, heightFuelCountModel, 1, 0,
                    -54 + x, heightFuelCountModel, 0, 0}, 0, 16);

        }
    }

    public void reloadModel(float heightModel){

        setHeightFuelCountModel(heightModel);
        loadFuelCountModel();
    }

    public void setHeightFuelCountModel(float heightFuelCountModel) {
        this.heightFuelCountModel = heightFuelCountModel;
    }

    public float getHeightFuelCountModel() {
        return heightFuelCountModel;
    }
}
