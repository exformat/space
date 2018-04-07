package io.exformat.space;

import io.exformat.space.framework.Screen;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.spase.LoadingScreenOpenGL;

public class MainActivity extends GLGame
{
    @Override
    public Screen getStartScreen() {
        return new LoadingScreenOpenGL(this);
    }
}
