package io.exformat.space.framework.impl;

import android.content.*;
import android.view.*;
import io.exformat.space.framework.*;
import java.util.*;

public class AndroidInput implements Input {

	AccelerometerHandler accelHandler;
	TouchHandler touchHandler;
	
	public AndroidInput(Context context, View view, float scaleX, float scaleY) { 
        
		accelHandler = new AccelerometerHandler(context);
        
        touchHandler = new MultitouchHandler(view, scaleX, scaleY);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }
    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }
    @Override
    public int getTouchY(int pointer) {
		return touchHandler.getTouchY(pointer);
    }
    @Override
    public float getAccelX() {
        return accelHandler.getAccelX();
    }
    @Override
    public float getAccelY() {
        return accelHandler.getAccelY();
    }
    @Override
    public float getAccelZ() {
        return accelHandler.getAccelZ();
    }
    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
}
