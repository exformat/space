package io.exformat.space.framework.impl;
import android.graphics.*;
import android.view.*;

public class AndroidFastRenderView extends SurfaceView implements Runnable{
	
	AndroidGame game; 
    Bitmap framebuffer; 
    Thread renderThread = null; 
    SurfaceHolder holder; 
    volatile boolean running = false;

	Canvas canvas;

	public AndroidFastRenderView(AndroidGame game, Bitmap framebuffer) {
		super(game);
		this.game = game;
		this.framebuffer = framebuffer;
		this.holder = getHolder();
	}
	
	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	public void run() {
		
		Rect dstRect = new Rect();
		long startTime = System.nanoTime();
		
		while(running) {
			
			if(!holder.getSurface().isValid())
				continue;
			float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
			startTime = System.nanoTime();

			try {


				game.getCurrentScreen().update(deltaTime);
				game.getCurrentScreen().present(deltaTime);

				canvas = holder.lockCanvas();
				canvas.getClipBounds(dstRect);
				canvas.drawBitmap(framebuffer, null, dstRect, null);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			finally {
				try {
					holder.unlockCanvasAndPost(canvas);
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}






			/*
			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().present(deltaTime);
			
			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(framebuffer, null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);
			*/
		}
	}

	public void pause() {
        running = false;
        while(true) {
            try {             
				renderThread.join();
                break; 
            } catch (InterruptedException e) {
                // повтор
            }
        }
	}
}
