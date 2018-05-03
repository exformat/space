package io.exformat.space.framework.game.physics;


import io.exformat.space.framework.game.objects.FlyObject;
import io.exformat.space.framework.game.objects.Vector2;
import io.exformat.space.framework.game.objects.Vector3;
import io.exformat.space.framework.game.physics.interfaces.INewDirect;

public class NewDirect implements INewDirect {


	@Override
	public Vector2 normalVector2(Vector2 v) {
		
		float x,y, modul, invers;
		
		x = v.getX2() - v.getX();
		y = v.getY2() - v.getY();
		
		modul = (float) Math.sqrt(Math.abs(x) + Math.abs(y));
		invers = 1 / modul;
		
		v.setX(0);
		v.setY(0);
		v.setX2(x * invers);
		v.setY2(y * invers);
		
		return v;
	}

	@Override
	public Vector3 normalVector3(Vector3 v) {
		
		float x,y,z, modul, invers;
		
		x = v.getX2() - v.getX();
		y = v.getY2() - v.getY();
		z = v.getZ2() - v.getZ();
		
		modul = (float) Math.sqrt(Math.abs(x) + Math.abs(y) + Math.abs(z));
		invers = 1 / modul;

		v.setX(0);
		v.setY(0);
		v.setZ(0);
		
		v.setX2(x * invers);
		v.setY2(y * invers);
		v.setZ2(z * invers);
		
		return v;
	}

	
	

	@Override
	public FlyObject newDirect2d(FlyObject f, Vector2 normalVector, float impulse) {
		
		float x = ((impulse / f.getMass()) * normalVector.getX2()) + f.getVelocityX();
		float y = ((impulse / f.getMass()) * normalVector.getY2()) + f.getVelocityY();
		
		//f.setVelocityX(f.getVelocityX() + x);
		f.setVelocityX(x);
		//f.setVelocityY(f.getVelocityY() + y);
		f.setVelocityY(y);

		return f;
	}

	@Override
	public FlyObject newDirect3d(FlyObject f, Vector3 normalVector, float impulse) {
		
		float x = ((impulse / f.getMass()) * normalVector.getX2()) + f.getX();
		float y = ((impulse / f.getMass()) * normalVector.getY2()) + f.getY();
		float z = ((impulse / f.getMass()) * normalVector.getZ2()) + f.getZ();
		
		f.setVelocityX(f.getVelocityX() + x);
		f.setVelocityY(f.getVelocityY() + y);
		f.setVelocityZ(f.getVelocityZ() + z);
		
		return f;
	}
}
