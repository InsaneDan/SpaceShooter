package ru.isakov.space.shooter.game;

import com.badlogic.gdx.Game;

import ru.isakov.space.shooter.game.screen.GameScreen;
import ru.isakov.space.shooter.game.screen.MenuScreen;
import ru.isakov.space.shooter.game.screen.TestScreen;

public class SpaceShooter extends Game {
	
	@Override
	public void create () {
//		setScreen(new TestScreen(this));
//		setScreen(new MenuScreen(this));
		setScreen(new GameScreen(this));
	}

}
