package ru.isakov.space.shooter.game.base;

import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;
import ru.isakov.space.shooter.game.pool.ExplosionPool;
import ru.isakov.space.shooter.game.sprite.Bullet;
import ru.isakov.space.shooter.game.sprite.Explosion;
import ru.isakov.space.shooter.game.utils.EnemySettingsTemplate;
import ru.isakov.space.shooter.game.utils.Regions;

public class BaseShip extends BaseSprite {

    public static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;
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

    private float getDamageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;
    protected ExplosionPool explosionPool;

    public BaseShip() {
    }

    public BaseShip(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

//    public void set(
//            TextureRegion[] regions,
//            Vector2 v0,
//            int hp,
//            TextureRegion bulletRegion,
//            float bulletHeight,
//            Vector2 bulletV,
//            int bulletDamage,
//            float shotsPerSecond,
//            Sound bulletSound,
//            float height,
//            float shotVolume
//    ) {
//        this.regions = regions;
//        setHeightProportion(height);
//        this.v0 = v0;
//        this.v = v0;
//        this.hp = hp;
//        this.bulletRegion = bulletRegion;
//        this.bulletHeight = bulletHeight;
//        this.bulletV = bulletV;
//        this.bulletDamage = bulletDamage;
//        this.shotsPerSecond = shotsPerSecond;
//        this.bulletSound = bulletSound;
//        this.shotVolume = shotVolume;
//    }

    public void set(TextureAtlas atlas, EnemySettingsTemplate enemySettingsTemplate, Sound bulletSound) {
        this.regions = Regions.split(atlas.findRegion(enemySettingsTemplate.getShipRegionName()), 1, 2, 2);
        setHeightProportion(enemySettingsTemplate.getHeight());
        this.v0.set(enemySettingsTemplate.getV());
        this.hp = enemySettingsTemplate.getHp();
        this.bulletRegion = atlas.findRegion(enemySettingsTemplate.getBulletRegionName());
        this.bulletHeight = enemySettingsTemplate.getBulletHight();
        this.bulletV.set(enemySettingsTemplate.getBulletV());
        this.bulletDamage = enemySettingsTemplate.getBulletDamage();
        this.bulletPos.set(pos.x, pos.y - getHalfHeight());
        this.shotsPerSecond = enemySettingsTemplate.getShotsPerSecond();
        this.shotVolume = enemySettingsTemplate.getShotVolume();
        this.bulletSound = bulletSound;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        shotTime += delta;
        if (shotTime >= 60 / shotsPerSecond * delta) {
            shotTime = 0;
            shoot();
        }

        getDamageAnimateTimer += delta;
        if (getDamageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
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
        boom();
    }

    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
        frame = 1;
        getDamageAnimateTimer = 0;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void boom() {
        if (explosionPool != null) {
            Explosion explosion = explosionPool.obtain();
            explosion.set(pos, getHeight() * 2);
        }
    }

    public boolean isCollision(Rect rect) {
        if (rect instanceof Bullet) {
            Bullet bullet = (Bullet) rect;

            boolean bulletTopBottom;
            if (bullet.getOwner().pos.y >= bullet.pos.y) {
                bulletTopBottom = rect.getBottom() > pos.y || rect.getTop() < getBottom(); // playerShip conditions
            } else {
                bulletTopBottom = rect.getBottom() > getTop() || rect.getTop() < pos.y; // enemyShip conditions
            }

            return !(rect.getRight() < getLeft() ||
                    rect.getLeft() > getRight() ||
                    bulletTopBottom);
        }
        return false;
    }
}
