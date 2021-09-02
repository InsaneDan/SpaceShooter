package ru.isakov.space.shooter.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.isakov.space.shooter.game.base.BaseScreen;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.sprite.Background;
import ru.isakov.space.shooter.game.sprite.FollowerSprite;
import ru.isakov.space.shooter.game.sprite.TargetSprite;

public class MenuScreen extends BaseScreen {

    private Texture backgroundTexture;
    private Background background;

    private Texture targetTexture;
    private TargetSprite target;
    private Texture followerTexture;
    private FollowerSprite follower;

    private Vector2 tmp;

    private float globalTime;

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("background.jpg");
        background = new Background(backgroundTexture);

        targetTexture = new Texture("target.png");
        target = new TargetSprite(targetTexture);
        followerTexture = new Texture("arrow.png");
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
//        batch.draw(target, 0, 0, 0.1f, 0.1f);
//        batch.setColor(0.3f, 0.3f, 0.3f, 1.0f);
//        batch.draw(backgroundTexture, -0.5f, 0f, 1f, 1f);
//        batch.setColor(0.1f, 0.1f, 1f, 1.0f);
//        batch.draw(target, targetPosition.x, targetPosition.y, (float)target.getWidth() / 2, (float)target.getHeight() / 2, target.getWidth(), target.getHeight(), 1, 1, globalTime  * 120f, 0, 0, target.getWidth(), target.getHeight(), false, false);
//        float scale = (float)Math.sin(globalTime * 4) * 0.2f + 0.8f;
//        float color = (float)Math.abs(Math.sin(globalTime * 4));
//        batch.setColor(color, 1.0f, color, 1.0f);
//        batch.draw(follower, followerPosition.x, followerPosition.y, (float)follower.getWidth() / 2, (float)follower.getHeight() / 2, follower.getWidth(), follower.getHeight(), scale, scale, -globalTime  * 120f, 0, 0, follower.getWidth(), follower.getHeight(), false, false);
        batch.end();
    }

    public void update(float delta) {
        globalTime += delta;
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
//        follower.dispose();
    }
}
