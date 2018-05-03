package io.exformat.space.framework.game.objects;

public class Vector3 extends Vector2{

	private float z, z2;

	public Vector3(){}
	
	public Vector3(float x, float y, float z,
				   float x2, float y2, float z2) {
		
		super.setX(x);
		super.setY(y);
		this.z = z;
		
		super.setX2(x2);
		super.setY2(y2);
		this.z2 = z2;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getZ() {
		return z;
	}

	public void setZ2(float z2) {
		this.z2 = z2;
	}

	public float getZ2() {
		return z2;
	}
}
