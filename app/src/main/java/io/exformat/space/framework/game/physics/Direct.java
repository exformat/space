package io.exformat.space.framework.game.physics;

import io.exformat.space.framework.game.objects.FlyObject;
import io.exformat.space.framework.game.physics.interfaces.IDirect;

public class Direct implements IDirect {
	
	@Override
	public void calculateImpulseDirect(FlyObject flyObject, float impulse,
									   float angleXY, float angleYZ) {
		
		float tmp, accelX, accelY, accelZ;

		impulse /= flyObject.getMass();
		
		//расчитываем ускорения исходя их заданых градусов поворота
		accelX = impulse * (float) Math.sin(Math.toRadians(180 - (angleXY + 90)));
		tmp    = impulse * (float) Math.sin(Math.toRadians(angleXY));
		accelY = tmp * (float) Math.sin(Math.toRadians(180 - (angleYZ + 90)));
		accelZ = tmp * (float) Math.sin(Math.toRadians(angleYZ));

		flyObject.setVelocityX(flyObject.getVelocityX() + accelX);
		flyObject.setVelocityY(flyObject.getVelocityY() + accelY);
		flyObject.setVelocityZ(flyObject.getVelocityZ() + accelZ);
		
		flyObject.setAngleDirectXY(angleXY);
		flyObject.setAngleDirectYZ(angleYZ);
	}

	

	

	
	

	
	


	
	
	
	
	
	
	
	
	
	
	
	/*
	//метод расчитывает направление от глобальных координат
//	@Override
	public void calculateImpulseDirection(Rocket rocket, double anglXY, double anglYZ){


		double tmp, accelX, accelY, accelZ;

		double accel = rocket.getPowerTrust() / (rocket.getMass() + rocket.getFuelMass());

			
		//расчитываем ускорения исходя их заданых градусов поворота
		accelX = accel * Math.sin(Math.toRadians(180 - (anglXY + 90)));
		tmp = accel * Math.sin(Math.toRadians(anglXY));
		accelY = tmp * Math.sin(Math.toRadians(180 - (anglYZ + 90)));
		accelZ = tmp * Math.sin(Math.toRadians(anglYZ));
			
		rocket.setVelocityX(rocket.getVelocityX() + accelX);
		rocket.setVelocityY(rocket.getVelocityY() + accelY);
		rocket.setVelocityZ(rocket.getVelocityZ() + accelZ);
	}
	
	/*===========================================================

	
	private boolean fuelOut(FlyObject flyObject){

		if (flyObject.getM() >= flyObject.getDryMass()) {

			flyObject.setM(flyObject.getM() - flyObject.getFuelOut());
			flyObject.setFuelMass(flyObject.getFuelMass() - flyObject.getFuelOut());

			return true;
		}else {

			return false;
		}
	}

	public int getAngle(float touchDownX, float touchDownY,
						float touchDraggedX, float touchDraggedY) {

		//int angle = (int) Math.toDegrees(Math.atan2( touchDraggedY - touchDownY, touchDraggedX - touchDownX));
		int angle = (int) Math.toDegrees(Math.atan2(touchDraggedX - touchDownX,  touchDraggedY - touchDownY));

		return angle < 0 ? angle + 360 : angle;
	}
	
	*/
}
