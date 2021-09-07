package ru.isakov.space.shooter.game.pool;

import ru.isakov.space.shooter.game.base.SpritesPool;
import ru.isakov.space.shooter.game.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

}
