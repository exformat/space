package io.exformat.space.framework.game.physics;

import io.exformat.space.framework.game.math.interfaces.IMyMath;
import io.exformat.space.framework.game.objects.FlyObject;
import io.exformat.space.framework.game.physics.interfaces.ICollision;


public class Collision implements ICollision {

	private IMyMath myMath;

	private float angleForImpulseSphere1;
	private float angleForImpulseSphere2;

	@Override
	public boolean isCollisionSphere(FlyObject f1, FlyObject f2) {
		
		return myMath.calculateDistance(f1, f2) - (f1.getRadius() + f2.getRadius()) < 0;
	}
	
	@Override
	public void calculateSphereCollision(FlyObject sphere1, FlyObject sphere2) {
	
		
		float angleCollision,
			  normalCollision;
			   
		float a,b,c;
		
		angleCollision = myMath.calculateAngle(sphere1.getX(),sphere1.getY(),
											   sphere2.getX(),sphere2.getY());
		normalCollision = angleCollision - 90;
		
		if(normalCollision < 0){
			normalCollision += 360;
		}
		
		a = angleCollision - sphere1.getAngleDirectXY();

		if(a < 0){
			a =+ a;
		}

		b = a + 90;
		c = 180 - b;
		
		
		angleForImpulseSphere1 = c - normalCollision;

		if(angleForImpulseSphere1 < 0){
			angleForImpulseSphere1 += 360;
		}

		angleForImpulseSphere2 = angleForImpulseSphere1 + 180;

		if(angleForImpulseSphere2 > 360){
			angleForImpulseSphere2 -= 360;
		}
	}

	@Override
	public float getAngleForImpulseSphere1() {
		
		return angleForImpulseSphere1;
	}

	@Override
	public float getAngleForImpulseSphere2() {
	
		return angleForImpulseSphere2;
	}
}
