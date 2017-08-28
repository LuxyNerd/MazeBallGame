package org.emine.turcan;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		/*
		//vorerst auf false
		config.useAccelerometer = false;
		config.useCompass = false;
		*/
		config.useAccelerometer = true;
		initialize(new MazeBall(), config);
	}
}
