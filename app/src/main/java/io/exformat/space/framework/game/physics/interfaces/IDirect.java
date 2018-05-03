package io.exformat.space.framework.game.physics.interfaces;

import io.exformat.space.framework.game.objects.FlyObject;


public interface IDirect {

	void calculateImpulseDirect(FlyObject flyObject,
								float impulse,
								float angleXY, float angleYZ);
}
