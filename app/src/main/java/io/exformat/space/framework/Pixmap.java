package io.exformat.space.framework;

import android.graphics.Bitmap;

import io.exformat.space.framework.Graphics.PixmapFormat;

public interface Pixmap
{
	
	public int getWidth();
    public int getHeight();
	
    public PixmapFormat getFormat();
	
    public void dispose();

    public Bitmap getBitmap();
    public void setBitmap(Bitmap bitmap);

}
