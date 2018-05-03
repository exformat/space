package io.exformat.space.framework.game.objects;

public class Vector2{
	
	private float x,y,
				  x2,y2;

	public Vector2(){}			  
				  
	public Vector2(float x, float y, float x2, float y2) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getY() {
		return y;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public float getX2() {
		return x2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}

	public float getY2() {
		return y2;
	}
}
