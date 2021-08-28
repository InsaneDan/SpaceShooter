package ru.isakov.space.shooter.game;

import com.badlogic.gdx.Game;

import ru.isakov.space.shooter.game.screen.MenuScreen;

public class SpaceShooter extends Game {

	@Override
	public void create () {
		setScreen(new MenuScreen());
	}

}
