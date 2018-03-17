package io.exformat.space.framework.impl;

import android.app.*;
import android.content.res.*;
import android.media.*;
import io.exformat.space.framework.*;
import java.io.*;

public class AndroidAudio implements Audio {

	AssetManager assets;
    SoundPool soundPool;
	
	public AndroidAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	
	@Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Невозможно загрузить музыку '" +
									   filename + "'");
        }
    }

	@Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Невозможно загрузить звук '" +
									   filename + "'");
        }
    }

}
