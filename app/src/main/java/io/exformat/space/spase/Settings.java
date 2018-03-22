package io.exformat.space.spase;


import io.exformat.space.spase.settings.SettingsModels;

public class Settings {

    public void calculateModels(){

        SettingsModels.displayHeight = Assets.displayHeight;
        SettingsModels.displayWidth = Assets.displayWidth;

        calcScaleX();
        calcScaleY();

        SettingsModels.displayHeight_05 = SettingsModels.displayHeight / 2;
        SettingsModels.displayWidth_05  = SettingsModels.displayWidth  / 2;

        SettingsModels.fuelBagTranslateX = SettingsModels.displayWidth / 10 / 2;
        SettingsModels.fuelBagTranslateY = SettingsModels.displayHeight / 2;


    }

    private void calcScaleX(){

        if (Assets.displayWidth < 1920){
            SettingsModels.scaleX = 1920 / Assets.displayWidth - 1;
        }
        if (Assets.displayWidth > 1920){
            SettingsModels.scaleX = 1920 / Assets.displayWidth;
        }
        if (Assets.displayWidth == 1920){
            SettingsModels.scaleX = 1;
        }
    }

    private void calcScaleY(){

        if(Assets.displayHeight < 1080){
            SettingsModels.scaleY = 1080 / Assets.displayHeight - 1;
        }
        if (Assets.displayHeight > 1080){
            SettingsModels.scaleY = 1080 / Assets.displayHeight;
        }
        if(Assets.displayHeight == 1080){
            SettingsModels.scaleY = 1;
        }
    }
}
