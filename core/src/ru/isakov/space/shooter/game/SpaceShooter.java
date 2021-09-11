package ru.isakov.space.shooter.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import ru.isakov.space.shooter.game.screen.MenuScreen;

public class SpaceShooter extends Game {

	Music music;

	@Override
	public void create () {

		setScreen(new MenuScreen(this));

		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
//		music.play(); // FIXME не забыть включить музыку )
	}

	@Override
	public void dispose() {
		music.dispose();
		super.dispose();
	}

}
