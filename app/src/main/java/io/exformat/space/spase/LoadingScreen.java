package io.exformat.space.spase;

import android.os.Build;
import android.support.annotation.RequiresApi;

import io.exformat.space.UI.Buttons;
import io.exformat.space.framework.Game;
import io.exformat.space.framework.Graphics;
import io.exformat.space.framework.Graphics.PixmapFormat;
import io.exformat.space.framework.Screen;

public class LoadingScreen extends Screen {

    private Buttons btnCoordinate = new Buttons();

    public LoadingScreen(Game game){

        super(game);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void update(float deltaTime) {
        Graphics graphics = game.getGraphics();

        //загрузка активов игры
        Assets.flyObject = graphics.newPixmap("rocket.png", PixmapFormat.RGB565);
        Assets.joystickButton = graphics.newPixmap("stick.png", PixmapFormat.ARGB4444);
        Assets.trustButton = graphics.newPixmap("trust_button.png", PixmapFormat.ARGB4444);
        Assets.background = graphics.newPixmap("background.png", PixmapFormat.ARGB4444);
        Assets.flyObjectTust = graphics.newPixmap("rocket_trust.png", PixmapFormat.ARGB4444);

        //тут нужно будет реализовать получение разрешения экрана девайса
        //TODO hardcodee...
        int height = 1920;
        int width = 1080;

        Assets.displayHeight = height;
        Assets.displayWidth = width;

        //расчет координат расположения кнопок исходя из размера экрана
        btnCoordinate.calcCoordinateJoystick(height,width);
        btnCoordinate.calcCoordinateButtonTrust(height,width);


        //загрузка экрана игры
        game.setScreen(new Space(game));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
