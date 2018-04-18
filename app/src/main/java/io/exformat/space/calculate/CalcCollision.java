package io.exformat.space.calculate;


import io.exformat.space.model.FlyObject;

public class CalcCollision {

    //TODO !!!!!!! не смотреть сюда!! тут всё через одно место =) и пока вообще не работает

    private double centerMassX;
    private double centerMassY;
    private double centerMassZ;

    private double length;          //расстояние от центра тяжести до края объекта
    private double lengthCollision; //расстояние от центра тяжести до места столкновения

    private double speedX;
    private double speedY;
    private double speedZ;

    private double angleSpeedXY;
    private double angleSpeedYZ;

    private double collisionX;
    private double collisionY;
    private double collisionZ;

    private double angleCollisionXY;
    private double angleCollisionYZ;

    private double speedRocket;
    private double acceleration;

    private FlyObject rocketFlyObject;
    private FlyObject flyObject;
    private CalculateDirect calculateDirect = new CalculateDirect();


    private boolean radiusCollision(){



        return true;
    }

    private void calcAngleCollision(){

        angleCollisionXY = flyObject.getAngleDirectXY() - rocketFlyObject.getAngleDirectXY();
        angleCollisionYZ = flyObject.getAngleDirectYZ() - rocketFlyObject.getAngleDirectYZ();
    }

    private void calcAcceleration(){

        speedRocket = Math.sqrt(Math.pow(rocketFlyObject.getVx(), 2) +
                                Math.pow(rocketFlyObject.getVy(), 2) +
                                Math.pow(rocketFlyObject.getVz(), 2));

        acceleration = (rocketFlyObject.getMass() * speedRocket) / flyObject.getMass();
    }

    private void calcImpulsDirect(){

        //вычисление нового направления от удара по направлению движния ракеты 10% от ускорения
        //calculateDirect.calculateDirection(flyObject, acceleration / 10,
        //        rocketFlyObject.getAngleDirectXY(), rocketFlyObject.getAngleDirectYZ());

        //отражение ракеты от объекта столкновения
        //TODO косяк тут, позже разберусь
        calculateDirect.calculateDirection(rocketFlyObject, acceleration / 10,
                                    rocketFlyObject.getAngleDirectXY() + flyObject.getAngleDirectXY(),
                                    rocketFlyObject.getAngleDirectYZ() + flyObject.getAngleDirectYZ());

        //отнимаем енергию потраченную на направление объекта по напрвлению движения ракеты
        //для удобства дальнейших расчетов возводим в 2ю степень
        acceleration -= (acceleration / 10);
        acceleration = Math.pow(acceleration,2);
    }

    private void calcXXXXXXXXX(){

        double k,
               tmpMass,
               accelerationDirectObject,
               accelerationAngleSpeedObject;

        length = flyObject.getRadius();

        lengthCollision = 100;//TODO calcCoordinateCollision();

        //коэффициент длины объекта  длины от центра тяжести до точки столкновения ^2
        k = Math.pow(length / lengthCollision, 2);

        //ускорение которое уйдет на передвижение объекта
        //ускорение которое уйдёт на вращение объекта
        accelerationDirectObject = acceleration / k;
        accelerationAngleSpeedObject =  accelerationDirectObject - acceleration;

        //масса в квадрате для расчета нового направления объекта
        tmpMass = Math.pow(flyObject.getMass(), 2) / k;
        tmpMass = Math.pow(flyObject.getMass(), 2) - tmpMass;


        //вычисление нового направления от удара по направлению движния ракеты 10% от ускорения
        calculateDirect.calculateDirection(flyObject,
                Math.sqrt(accelerationDirectObject / Math.pow(flyObject.getMass(), 2)),
                      rocketFlyObject.getAngleDirectXY(), rocketFlyObject.getAngleDirectYZ());

        //вычисление оборотов в секунду
        angleSpeedXY = (accelerationAngleSpeedObject / tmpMass) / (flyObject.getRadius() * Math.PI);

        //перевод из оборотов/сек в градусы/секунду
        flyObject.setAngleSpeedXY(360 * (float) angleSpeedXY);
    }

    private void calcCoordinateCollision(){


    }

}
