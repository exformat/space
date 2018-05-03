package io.exformat.space.framework.game.physics.interfaces;

import io.exformat.space.framework.game.objects.FlyObject;


public interface ICollision {

		   
	void calculateSphereCollision(FlyObject f1, FlyObject f2);
	
	boolean isCollisionSphere(FlyObject f1, FlyObject f2);
	
	float getAngleForImpulseSphere1();
	float getAngleForImpulseSphere2();
}
