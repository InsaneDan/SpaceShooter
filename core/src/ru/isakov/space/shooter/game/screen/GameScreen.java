package ru.isakov.space.shooter.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.isakov.space.shooter.game.base.BaseScreen;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.sprite.Background;
import ru.isakov.space.shooter.game.sprite.PlayerShip;
import ru.isakov.space.shooter.game.sprite.Star;

public class GameScreen extends BaseScreen {

    private final Game game;

    private static final int STAR_COUNT = 64;

    private Texture bg;
    private TextureAtlas atlas;

    private Background background;
    private Star[] stars;

    private PlayerShip playerShip;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background.jpg");
        background = new Background(bg);

        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }

        playerShip = new PlayerShip(atlas);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        playerShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        playerShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        playerShip.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        playerShip.touchDragged(touch, pointer);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        playerShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        super.keyUp(keycode);
        playerShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        super.keyTyped(character);
        playerShip.keyTyped(character);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        playerShip.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        playerShip.draw(batch);
        batch.end();
    }
}
