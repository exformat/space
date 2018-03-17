package io.exformat.space;

import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.AndroidGame;
import io.exformat.space.spase.LoadingScreen;

public class MainActivity extends AndroidGame
{
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
