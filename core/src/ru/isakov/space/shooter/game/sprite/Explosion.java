package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseSprite;

public class Explosion extends BaseSprite {

    private static final float ANIMATE_INTERVAL = 0.01f;

    private final Sound explosionSound;

    private float animateTimer;

    public Explosion(TextureAtlas atlas, Sound explosionSound) {
        super(atlas.findRegion("explosion"), 12, 12, 143);
        this.explosionSound = explosionSound;
        animateTimer = 0;
        frame = 0;
    }

    public void set(Vector2 pos, float height) {
        this.pos.set(pos);
        setHeightProportion(height);
        explosionSound.play(0.003f);
    }

    @Override
    public void update(float delta) {
        if (!this.isDestroyed()) {
            animateTimer += delta;
            if (animateTimer >= ANIMATE_INTERVAL) {
                animateTimer = 0f;
                if (++frame == regions.length) {
                    frame = 0;
                    destroy();
                }
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 1;
        animateTimer = 0f;
    }
}
