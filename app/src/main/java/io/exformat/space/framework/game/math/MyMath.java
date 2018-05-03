package io.exformat.space.framework.game.math;

import io.exformat.space.framework.game.math.interfaces.IMyMath;
import io.exformat.space.framework.game.objects.FlyObject;
import io.exformat.space.framework.game.objects.VectorXYZ;

public class MyMath implements IMyMath {

	@Override
	public float calculateAngle(float x1, float y1, float x2, float y2) {
		
		//float angle = (float) Math.toDegrees(Math.atan2(x2 - x1,  y2 - y1));
		float angle = (float) Math.toDegrees(Math.atan2(y2 - y1,  x2 - x1));

		return angle < 0 ? angle + 360 : angle;
	}

	@Override
	public float calculateImpulse(FlyObject flyObject) {
		
		float a = (float) Math.sqrt(Math.pow(flyObject.getVelocityX(), 2) +
				  			        Math.pow(flyObject.getVelocityY(), 2) +
				  			 		Math.pow(flyObject.getVelocityZ(), 2));
		
		
		return flyObject.getMass() * a;
	}

	@Override
	public void calculateAngleDirect(FlyObject f) {
		
		float angleXY = calculateAngle(f.getX(),f.getY(),
								        f.getX() + f.getVelocityX(),
										f.getY() + f.getVelocityY());
		
		float angleYZ = calculateAngle(f.getY(),f.getZ(),
								        f.getY() + f.getVelocityY(),
										f.getZ() + f.getVelocityZ());
										
		f.setAngleDirectXY(angleXY + 270);
		f.setAngleDirectYZ(angleYZ + 270);
	}

	@Override
	public float calculateDistance(VectorXYZ v1, VectorXYZ v2) {
		
		return (float) Math.sqrt(Math.pow(v1.getX() - v2.getX(), 2) +
								 Math.pow(v1.getY() - v2.getY(), 2) +
								 Math.pow(v1.getZ() - v2.getZ(), 2));
	}
}
