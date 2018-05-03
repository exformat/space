package io.exformat.space.framework.game.physics.interfaces;

import io.exformat.space.framework.game.objects.FlyObject;
import io.exformat.space.framework.game.objects.Vector2;
import io.exformat.space.framework.game.objects.Vector3;

public interface INewDirect{
	
	Vector2 normalVector2(Vector2 v);
	Vector3 normalVector3(Vector3 v);
	
	FlyObject newDirect2d(FlyObject flyObject, Vector2 normalVector, float impulse);

	FlyObject newDirect3d(FlyObject flyObject, Vector3 normalVector, float impulse);
}
