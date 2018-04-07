package io.exformat.space.spase;



import io.exformat.space.spase.settings.SettingsModels;

public class Settings {

    public void calculateModels() {

        SettingsModels.displayHeight = Assets.displayHeight;
        SettingsModels.displayWidth = Assets.displayWidth;


        SettingsModels.displayHeight_05 = SettingsModels.displayHeight / 2f;
        SettingsModels.displayWidth_05 = SettingsModels.displayWidth / 2f;

        SettingsModels.fuelBagTranslateX = 390;
        SettingsModels.fuelBagTranslateY = 1035;

        SettingsModels.fuelCountTranslateX = 390;
        SettingsModels.fuelCountTranslateY = 1035;


    }
}
