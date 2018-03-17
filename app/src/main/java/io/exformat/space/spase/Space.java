package io.exformat.space.spase;

import java.util.ArrayList;
import java.util.List;

import io.exformat.space.UI.Buttons;
import io.exformat.space.calculate.CalculateCoordinate;
import io.exformat.space.calculate.CalculateDirect;
import io.exformat.space.framework.Game;
import io.exformat.space.framework.Graphics;
import io.exformat.space.framework.Input.TouchEvent;
import io.exformat.space.framework.Screen;
import io.exformat.space.model.FlyObject;
import io.exformat.space.model.MassObject;

import static android.graphics.Color.WHITE;

public class Space extends Screen {

    private double STEP = 0.01;
    private int stepCount = 0;

    private int touchDownX;
    private int touchDownY;

    private int touchDraggedX;
    private int touchDraggedY;
    private boolean calcTouch;
    private boolean trust = false;

    private float angle;


    FlyObject flyObject = new FlyObject();
    MassObject massObject = new MassObject();

    CalculateCoordinate calculateCoordinate = new CalculateCoordinate();
    CalculateDirect calculateDirect = new CalculateDirect();
    Buttons buttons = new Buttons();

    //сразу инициализируем массив при старте этого класса
    //инициализация идет через вложенный класс
    ArrayList<MassObject> massObjects = new AddMassObject().getMassObjects();


    public Space(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

        calculateCoordinate.calculate(massObjects,flyObject,STEP);

        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {

            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_DOWN){

                touchDownX = event.x;
                touchDownY = event.y;

                //calcTouch = true;
            }

            if (event.type == TouchEvent.TOUCH_DRAGGED) {

                touchDraggedX = event.x;
                touchDraggedY = event.y;

                angle = calculateDirect.getAngle(touchDownX, touchDownY,
                            touchDraggedX, touchDraggedY);

                //высчитываем новое направление
                calculateDirect.calculateDirection(flyObject, 1, angle, 0);

                //рисуем новую ракету
                trust = true;
            }

            if (event.type == TouchEvent.TOUCH_UP){

                //calcTouch = false;
                trust = false;
            }
        }
    }

    @Override
    public void present(float deltaTime) {

        Graphics graphics = game.getGraphics();

        graphics.drawPixmap(Assets.background, 0, 0);
        graphics.drawText("step: " + stepCount, 300, 200, WHITE);

        if (!trust) {
            graphics.drawPixmap(Assets.flyObject,
                    (int) flyObject.getX() - Assets.flyObject.getWidth() / 2,
                    (int) flyObject.getY() - Assets.flyObject.getHeight() / 2);
        }else{
            graphics.drawPixmap(Assets.flyObjectTust,
                    (int) flyObject.getX() - Assets.flyObject.getWidth() / 2,
                    (int) flyObject.getY() - Assets.flyObject.getHeight() / 2);
        }

        for (MassObject massObj : massObjects) {

            graphics.drawCircle((int) massObj.getX(),
                    (int) massObj.getY(), 20, WHITE);

        }
        graphics.drawPixmap(Assets.joystickButton,
                buttons.getJoystickX() - (Assets.joystickButton.getWidth() / 2),
                buttons.getJoystickY() - (Assets.joystickButton.getWidth() / 2));
        graphics.drawPixmap(Assets.trustButton,
                buttons.getButtonTrustX() - (Assets.trustButton.getHeight() / 2),
                buttons.getButtonTrustY() - (Assets.trustButton.getWidth() / 2));

        graphics.drawText(" " + buttons.getJoystickX() + buttons.getJoystickY() + "angle: " + angle, 500, 100, WHITE);

        update(deltaTime);
        stepCount++;
    }

    //метод выполняет проверку области касания
    //параметры, событие "касание",левый верхний угол(x, y)
    // высота и ширина области отслеживания
    private boolean inBounds(TouchEvent event, int x,int y,int width, int height){

        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private class AddMassObject{

        ArrayList<MassObject> massObjects = new ArrayList<>();

        public ArrayList<MassObject> getMassObjects(){

            massObjects.add(new MassObject(960,512,0,200000));

            return this.massObjects;
        }
    }
}