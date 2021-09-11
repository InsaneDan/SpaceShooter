package ru.isakov.space.shooter.game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;
import ru.isakov.space.shooter.game.sprite.Bullet;

public class BaseShip extends BaseSprite {

    protected Rect worldBounds;
    protected float height;
    protected Vector2 v0 = new Vector2();
    protected Vector2 v = new Vector2();

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected float bulletHeight;
    protected int bulletDamage;

    protected float shotTime;
    protected float shotsPerSecond = 1f;
    protected Sound bulletSound;

    protected int hp;

    public BaseShip() {
    }

    public BaseShip(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
//        super.update(delta);
        pos.mulAdd(v, delta);
        shotTime += delta;
        if (shotTime >= 60 / shotsPerSecond * delta) {
            shotTime = 0;
            shoot();
        }
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, bulletDamage);
        bulletSound.play(0.02f);
    }
}
