package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;
import ru.isakov.space.shooter.game.pool.ExplosionPool;

public class EnemyShip extends BaseShip {

    public static final Vector2 startV = new Vector2(0, -0.3f);

    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletV = new Vector2();
        this.bulletPos = new Vector2();
        this.explosionPool = explosionPool;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (this.getTop() < worldBounds.getTop() && this.getBottom() > worldBounds.getBottom()) {
            v.set(v0);
        } else {
            this.v.set(startV); // начальная скорость для быстрого появления на экране
            shotTime = 60 / shotsPerSecond * delta * 0.7f;
        }
        this.bulletPos.set(pos.x, pos.y - getHalfHeight());
        if (getBottom() < worldBounds.getBottom()) {
            destroySilently();
        }
    }
}
