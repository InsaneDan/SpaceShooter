package ru.isakov.space.shooter.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseScreen;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.sprite.Background;
import ru.isakov.space.shooter.game.sprite.test.FollowerSprite;
import ru.isakov.space.shooter.game.sprite.test.TargetSprite;

public class TestScreen extends BaseScreen {

    private final Game game;

    private Texture backgroundTexture;
    private Background background;

    private Texture targetTexture;
    private TargetSprite target;
    private Texture followerTexture;
    private FollowerSprite follower;

    public TestScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("Textures/background.jpg");
        background = new Background(backgroundTexture);

        targetTexture = new Texture("Textures/target.png");
        target = new TargetSprite(targetTexture);
        followerTexture = new Texture("Textures/arrow.png");
        follower = new FollowerSprite(followerTexture);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        batch.begin();
        background.draw(batch);
        target.draw(batch);
        follower.draw(batch);
        batch.end();
    }

    public void update(float delta) {
        target.update(delta);
        follower.update(target, delta);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        target.pos.set(touch);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
        targetTexture.dispose();
        followerTexture.dispose();
    }
}
