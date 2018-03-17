package io.exformat.space.calculate;

import io.exformat.space.model.FlyObject;

public class CalculateDirect 
{
	
	//метод расчитывает направление от глобальных координат
	
	public void calculateDirection(FlyObject flyObject, double accel, double anglXY, double anglYZ){
		
		double tmp, accelX, accelY, accelZ, velocityX,velocityY,velocityZ;

		velocityX = flyObject.getVx();
		velocityY = flyObject.getVy();
		velocityZ = flyObject.getVz();
		
		//ускорение воспринимаем как вектор,
		//считаем по формуле, гипотенуза(вектор) * синус
		//писать свою реализацию расчета синуса посчитал извращением))

		//расчитываем ускорения исходя их заданых градусов поворота
		accelX = accel * Math.sin(Math.toRadians(180 - (anglXY + 90)));
		tmp =    accel * Math.sin(Math.toRadians(anglXY));
		accelY =   tmp * Math.sin(Math.toRadians(180 - (anglYZ + 90)));
		accelZ =   tmp * Math.sin(Math.toRadians(anglYZ));
		
		//получаем новые скорости
		velocityX += accelX;
		velocityY += accelY;
		velocityZ += accelZ;
		
		//заносим все новые значения в экземпляр класса
		flyObject.setVx(velocityX);
		flyObject.setVy(velocityY);
		flyObject.setVz(velocityZ);
	}

	public int getAngle(float touchDownX, float touchDownY, float touchDraggedX, float touchDraggedY) {

		int angle = (int) Math.toDegrees(Math.atan2( touchDraggedY - touchDownY, touchDraggedX - touchDownX));

		return angle < 0 ? angle + 360 : angle;
	}
}
