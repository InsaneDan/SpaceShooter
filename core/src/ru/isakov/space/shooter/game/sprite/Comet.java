package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseSprite;
import ru.isakov.space.shooter.game.math.Rect;

public class Comet extends BaseSprite {

    private Vector2 v;
    private Rect worldBounds;
    private float globalTime;

    public Comet(TextureAtlas atlas) {
        super(atlas.findRegion("Comet"));
        setVelocity();
        globalTime = 1f;
    }

    private void setVelocity() {
        float vx = MathUtils.random(-0.05f, 0.01f);
        float vy = MathUtils.random(-0.1f, -0.05f);
        v = new Vector2(vx, vy);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float height = MathUtils.random(0.02f, 0.035f);
        setHeightProportion(height);
        float posX = MathUtils.random(worldBounds.getLeft(), worldBounds.getRight());
        float posY = worldBounds.getTop() + 0.1f;
        pos.set(posX, posY);
    }

    @Override
    public void update(float delta) {
        globalTime -= delta;
        checkAhdHandleBounds();
        pos.mulAdd(v, delta * 25);
        this.angle = v.angle() - 180; // rotate in the same direction as "v"
    }

    public void checkAhdHandleBounds() {
        if (getRight() < worldBounds.getLeft() || getLeft() > worldBounds.getRight() || getTop() < worldBounds.getBottom()) {
            if (globalTime <= 0) {
                globalTime = MathUtils.random(2, 10);
                setVelocity();
                float posX = MathUtils.random(worldBounds.getLeft(), worldBounds.getRight());
                float posY = worldBounds.getTop() + 0.1f;
                pos.set(posX, posY);
            } else {
                v.set(0f, 0f);
            }
        }
    }
}
