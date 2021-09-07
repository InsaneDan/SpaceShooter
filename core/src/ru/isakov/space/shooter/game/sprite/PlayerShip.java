package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;
import ru.isakov.space.shooter.game.utils.MovementController;

public class PlayerShip extends BaseShip {

    private Rect worldBounds;

    private MovementController controller;

    private final BulletPool bulletPool;
    private final TextureRegion bulletRegion;
    private final Vector2 bulletV;
    private final Vector2 bulletPos;
    private final float bulletHeight;
    private final int bulletDamage;

    private float shotTime;
    private final float shotsPerSecond = 20f;

    private Sound shotSound;

    public PlayerShip(TextureAtlas atlas, BulletPool bulletPool, Sound shotSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        controller = new MovementController(this);
        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV = new Vector2(0, 0.5f);
        bulletPos = new Vector2();
        bulletHeight = 0.01f;
        bulletDamage = 1;
        this.shotSound = shotSound;
        shotSound.setVolume(1, 0.1f);

    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float heightProprotion = 0.1f;
        setHeightProportion(heightProprotion);
        this.pos.setZero();
        controller.resize(worldBounds);
    }

    @Override
    public void update(float delta) {
        shotTime += delta;
        if (shotTime >= 60 / shotsPerSecond * delta) {
            shoot();
            shotTime = 0;
        }
        controller.update(delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(1,1,1,1);
        super.draw(batch);
        batch.setColor(1,1,1,1);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return controller.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return controller.touchUp(touch, pointer, button);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        return controller.touchDragged(touch, pointer);
    }


    @Override
    public boolean keyDown(int keycode) {
        return controller.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return controller.keyUp(keycode);
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bulletPos.set(pos.x, pos.y + getHalfHeight());
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, bulletDamage);
        shotSound.play(0); // FIXME 07.09.2021 отрегулировать звуки выстрелов - перекрывают музыку, раздражают )
    }
}
