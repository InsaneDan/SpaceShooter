package ru.isakov.space.shooter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpaceShooter extends ApplicationAdapter {

	private final static float SCREEN_HEIGHT = 720f;
	private final static float SCREEN_WIDTH = 1280f;

	SpriteBatch batch;
	Texture img;
	TextureRegion badRegion;
	Texture background;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.jpg");
		img = new Texture("badlogic.jpg");
		badRegion = new TextureRegion(img, 50, 0, 135, 60);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.setColor(1f, 1f, 1f, .5f);
		batch.draw(background, 0, 0);
		batch.setColor(0f, 0f, 1f, .3f);
		// TODO: 27.08.2021 уточнить, нужно ли приведение типов?
		batch.draw(img, SCREEN_WIDTH / 2 - (float) img.getWidth() / 2, SCREEN_HEIGHT / 2 - (float) img.getHeight() / 2);
		batch.setColor(1f, 1f, 1f, 1f);
		batch.draw(badRegion, SCREEN_WIDTH / 2 - (float) badRegion.getRegionWidth() / 2, SCREEN_HEIGHT / 2 - (float) badRegion.getRegionHeight() / 2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		background.dispose();
	}
}
