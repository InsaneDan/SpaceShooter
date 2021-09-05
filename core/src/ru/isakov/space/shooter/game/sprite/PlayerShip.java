package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.base.BaseSprite;
import ru.isakov.space.shooter.game.math.Rect;

public class PlayerShip extends BaseShip {

    private Rect worldBounds;

    private TextureRegion[] regions;

    private BaseSprite destination;
    private Vector2 tmp;

    private float bumpedTime;
    public static final float MOVE_INTERVAL = 0.0002f;

    private float moveLeft;
    private float moveRight;
    private float moveUp;
    private float moveDown;

    public PlayerShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship").split(195, 287)[0][0]);
        regions = atlas.findRegion("main_ship").split(195, 287)[0];
        destination = new BaseSprite(atlas.findRegion("main_ship").split(195, 287)[0][1]);
        destination.pos.set(this.pos);
        tmp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float heightProprotion = 0.1f;
        setHeightProportion(heightProprotion);
        destination.setHeightProportion(heightProprotion);
        setLeft(0f);
        setBottom(0f);
        destination.setLeft(0f);
        destination.setBottom(0f);
    }

    @Override
    public void update(float delta) {
        destination.pos.x += (moveRight - moveLeft) / delta;
        destination.pos.y += (moveUp - moveDown) / delta;
        checkBoundsPointer();

        if (bumpedTime > 0) bumpedTime -= delta;

        if (!destination.equals(pos)) {
            tmp.set(destination.pos);
            pos.mulAdd(tmp.sub(pos), 0.1f);
        }
    }

    private void checkBoundsPointer() {
        // если выскочил за границы - сбросить ускорение до 0
        if (destination.getTop() > worldBounds.getTop()) {
            destination.setTop(worldBounds.getTop() - 0.001f); // без этой поправки немножко уходит за границу
        } else if (destination.getBottom() < worldBounds.getBottom()) {
            destination.setBottom(worldBounds.getBottom());
        }
        if (destination.getLeft() < worldBounds.getLeft()) {
            destination.setLeft(worldBounds.getLeft());
        } else if (destination.getRight() > worldBounds.getRight()) {
            destination.setRight(worldBounds.getRight());
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (bumpedTime > 0) {
            batch.setColor(1, 0, 0, 1);
        }
        batch.setColor(1,1,1,0.3f);
        destination.draw(batch);
        batch.setColor(1,1,1,1);
        super.draw(batch);
        batch.setColor(1,1,1,1);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        destination.pos.set(touch);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        destination.pos.set(touch);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        destination.pos.set(touch);
        return false;
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case 21:
                moveLeft = MOVE_INTERVAL; // left
                break;
            case 22:
                moveRight = MOVE_INTERVAL; // right
                break;
            case 19:
                moveUp = MOVE_INTERVAL; // up
                break;
            case 20:
                moveDown = MOVE_INTERVAL; // down
                break;
            case 62:
                System.out.println("FIRE!");
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case 21: moveLeft = 0;
                break;
            case 22: moveRight = 0;
                break;
            case 19: moveUp = 0;
                break;
            case 20: moveDown = 0;
                break;
            case 62:
                System.out.println("STOP FIRE!");
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
