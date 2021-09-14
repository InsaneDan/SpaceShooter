package ru.isakov.space.shooter.game.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.EnemyPool;
import ru.isakov.space.shooter.game.sprite.EnemyShip;

public class EnemyEmitter {

    public static final float GENERATE_INTERVAL = 4f;

    public static final float ENEMY_SMALL_HEIGHT = 0.1f;
    public static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    public static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    public static final float ENEMY_SMALL_SHOTS_PER_SECOND = 1f;
    public static final int ENEMY_SMALL_HP = 1;

    public static final float ENEMY_MEDIUM_HEIGHT = 0.15f;
    public static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    public static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    public static final float ENEMY_MEDIUM_SHOTS_PER_SECOND = 4f;
    public static final int ENEMY_MEDIUM_HP = 5;

    public static final float ENEMY_BIG_HEIGHT = 0.2f;
    public static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    public static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    public static final float ENEMY_BIG_SHOTS_PER_SECOND = 1f;
    public static final int ENEMY_BIG_HP = 10;

    private final Vector2 enemySmallBulletV = new Vector2(0, -0.3f);
    private final Vector2 enemyMediumBulletV = new Vector2(0, -0.3f);
    private final Vector2 enemyBigBulletV = new Vector2(0, -0.3f);
    private final Vector2 enemySmallV = new Vector2(0, -0.1f);
    private final Vector2 enemyMediumV = new Vector2(0, -0.01f);
    private final Vector2 enemyBigV = new Vector2(0, -0.005f);

    private Rect worldBounds;
    private Sound bulletSound;

    private float generateTimer;

    private final TextureRegion[] enemySmallRegions;
    private final TextureRegion[] enemyMediumRegions;
    private final TextureRegion[] enemyBigRegions;
    private final TextureRegion bulletRegion;

    private final EnemyPool enemyPool;

    public EnemyEmitter(TextureAtlas atlas, EnemyPool enemyPool, Rect worldBounds, Sound bulletSound) {
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
        this.enemySmallRegions = Regions.split(atlas.findRegion("enemy0"), 1, 2, 2);
        this.enemyMediumRegions = Regions.split(atlas.findRegion("enemy1"), 1, 2, 2);
        this.enemyBigRegions = Regions.split(atlas.findRegion("enemy2"), 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.enemyPool = enemyPool;
        this.generateTimer = GENERATE_INTERVAL;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0;
            EnemyShip enemyShip = enemyPool.obtain();
            float type = MathUtils.random(0f, 1f);
            if (type < 0.5f) {
                enemyShip.set(
                        enemySmallRegions,
                        enemySmallV,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        enemySmallBulletV,
                        ENEMY_SMALL_BULLET_DAMAGE,
                        ENEMY_SMALL_SHOTS_PER_SECOND,
                        bulletSound,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP
                );
            } else if (type < 0.8f) {
                enemyShip.set(
                        enemyMediumRegions,
                        enemyMediumV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        enemyMediumBulletV,
                        ENEMY_MEDIUM_BULLET_DAMAGE,
                        ENEMY_MEDIUM_SHOTS_PER_SECOND,
                        bulletSound,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP
                );
            } else {
                enemyShip.set(
                        enemyBigRegions,
                        enemyBigV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        enemyBigBulletV,
                        ENEMY_BIG_BULLET_DAMAGE,
                        ENEMY_BIG_SHOTS_PER_SECOND,
                        bulletSound,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP
                );
            }
            enemyShip.pos.x = MathUtils.random(
                    worldBounds.getLeft() + enemyShip.getHalfWidth(),
                    worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }

}
