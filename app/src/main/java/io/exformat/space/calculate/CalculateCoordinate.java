package io.exformat.space.calculate;

import io.exformat.space.model.FlyObject;
import io.exformat.space.model.MassObject;

import java.util.*;

public class CalculateCoordinate{
	
	private double k1,k2,k3,k4,q1,q2,q3,q4;

    private double velocityX   = 0;
	private double velocityY   = 0;
	private double velocityZ   = 0;
	private double coordinateX = 0;
	private double coordinateY = 0;
	private double coordinateZ = 0;
	
	
	private double calculateRadius(FlyObject flyObject, MassObject massObject){
		
		return Math.sqrt(Math.pow(flyObject.getX() - massObject.getX(), 2) +
						 Math.pow(flyObject.getY() - massObject.getY(), 2) +
						 Math.pow(flyObject.getZ() - massObject.getZ(), 2));
	}

	public void calculate(ArrayList<MassObject> massObjects,
						  			  FlyObject flyObject,
						  				 double step){

			//calc x
			calcX(massObjects,
					flyObject,
			      step);
				
			//calc y
			calcY(massObjects,
					flyObject,
				  step);
					  	
			//calc z
			calcZ(massObjects,
					flyObject,
				  step);
					  
			
			//отправляем все новые значения в экземпляр класса
			flyObject.setX(coordinateX);
			flyObject.setVx(velocityX);
			flyObject.setY(coordinateY);
			flyObject.setVy(velocityY);
			flyObject.setZ(coordinateZ);
			flyObject.setVz(velocityZ);
	}

	//вычисляем координату и скорость по оси Х
    private void calcX(ArrayList<MassObject> massObjects,
					   FlyObject flyObject,
					   double step){
       
        k1 = step * fX(massObjects, flyObject, flyObject.getX());
        q1 = step * flyObject.getVx();

        k2 = step * fX(massObjects, flyObject, flyObject.getX() + q1 / 2);
        q2 = step * (flyObject.getVx() + k1 / 2);

        k3 = step * fX(massObjects, flyObject, flyObject.getX() + q2 / 2);
        q3 = step * (flyObject.getVx() + k2 /2);

        k4 = step * fX(massObjects, flyObject, flyObject.getX() + q3);
        q4 = step * (flyObject.getVx() + k3);

		this.velocityX = flyObject.getVx();
		this.coordinateX = flyObject.getX();

        this.velocityX += (k1 + 2 * k2 + 2 * k3 + k4) / 6;
        this.coordinateX += (q1 +2 * q2 + 2 * q3 + 2 * q3 + q4) / 6;
    }

	//вычисляем координату и скорость по оси Y
    private void calcY(ArrayList<MassObject> massObjects,
					   FlyObject flyObject,
					   double step){

        k1 = step * fY(massObjects, flyObject, flyObject.getY());
        q1 = step * flyObject.getVy();

        k2 = step * fY(massObjects, flyObject, flyObject.getY() + q1 / 2);
        q2 = step * (flyObject.getVy() + k1 / 2);

        k3 = step * fY(massObjects, flyObject, flyObject.getY() + q2 / 2);
        q3 = step * (flyObject.getVy() + k2 /2);

        k4 = step * fY(massObjects, flyObject, flyObject.getY() + q3);
        q4 = step * (flyObject.getVy() + k3);

		this.velocityY = flyObject.getVy();
		this.coordinateY = flyObject.getY();

        this.velocityY += (k1 + 2 * k2 + 2 * k3 + k4) / 6;
        this.coordinateY += (q1 +2 * q2 + 2 * q3 + 2 * q3 + q4) / 6;
    }

	//вычисляем координату и скорость по оси Z
    private void calcZ(ArrayList<MassObject> massObjects,
					   FlyObject flyObject,
					   double step){

        k1 = step * fZ(massObjects, flyObject, flyObject.getZ());
        q1 = step * flyObject.getVz();

        k2 = step * fZ(massObjects, flyObject, flyObject.getZ() + q1 / 2);
        q2 = step * (flyObject.getVz() + k1 / 2);

        k3 = step * fZ(massObjects, flyObject, flyObject.getZ() + q2 / 2);
        q3 = step * (flyObject.getVz() + k2 /2);

        k4 = step * fZ(massObjects, flyObject, flyObject.getZ() + q3);
        q4 = step * (flyObject.getVz() + k3);

		this.velocityZ = flyObject.getVz();
		this.coordinateZ = flyObject.getZ();
		
        this.velocityZ += (k1 + 2 * k2 + 2 * k3 + k4) / 6;
        this.coordinateZ += (q1 +2 * q2 + 2 * q3 + 2 * q3 + q4) / 6;
    }

	//функция для х
	//вычисляется ускорение по конкретной оси
    private double fX(ArrayList<MassObject> massObjects, FlyObject flyObject, double coordinate){

        double acceleration = 0;

		//цикл по всем объектам
		for (MassObject massObject: massObjects) {

			//находим ускорение по этой оси			  
			acceleration += massObject.getMass() * (massObject.getX() - coordinate) / Math.pow(calculateRadius(flyObject,massObject), 3);
		}   

        return acceleration;
    }

	//функция для Y
	private double fY(ArrayList<MassObject> massObjects, FlyObject flyObject, double coordinate){

        double acceleration = 0;

		//цикл по всем объектам
		for (MassObject massObject: massObjects) {

			//находим ускорение по этой оси			  
			acceleration += massObject.getMass() * (massObject.getY() - coordinate) / Math.pow(calculateRadius(flyObject,massObject), 3);
		}   

        return acceleration;
    }
	
	//функция для Z
	private double fZ(ArrayList<MassObject> massObjects, FlyObject flyObject, double coordinate){

        double acceleration = 0;

		//цикл по всем объектам
		for (MassObject massObject: massObjects) {
			
			//находим ускорение по этой оси			  
			acceleration += massObject.getMass() * (massObject.getZ() - coordinate) / Math.pow(calculateRadius(flyObject,massObject), 3);
		}   

        return acceleration;
    }
	
}
