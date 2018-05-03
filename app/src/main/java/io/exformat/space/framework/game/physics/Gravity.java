package io.exformat.space.framework.game.physics;

import java.util.*;

import io.exformat.space.framework.game.math.MyMath;
import io.exformat.space.framework.game.objects.FlyObject;
import io.exformat.space.framework.game.physics.interfaces.IGravity;

public class Gravity implements IGravity {

	private float k1,k2,k3,k4,q1,q2,q3,q4;


	private double calculateDistance(FlyObject flyObject, FlyObject massObject) {

		return Math.sqrt(Math.pow(flyObject.getX() - massObject.getX(), 2) +
						 Math.pow(flyObject.getY() - massObject.getY(), 2) +
						 Math.pow(flyObject.getZ() - massObject.getZ(), 2));
	}

	@Override
	public void calculateGravity(ArrayList<FlyObject> massObjects,
								 FlyObject flyObject,
								 float STEP) {

		flyObject.setOldPosition(flyObject);								  
										  
		//calc x
		calcX(massObjects,
			  flyObject,
			  STEP);

		//calc y
		calcY(massObjects,
			  flyObject,
			  STEP);

		//calc z
		calcZ(massObjects,
			  flyObject,
			  STEP);
				  
		new MyMath().calculateAngleDirect(flyObject);
		
	}

	@Override
	public void calculateInertial(FlyObject flyObject, float STEP) {
		
		flyObject.setOldPosition(flyObject);
		
		flyObject.setX(flyObject.getX() + (STEP * flyObject.getVelocityX()));
		flyObject.setY(flyObject.getY() + (STEP * flyObject.getVelocityY()));
		flyObject.setZ(flyObject.getZ() + (STEP * flyObject.getVelocityZ()));
		
		new MyMath().calculateAngleDirect(flyObject);
	}
	
	@Override
	public void calculateGravityAllObjects(ArrayList<FlyObject> flyObjects, float STEP){
		
		for(int i = 0; i < flyObjects.size(); i++){

			FlyObject flyObject = flyObjects.get(i);
			flyObjects.remove(i);

			calculateGravity(flyObjects, flyObject, STEP);

			flyObjects.add(i, flyObject);
		}
	}


	//вычисляем координату и скорость по оси Х
    private void calcX(ArrayList<FlyObject> massObjects,
					   FlyObject flyObject,
					   float STEP) {

        k1 = STEP * fX(massObjects, flyObject, flyObject.getX());
        q1 = STEP * flyObject.getVelocityX();

        k2 = STEP * fX(massObjects, flyObject, flyObject.getX() + q1 / 2);
        q2 = STEP * (flyObject.getVelocityX() + k1 / 2);

        k3 = STEP * fX(massObjects, flyObject, flyObject.getX() + q2 / 2);
        q3 = STEP * (flyObject.getVelocityX() + k2 / 2);

        k4 = STEP * fX(massObjects, flyObject, flyObject.getX() + q3);
        q4 = STEP * (flyObject.getVelocityX() + k3);


		flyObject.setVelocityX(flyObject.getVelocityX() + (k1 + 2 * k2 + 2 * k3 + k4) / 6);
		flyObject.setX(flyObject.getX() + (q1 + 2 * q2 + 2 * q3 + 2 * q3 + q4) / 6);
    }

	//вычисляем координату и скорость по оси Y
    private void calcY(ArrayList<FlyObject> massObjects,
					   FlyObject flyObject,
					   float STEP) {

        k1 = STEP * fY(massObjects, flyObject, flyObject.getY());
        q1 = STEP * flyObject.getVelocityY();

        k2 = STEP * fY(massObjects, flyObject, flyObject.getY() + q1 / 2);
        q2 = STEP * (flyObject.getVelocityY() + k1 / 2);

        k3 = STEP * fY(massObjects, flyObject, flyObject.getY() + q2 / 2);
        q3 = STEP * (flyObject.getVelocityY() + k2 / 2);

        k4 = STEP * fY(massObjects, flyObject, flyObject.getY() + q3);
        q4 = STEP * (flyObject.getVelocityY() + k3);


		flyObject.setVelocityY(flyObject.getVelocityY() + (k1 + 2 * k2 + 2 * k3 + k4) / 6);
		flyObject.setY(flyObject.getY() + (q1 + 2 * q2 + 2 * q3 + 2 * q3 + q4) / 6);
    }

	//вычисляем координату и скорость по оси Z
    private void calcZ(ArrayList<FlyObject> massObjects,
					   FlyObject flyObject,
					   float STEP) {

        k1 = STEP * fZ(massObjects, flyObject, flyObject.getZ());
        q1 = STEP * flyObject.getVelocityZ();

        k2 = STEP * fZ(massObjects, flyObject, flyObject.getZ() + q1 / 2);
        q2 = STEP * (flyObject.getVelocityZ() + k1 / 2);

        k3 = STEP * fZ(massObjects, flyObject, flyObject.getZ() + q2 / 2);
        q3 = STEP * (flyObject.getVelocityZ() + k2 / 2);

        k4 = STEP * fZ(massObjects, flyObject, flyObject.getZ() + q3);
        q4 = STEP * (flyObject.getVelocityZ() + k3);


		flyObject.setVelocityZ(flyObject.getVelocityZ() + (k1 + 2 * k2 + 2 * k3 + k4) / 6);
		flyObject.setZ(flyObject.getZ() + (q1 + 2 * q2 + 2 * q3 + 2 * q3 + q4) / 6);
    }

	//функция для х
	//вычисляется ускорение по конкретной оси
    private float fX(ArrayList<FlyObject> massObjects, FlyObject flyObject, float coordinate) {

        float acceleration = 0;

		//цикл по всем объектам
		for (FlyObject massObject: massObjects) {

			//находим ускорение по этой оси			  
			acceleration += massObject.getMass() * (massObject.getX() - coordinate) / Math.pow(calculateDistance(flyObject, massObject), 3);
		}   
        return acceleration;
    }

	//функция для Y
	private float fY(ArrayList<FlyObject> massObjects, FlyObject flyObject, float coordinate) {

        float acceleration = 0;

		//цикл по всем объектам
		for (FlyObject massObject: massObjects) {

			//находим ускорение по этой оси			  
			acceleration += massObject.getMass() * (massObject.getY() - coordinate) / Math.pow(calculateDistance(flyObject, massObject), 3);
		}   
        return acceleration;
    }

	//функция для Z
	private float fZ(ArrayList<FlyObject> massObjects, FlyObject flyObject, float coordinate) {

        float acceleration = 0;

		//цикл по всем объектам
		for (FlyObject massObject: massObjects) {

			//находим ускорение по этой оси			  
			acceleration += massObject.getMass() * (massObject.getZ() - coordinate) / Math.pow(calculateDistance(flyObject, massObject), 3);
		}   
        return acceleration;
    }
}
