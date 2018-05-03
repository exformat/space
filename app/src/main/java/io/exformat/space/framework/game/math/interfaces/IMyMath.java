package io.exformat.space.framework.game.math.interfaces;


import io.exformat.space.framework.game.objects.FlyObject;
import io.exformat.space.framework.game.objects.VectorXYZ;

public interface IMyMath
{

	float calculateDistance(VectorXYZ v1, VectorXYZ v2);

	float calculateImpulse(FlyObject flyObject);

	void calculateAngleDirect(FlyObject flyObject);

	float calculateAngle(float x1, float y1,
						 float x2, float y2);
}
