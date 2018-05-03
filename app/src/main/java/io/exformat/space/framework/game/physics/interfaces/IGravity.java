package io.exformat.space.framework.game.physics.interfaces;

import java.util.ArrayList;
import io.exformat.space.framework.game.objects.FlyObject;


public interface IGravity {

    void calculateGravity(ArrayList<FlyObject> massObjects, FlyObject flyObject, float STEP);

    void calculateGravityAllObjects(ArrayList<FlyObject> flyObjects, float STEP);

    void calculateInertial(FlyObject flyObject, float STEP);
}
