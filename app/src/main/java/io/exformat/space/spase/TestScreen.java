package io.exformat.space.spase;

import android.util.Log;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import io.exformat.space.framework.Game;
import io.exformat.space.framework.Input;
import io.exformat.space.framework.Screen;
import io.exformat.space.framework.game.math.MyMath;
import io.exformat.space.framework.game.objects.Vector2;
import io.exformat.space.framework.game.math.interfaces.IMyMath;
import io.exformat.space.framework.game.physics.Direct;
import io.exformat.space.framework.game.physics.Gravity;
import io.exformat.space.framework.game.physics.NewDirect;
import io.exformat.space.framework.game.physics.interfaces.IDirect;
import io.exformat.space.framework.game.physics.interfaces.IGravity;
import io.exformat.space.framework.impl.GLGame;
import io.exformat.space.framework.impl.GLGraphics;
import io.exformat.space.model.Models;
import io.exformat.space.model.Rocket;
import io.exformat.space.model.Textures;

public class TestScreen extends Screen {

    GLGraphics glGraphics;

    private final float STEP = 0.1f;

    private Rocket   rocket  = new Rocket();

    private IGravity gravity = new Gravity();
    private IDirect  direct  = new Direct();
    private IMyMath  myMath  = new MyMath();

    private int touchDownX;
    private int touchDownY;

    private int touchDraggedX;
    private int touchDraggedY;


    public TestScreen(Game game) {
        super(game);
        glGraphics = ((GLGame) game).getGLGraphics();

        rocket.defaultInitialise();

        rocket.setX(500);
        rocket.setY(500);
    }

    @Override
    public void update(float deltaTime) {

        control();
        gravity.calculateInertial(rocket, STEP);
    }

    @Override
    public void present(float deltaTime) {

        GL10 gl = glGraphics.getGL();

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, Assets.displayWidth, 0, Assets.displayHeight, 1, -1);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);


//==================================================
        if (rocket.isTrust()) {

            Textures.rocketTrustTexture.bind();

        } else {

            Textures.rocketTexture.bind();
        }

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(rocket.getX(), rocket.getY(), 0);
        gl.glRotatef(rocket.getAngleDirectXY(), 0,0,1);
        Models.rocketVertices.draw(GL10.GL_TRIANGLES, 0, 6);


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

    private void control() {


        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {

            Input.TouchEvent event = touchEvents.get(i);

            //первое касание записываем координаты
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {

                touchDownX = event.x;
                touchDownY = event.y;
            }

            //передвижение считаем
            //записываем координаты
            //считаем угол от первого касания
            if (event.type == Input.TouchEvent.TOUCH_DRAGGED) {

                touchDraggedX = event.x;
                touchDraggedY = event.y;

                rocketImpulse();
            }

            if (event.type == Input.TouchEvent.TOUCH_UP) {

                //ракета без огонька
                //rocketImpulse();

                rocket.setTrust(false);
            }
        }
    }

    private void rocketImpulse(){

        Vector2 normalVector = new Vector2();

        normalVector.setX(touchDownX);
        normalVector.setY(Math.abs(touchDownY - 1080)); //fixme
        normalVector.setX2(touchDraggedX);
        normalVector.setY2(Math.abs(touchDraggedY - 1080));//fixme

        normalVector = new NewDirect().normalVector2(normalVector);


        //рисуем огонёк у ракеты
        if (rocket.getFuelMass() > 0) {

            rocket.setTrust(true);

            new MyMath().calculateAngleDirect(rocket);
            new NewDirect().newDirect2d(rocket, normalVector,rocket.getPowerTrust() * STEP);

            rocket.setFuelMass(rocket.getFuelMass() - rocket.getFuelOut() * STEP);
        }
        else {
            rocket.setTrust(false);
        }
    }

}
