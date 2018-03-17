package io.exformat.space.framework.impl;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerHandler implements SensorEventListener {
	
    float accelX;
	float accelY;
    float accelZ;
	
    public AccelerometerHandler(Context context) {
		
        SensorManager manager = (SensorManager) context
			.getSystemService(Context.SENSOR_SERVICE);
		
		//если датчик есть, включаем слушатель датчика в игровом режиме
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			
            Sensor accelerometer = manager.getSensorList(
				Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this, accelerometer,
									 SensorManager.SENSOR_DELAY_GAME);
        }
    }
	
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Здесь ничего не делаем
    }
	
	//определяем текущие значения датчика
    @Override
    public void onSensorChanged(SensorEvent event) {
		
        accelX = event.values[0];
        accelY = event.values[1];
        accelZ = event.values[2];
    }
	
	//возвращаем текущие значения датчика
    public float getAccelX() {
		
        return accelX;
    }
    public float getAccelY() {
		
        return accelY;
    }
    public float getAccelZ() {
		
        return accelZ;
    }
}
