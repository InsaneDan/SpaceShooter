package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;
import ru.isakov.space.shooter.game.pool.ExplosionPool;
import ru.isakov.space.shooter.game.utils.EnemySettingsTemplate;
import ru.isakov.space.shooter.game.utils.Regions;

public class EnemyShip extends BaseShip {

    public static final Vector2 startV = new Vector2(0, -0.25f);

    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletV = new Vector2();
        this.bulletPos = new Vector2();
        this.explosionPool = explosionPool;
        this.v = startV; // начальная скорость для быстрого появления на экране
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getTop() < worldBounds.getTop() && getBottom() > worldBounds.getBottom()) {
            v.set(v0);
        } else {
            shotTime = 60 / shotsPerSecond * delta * 0.7f;
        }
        this.bulletPos.set(pos.x, pos.y - getHalfHeight());
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }



    // TODO вынести isCollision в родительский класс, top-bottom определить через getOwner
    public boolean isCollision(Rect rect) {
        return !(
                rect.getRight() < getLeft()
                        || rect.getLeft() > getRight()
                        || rect.getBottom() > getTop()
                        || rect.getTop() < pos.y
        );
    }
}
