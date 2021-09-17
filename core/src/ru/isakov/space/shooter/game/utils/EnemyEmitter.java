package ru.isakov.space.shooter.game.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.EnemyPool;
import ru.isakov.space.shooter.game.pool.ExplosionPool;
import ru.isakov.space.shooter.game.sprite.EnemyShip;

public class EnemyEmitter {

    public static final float GENERATE_INTERVAL = 5f;
    private static int counter = 0;
//    private final Vector2 enemySmallV = new Vector2(0, -0.1f);
//    private final Vector2 enemySmallBulletV = new Vector2(0, enemySmallV.y - 0.2f);
//
//    private final Vector2 enemyMediumV = new Vector2(0, -0.05f);
//    private final Vector2 enemyMediumBulletV = new Vector2(0, enemyMediumV.y - 0.2f);
//
//    private final Vector2 enemyBigV = new Vector2(0, -0.005f);
//    private final Vector2 enemyBigBulletV = new Vector2(0, enemyBigV.y - 0.3f);

    private Rect worldBounds;
    private Sound bulletSound;

    private float generateTimer;

    private final EnemyPool enemyPool;

    private final TextureAtlas atlas;

    public EnemyEmitter(TextureAtlas atlas, EnemyPool enemyPool, Rect worldBounds, Sound bulletSound) {
        this.atlas = atlas;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
        this.enemyPool = enemyPool;
        this.generateTimer = GENERATE_INTERVAL;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0;
            EnemyShip enemyShip = enemyPool.obtain();

            // для проверки всех типов кораблей
            enemyShip.set(atlas, new EnemyShipTemplates().get(counter), bulletSound);
            counter++;
            if (counter > 8) counter = 0;

//            float type = MathUtils.random(0f, 1f);
//            if (type < 0.5f) {
//                enemyShip.set(atlas, new EnemyShipTemplates().get(0), bulletSound);
//            } else if (type < 0.8f) {
//                enemyShip.set(atlas, new EnemyShipTemplates().get(1), bulletSound);
//            } else {
//                enemyShip.set(atlas, new EnemyShipTemplates().get(2), bulletSound);
//            }
            enemyShip.pos.x = MathUtils.random(
                    worldBounds.getLeft() + enemyShip.getHalfWidth(),
                    worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }

}
