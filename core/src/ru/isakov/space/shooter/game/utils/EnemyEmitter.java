package ru.isakov.space.shooter.game.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.EnemyPool;
import ru.isakov.space.shooter.game.sprite.EnemyShip;

public class EnemyEmitter {

    public static final float GENERATE_INTERVAL = 5f;

    private final Rect worldBounds;
    private final Sound bulletSound;

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

            float type = MathUtils.random(0f, 1f);
            int shipNum;
            if (type < 0.3f) {
                shipNum = 0;
            } else if (type < 0.4f) {
                shipNum = 1;
            } else if (type < 0.5f) {
                shipNum = 2;
            } else if (type < 0.6f) {
                shipNum = 3;
            } else if (type < 0.75f) {
                shipNum = 4;
            } else if (type < 0.85f) {
                shipNum = 5;
            } else if (type < 0.92f) {
                shipNum = 6;
            } else if (type < 0.97f) {
                shipNum = 7;
            } else {
                shipNum = 8;
            }

            enemyShip.set(atlas, new EnemyShipTemplates().get(shipNum), bulletSound);
            enemyShip.pos.x = MathUtils.random(
                    worldBounds.getLeft() + enemyShip.getHalfWidth(),
                    worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }

}
