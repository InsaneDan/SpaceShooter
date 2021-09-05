package ru.isakov.space.shooter.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.isakov.space.shooter.game.base.BaseScreen;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.sprite.Background;
import ru.isakov.space.shooter.game.sprite.Comet;
import ru.isakov.space.shooter.game.sprite.button.ButtonExit;
import ru.isakov.space.shooter.game.sprite.button.ButtonPlay;
import ru.isakov.space.shooter.game.sprite.Star;
import ru.isakov.space.shooter.game.sprite.button.ButtonTemplate;
import ru.isakov.space.shooter.game.sprite.button.ButtonTest;

public class MenuScreen extends BaseScreen {

    private static final int STAR_COUNT = 256;

    private final Game game;

    private Texture bg;
    private TextureAtlas atlas;
    private TextureAtlas btnAtlas;

    private Background background;
    private Star[] stars;
    private Comet comet;

    private ButtonPlay buttonPlay;
    private ButtonExit buttonExit;
    private ButtonTest buttonTest;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background.jpg");
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        btnAtlas = new TextureAtlas("Textures/Button/BtnAtlas.atlas");

        background = new Background(bg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        comet = new Comet(btnAtlas);
        buttonPlay = new ButtonPlay(btnAtlas, "Play", game);
        buttonExit = new ButtonExit(btnAtlas, "Exit");
        buttonTest = new ButtonTest(btnAtlas, game);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        comet.resize(worldBounds);
        buttonPlay.resize(worldBounds);
        buttonExit.resize(worldBounds);
        buttonTest.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        buttonPlay.touchDown(touch, pointer, button);
        buttonExit.touchDown(touch, pointer, button);
        buttonTest.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        buttonPlay.touchUp(touch, pointer, button);
        buttonExit.touchUp(touch, pointer, button);
        buttonTest.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        comet.update(delta);
        buttonPlay.update(delta);
        buttonExit.update(delta);
        buttonTest.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        comet.draw(batch);
        buttonPlay.draw(batch);
        buttonExit.draw(batch);
        buttonTest.draw(batch);
        batch.end();
    }
}
