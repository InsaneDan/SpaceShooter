package ru.isakov.space.shooter.game.sprite.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseSprite;
import ru.isakov.space.shooter.game.math.Rect;

public class FollowerSprite extends BaseSprite {

    private float speed;
    private Vector2 direction;
    private float globalTime;
    private static final float rotationTime = 2f;

    public FollowerSprite(Texture texture) {
        super(new TextureRegion(texture));
        setSize(0.05f, 0.05f);
        direction = new Vector2(1, 0);
        speed = 0f;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(new Vector2(0.5f, 0.5f));
        setHeightProportion(worldBounds.getHeight());
    }

    private void rotate(Vector2 target, float delta) {
        // rotate within the less angle
        float rotationAngle = 360 / rotationTime * delta;
        if (Math.abs(target.sub(pos).len()) > 0.01f) {
            if (!direction.cpy().rotateDeg(90f).hasSameDirection(target)) {
                direction.rotateDeg(-rotationAngle);
                angle = direction.angleDeg();
            }
            if (!direction.cpy().rotateDeg(-90f).hasSameDirection(target)) {
                direction.rotateDeg(rotationAngle);
                angle = direction.angleDeg();
            }
        }
    }

    public void update(TargetSprite targetSprite, float delta) {
        super.update(delta);
        globalTime += delta;
        if (globalTime >= 1) globalTime = -1;
        this.scaleX = Math.abs(globalTime) * 0.2f + 0.8f;
        this.scaleY = Math.abs(globalTime) * 0.2f + 0.8f;

        rotate(targetSprite.pos.cpy(), delta);

        if (pos.cpy().sub(targetSprite.pos).len() <= 0.01f) {
            pos.set(targetSprite.pos);
            speed = 0f;
        } else {
            if (speed < 0.01f) speed += 0.005f * delta;
        }
        pos.add(direction.cpy().scl(speed));

    }

    @Override
    public void draw(SpriteBatch batch) {
        float color = Math.abs(globalTime);
        batch.setColor(color, 1.0f, color, 1.0f);
        super.draw(batch);
        batch.setColor(1,1,1,1);
    }

}
