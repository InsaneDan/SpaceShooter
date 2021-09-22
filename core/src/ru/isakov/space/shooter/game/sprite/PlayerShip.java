package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;
import ru.isakov.space.shooter.game.pool.ExplosionPool;
import ru.isakov.space.shooter.game.utils.MovementController;

public class PlayerShip extends BaseShip {

    public static final int HP = 50;

    private MovementController controller;

    public PlayerShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool, Sound bulletSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        controller = new MovementController(this);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletV = new Vector2(0, 0.5f);
        this.bulletPos = new Vector2();
        this.bulletHeight = 0.01f;
        this.bulletDamage = 5;
        this.shotsPerSecond = 3f;
        this.bulletSound = bulletSound;
        this.hp = HP;
    }

    public void startNewGame() {
        flushDestroy();
        this.hp = HP;
        this.controller.setToZero();
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
        super.update(delta);
        bulletPos.set(pos.x, pos.y + getHalfHeight());
        controller.update(delta);
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

}
