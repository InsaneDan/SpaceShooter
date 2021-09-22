package ru.isakov.space.shooter.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Align;
import ru.isakov.space.shooter.game.base.BaseScreen;
import ru.isakov.space.shooter.game.base.Font;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;
import ru.isakov.space.shooter.game.pool.EnemyPool;
import ru.isakov.space.shooter.game.pool.ExplosionPool;
import ru.isakov.space.shooter.game.sprite.*;
import ru.isakov.space.shooter.game.utils.EnemyEmitter;

import java.util.List;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;
    public static final float MARGIN = 0.01f;
    public static final String FRAGS = "Frags: ";
    private static final String HP = "HP: ";
    private static final String LEVEL = "Level: ";

    private final Game game;

    private Texture bg;
    private TextureAtlas atlas;

    private Background background;
    private Star[] stars;

    private PlayerShip playerShip;
    private BulletPool bulletPool;
    private Sound playerShipShootSound;

    protected ExplosionPool explosionPool;
    private Sound explosionSound;
    private EnemyPool enemyPool;
    private EnemyEmitter enemyEmitter;
    private Sound enemyShipShootSound;

    private GameOver gameOver;
    private NewGameButton newGameButton;

    private Font font;
    private int frags;
    private StringBuilder sbFrags;
    private StringBuilder sbHP;
    private StringBuilder sbLevel;

    public GameScreen(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background.jpg");
        background = new Background(bg);

        atlas = new TextureAtlas("textures/myAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }

        bulletPool = new BulletPool();
        playerShipShootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        explosionPool = new ExplosionPool(atlas, explosionSound);
        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds);
        enemyShipShootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        enemyEmitter = new EnemyEmitter(atlas, enemyPool, worldBounds, enemyShipShootSound);
        playerShip = new PlayerShip(atlas, bulletPool, explosionPool, playerShipShootSound);

        gameOver = new GameOver(atlas);
        newGameButton = new NewGameButton(atlas, this);

        font = new Font("font/font.fnt", "font/font.png");
        font.setSize(0.02f);
        sbFrags = new StringBuilder();
        sbHP = new StringBuilder();
        sbLevel = new StringBuilder();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        playerShip.resize(worldBounds);
        gameOver.resize(worldBounds);
        newGameButton.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        playerShipShootSound.dispose();
        bulletPool.dispose();
        enemyShipShootSound.dispose();
        explosionPool.dispose();
        explosionSound.dispose();
        enemyPool.dispose();
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        if (!playerShip.isDestroyed()) {
            playerShip.update(delta);
            bulletPool.updateActiveSprites(delta);
            enemyPool.updateActiveSprites(delta);
            enemyEmitter.generate(delta, frags);
        }
        explosionPool.updateActiveSprites(delta);
    }

    private void checkCollisions() {
        if (playerShip.isDestroyed()) {
            return;
        }
        List<EnemyShip> enemyShipList = enemyPool.getActiveObjects();
        for (EnemyShip enemyShip : enemyShipList) {
            float minDst = enemyShip.getHalfWidth() + playerShip.getHalfWidth() * 0.75f;
            if (playerShip.pos.dst(enemyShip.pos) < minDst) {
                enemyShip.destroy();
                playerShip.damage(enemyShip.getBulletDamage() * 3);
            }
        }
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList) {
            if (bullet.getOwner() != playerShip) {
                if (playerShip.isCollision(bullet)) {
                    playerShip.damage(bullet.getDamage());
                    bullet.destroy();
                }
            } else {
                for (EnemyShip enemyShip : enemyShipList) {
                    if (enemyShip.isCollision(bullet)) {
                        enemyShip.damage(bullet.getDamage());
                        bullet.destroy();
                        if (enemyShip.isDestroyed()) {
                            frags++;
                        }
                    }
                }
            }
        }
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        if (!playerShip.isDestroyed()) {
            playerShip.draw(batch);
            enemyPool.drawActiveSprites(batch);
            bulletPool.drawActiveSprites(batch);
        } else {
            gameOver.draw(batch);
            newGameButton.draw(batch);
        }
        explosionPool.drawActiveSprites(batch);
        printInfo();
        batch.end();
    }

    public void startNewGame() {
        frags = 0;

        playerShip.startNewGame();

        bulletPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();

        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
    }

    public void printInfo() {
        sbFrags.setLength(0);
        font.draw(batch, sbFrags.append(FRAGS).append(frags), worldBounds.getLeft() + MARGIN, worldBounds.getTop() - MARGIN);
        sbHP.setLength(0);
        font.draw(batch, sbHP.append(HP).append(playerShip.getHp()), worldBounds.pos.x, worldBounds.getTop() - MARGIN, Align.center);
        sbLevel.setLength(0);
        font.draw(batch, sbLevel.append(LEVEL).append(enemyEmitter.getLevel()), worldBounds.getRight() - MARGIN, worldBounds.getTop() - MARGIN, Align.right);
    }

    // управление и навигация

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (!playerShip.isDestroyed()) {
            playerShip.touchDown(touch, pointer, button);
        } else {
            newGameButton.touchDown(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (!playerShip.isDestroyed()) {
            playerShip.touchUp(touch, pointer, button);
        } else {
            newGameButton.touchUp(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        if (!playerShip.isDestroyed()) {
            playerShip.touchDragged(touch, pointer);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!playerShip.isDestroyed()) {
            super.keyDown(keycode);
            playerShip.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!playerShip.isDestroyed()) {
            super.keyUp(keycode);
            playerShip.keyUp(keycode);
        }
        return false;
    }

}
