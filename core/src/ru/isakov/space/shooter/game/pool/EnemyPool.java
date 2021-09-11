package ru.isakov.space.shooter.game.pool;

import ru.isakov.space.shooter.game.base.SpritesPool;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, worldBounds);
    }
}
