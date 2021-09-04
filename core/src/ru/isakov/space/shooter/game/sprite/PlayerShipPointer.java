package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseSprite;
import ru.isakov.space.shooter.game.math.Rect;

public class PlayerShipPointer extends BaseSprite {

    private Rect worldBounds;


    public PlayerShipPointer(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeight(5f);
        setLeft(worldBounds.getLeft() + 0.1f);
        setBottom(worldBounds.getBottom() + 0.1f);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        return super.touchDragged(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }
}
