package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import jdk.jfr.internal.EventInstrumentation;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.utils.MovementController;

public class PlayerShip extends BaseShip {

    private Rect worldBounds;

    private TextureRegion[] regions;

    private Vector2 tmp;
    private MovementController controller;

    public PlayerShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        controller = new MovementController(this);
        tmp = new Vector2();
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

}
