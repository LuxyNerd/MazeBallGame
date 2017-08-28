package org.emine.turcan.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.emine.turcan.MazeBall;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		/*
		config.title = "MazeBall";
		config.width = 800;
		config.height = 480;
		 */
		new LwjglApplication(new MazeBall(), config);
	}
}
