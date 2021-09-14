package ru.isakov.space.shooter.game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;
import ru.isakov.space.shooter.game.sprite.Bullet;

public class BaseShip extends BaseSprite {

    // общие параметры
    protected Rect worldBounds;

    // параметры корабля
    protected float height;
    protected Vector2 v0 = new Vector2();
    protected Vector2 v = new Vector2();
    protected int hp;

    // параметры пули
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected float bulletHeight;
    protected int bulletDamage;
    // параметры выстрела
    protected float shotTime;
    protected float shotsPerSecond = 1f;
    protected Sound bulletSound;
    protected float shotVolume;

    public BaseShip() {
    }

    public BaseShip(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            int hp,
            TextureRegion bulletRegion,
            float bulletHeight,
            Vector2 bulletV,
            int bulletDamage,
            float shotsPerSecond,
            Sound bulletSound,
            float height,
            float shotVolume
    ) {
        this.regions = regions;
        setHeightProportion(height);
        this.v0 = v0;
        this.v = v0;
        this.hp = hp;
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV = bulletV;
        this.bulletDamage = bulletDamage;
        this.shotsPerSecond = shotsPerSecond;
        this.bulletSound = bulletSound;
        this.shotVolume = shotVolume;
    }

    @Override
    public void update(float delta) {
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
        bulletSound.play(shotVolume);
    }

    @Override
    public void destroy() {
        super.destroy();
        shotTime = 0;
    }
}
