package io.exformat.space.framework.impl;
import android.view.View.*;
import java.util.*;
import io.exformat.space.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener{

	public boolean isTouchDown(int pointer);
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);
    public List<TouchEvent> getTouchEvents();

}
