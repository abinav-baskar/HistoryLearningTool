package com.akb.hgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.akb.hgame.HGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title ="";
		//config.useGl20 = false;
		config.width = 720;
		config.height = 450;

	//	config.width = 360;
		//config.height = 225;

		new LwjglApplication(new HGame(), config);

	}
}
