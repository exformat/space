package io.exformat.space.UI;

public class joystickButton {


    //метод возвращает угол наклона
    private int getAngle(float btnX, float btnY, float touchX, float touchY) {

        int angle = (int) Math.toDegrees(Math.atan2( touchY - btnY, touchX - btnX));

        return angle < 0 ? angle + 360 : angle;
    }


}
