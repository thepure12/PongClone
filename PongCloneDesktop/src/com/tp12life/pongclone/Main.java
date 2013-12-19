package com.tp12life.pongclone;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "PongClone";
		cfg.useGL20 = false;
		cfg.width = 500;
		cfg.height = 500;
		cfg.resizable = false;
		new LwjglApplication(new PongClone(), cfg);
	}
}
