package ru.isakov.space.shooter.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.isakov.space.shooter.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture background;

    private Texture target;
    private Vector2 targetPosition;

    private Texture follower;
    private Vector2 followerPosition;

    private float globalTime;

    @Override
    public void show() {
        super.show();
        background = new Texture("background.jpg");
        target = new Texture("target.png");
        follower = new Texture("target.png");
        targetPosition = new Vector2();
        followerPosition = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        update(delta);
        batch.setColor(0.3f, 0.3f, 0.3f, 1.0f);
        batch.draw(background, 0, 0);
        batch.setColor(0.1f, 0.1f, 1f, 1.0f);
        batch.draw(target, targetPosition.x, targetPosition.y, (float)target.getWidth() / 2, (float)target.getHeight() / 2, target.getWidth(), target.getHeight(), 1, 1, globalTime  * 120f, 0, 0, target.getWidth(), target.getHeight(), false, false);
        float scale = (float)Math.sin(globalTime * 4) * 0.2f + 0.8f;
        float color = (float)Math.abs(Math.sin(globalTime * 4));
        batch.setColor(color, 1.0f, color, 1.0f);
        batch.draw(follower, followerPosition.x, followerPosition.y, (float)follower.getWidth() / 2, (float)follower.getHeight() / 2, follower.getWidth(), follower.getHeight(), scale, scale, -globalTime  * 120f, 0, 0, follower.getWidth(), follower.getHeight(), false, false);
        batch.end();
    }

    public void update(float delta) {
        globalTime += delta;
        Vector2 distance = targetPosition.cpy().sub(followerPosition); // TODO: 27.08.2021 уточнить, что будет с памятью при использовании copy? будет ли переполнение?
        if (distance.len() == 1) {
            followerPosition.set(targetPosition);
        } else if (distance.len() > 1) {
            followerPosition.add(distance.scl(0.001f / delta));
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        targetPosition.set(screenX - (float)target.getWidth() / 2, Gdx.graphics.getHeight() - screenY - (float)target.getHeight() / 2);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
