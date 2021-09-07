package ru.isakov.space.shooter.game.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseButton extends BaseSprite {

    private static final float PRESS_SCALE = 0.6f;

    private int pointer;
    protected boolean pressed;
    protected float initialScale;

    public BaseButton(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (pressed || !isMe(touch)) {
            return false;
        }
        this.pointer = pointer;
        pressed = true;
        scaleX = PRESS_SCALE;
        scaleY = PRESS_SCALE;
        return true;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (this.pointer != pointer || !pressed) {
            return false;
        }
        if (isMe(touch)) {
            action();
        }
        pressed = false;
        scaleX = initialScale;
        scaleY = initialScale;
        return true;
    }

    public abstract void action();
}
