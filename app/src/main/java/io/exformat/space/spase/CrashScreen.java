package io.exformat.space.spase;

import io.exformat.space.framework.Game;
import io.exformat.space.framework.Graphics;
import io.exformat.space.framework.Screen;

import static android.graphics.Color.WHITE;


public class CrashScreen extends Screen {

    public CrashScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {

        Graphics graphics = game.getGraphics();

        graphics.drawText("crash!!!", Assets.displayHeight / 2, Assets.displayWidth / 2, WHITE);
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
