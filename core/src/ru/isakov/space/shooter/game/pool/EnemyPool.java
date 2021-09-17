package ru.isakov.space.shooter.game.pool;

import com.badlogic.gdx.math.Interpolation;
import ru.isakov.space.shooter.game.base.SpritesPool;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final ExplosionPool explosionPool;
    private final Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, explosionPool, worldBounds);
    }
}
