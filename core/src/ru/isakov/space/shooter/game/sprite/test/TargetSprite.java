package ru.isakov.space.shooter.game.sprite.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseSprite;
import ru.isakov.space.shooter.game.math.Rect;

public class TargetSprite extends BaseSprite {

    private static final float RPS = 0.5f; // rotation - rounds per second
    private float globalTime;

    public TargetSprite(Texture texture) {
        super(new TextureRegion(texture));
        setSize(0.05f, 0.05f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(new Vector2(0.5f, 0.5f));
        setHeightProportion(worldBounds.getHeight());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        globalTime += delta;
        if (globalTime >= 1) globalTime = -1;
        this.angle = globalTime * 360f * RPS; // angle to degrees
    }
}
