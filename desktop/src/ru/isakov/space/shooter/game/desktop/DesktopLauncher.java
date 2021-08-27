package ru.isakov.space.shooter.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.isakov.space.shooter.game.SpaceShooter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// FIXME: 27.08.2021 размер приложения должен зависеть от параметров экрана
		config.width = 1280;
		config.height = 720;

		new LwjglApplication(new SpaceShooter(), config);
	}
}
