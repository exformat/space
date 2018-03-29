package io.exformat.space.spase;



import io.exformat.space.spase.settings.SettingsModels;

public class Settings {

    public void calculateModels(){

        SettingsModels.displayHeight = Assets.displayHeight;
        SettingsModels.displayWidth = Assets.displayWidth;

        calcScaleX();
        calcScaleY();

        SettingsModels.displayHeight_05 = SettingsModels.displayHeight / 2f;
        SettingsModels.displayWidth_05  = SettingsModels.displayWidth  / 2f;

        SettingsModels.fuelBagTranslateX = 390 * SettingsModels.scaleX;
        SettingsModels.fuelBagTranslateY = 1035 * SettingsModels.scaleX;

        SettingsModels.fuelCountTranslateX = 390 * SettingsModels.scaleX;
        SettingsModels.fuelCountTranslateY = 1035 * SettingsModels.scaleX;


    }

    private void calcScaleX() {

        if (SettingsModels.displayHeight < 1080){
            SettingsModels.scaleY = (1080 / SettingsModels.displayHeight) - 1;
        }
        if (SettingsModels.displayHeight > 1080){
            SettingsModels.scaleY = 1080 / SettingsModels.displayHeight;
        }
        if (SettingsModels.displayHeight == 1080){
            SettingsModels.scaleY = 1;
        }



        /*
        if(Assets.displayHeight < 1080){
            SettingsModels.scaleY = 1080 / Assets.displayHeight - 1;
        }
        if (Assets.displayHeight > 1080){
            SettingsModels.scaleY = 1080 / Assets.displayHeight;
        }
        if(Assets.displayHeight == 1080){
            SettingsModels.scaleY = 1;
        }
        */
    }

    private void calcScaleY(){

        if (SettingsModels.displayWidth < 1920){
            SettingsModels.scaleX = (1920 / SettingsModels.displayWidth) - 1;
        }
        if (SettingsModels.displayWidth > 1920){
            SettingsModels.scaleX = 1920 / SettingsModels.displayWidth;
        }
        if (SettingsModels.displayWidth == 1920){
            SettingsModels.scaleX = 1;
        }

        /*
        if (Assets.displayWidth < 1920){
            SettingsModels.scaleX = 1920 / Assets.displayWidth - 1;
        }
        if (Assets.displayWidth > 1920){
            SettingsModels.scaleX = 1920 / Assets.displayWidth;
        }
        if (Assets.displayWidth == 1920){
            SettingsModels.scaleX = 1;
        }
        */
    }
}
