package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;

public class EnemyShip extends BaseShip {

    public EnemyShip(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        bulletV = new Vector2();
        bulletPos = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        this.bulletPos.set(pos.x, pos.y - getHalfHeight());
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHight,
            Vector2 bulletV,
            int bulletDamage,
            float shotsPerSecond,
            Sound bulletSound,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0 = v0;
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHight;
        this.bulletV = bulletV;
        this.bulletDamage = bulletDamage;
        this.shotsPerSecond = shotsPerSecond;
        this.bulletSound = bulletSound;
        setHeightProportion(height);
        this.hp = hp;
        this.v = v0;
    }
}
