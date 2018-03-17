package io.exformat.space.UI;

public class Buttons {

    private static int buttonTrustX;
    private  static int buttonTrustY;

    private static int joystickX;
    private static int joystickY;

    public void calcCoordinateButtonTrust(int height, int width){

        buttonTrustY = (int) (width - ((width / 2) - (width / 2 / 1.6)));
        buttonTrustX = (int) (height - ((height / 2) - (height / 2 / 1.6)));
    }

    public void calcCoordinateJoystick(int height, int width){

        joystickX = (int) ((height / 2) - (height / 2 / 1.6));
        joystickY = (int) (width - ((width / 2) - ((width / 2) / 1.6)));
    }

    public int getButtonTrustX() {
        return buttonTrustX;
    }

    public void setButtonTrustX(int buttonTrustX) {
        Buttons.buttonTrustX = buttonTrustX;
    }

    public int getButtonTrustY() {
        return buttonTrustY;
    }

    public void setButtonTrustY(int buttonTrustY) {
        Buttons.buttonTrustY = buttonTrustY;
    }

    public int getJoystickX() {
        return joystickX;
    }

    public void setButtonDirectX(int buttonDirectX) {
        Buttons.joystickX = buttonDirectX;
    }

    public int getJoystickY() {
        return joystickY;
    }

    public void setButtonDirectY(int buttonDirectY) {
        Buttons.joystickY = buttonDirectY;
    }
}
