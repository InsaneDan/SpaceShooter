package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.math.Rect;

public class PlayerShip extends BaseShip {

    public static final float ACCELERATE_VAL = 0.0005f;
    public static final float MAX_BUMPED_TIME = 0.1f;
    public static final float DECELERATION = 0.04f;

    private Rect worldBounds;

    private TextureRegion[] regions;

    private Vector2 destination = new Vector2(-1, -1);
    private Vector2 moveDirection = new Vector2(0,0);
    private Vector2 tmp = new Vector2();

    private float bumpedTime;
    private float accelerateLeft;
    private float accelerateRight;
    private float accelerateUp;
    private float accelerateDown;

    public PlayerShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship").split(195, 287)[0][0]);
        regions = atlas.findRegion("main_ship").split(195, 287)[0]; // TODO 05.09.2021: при столкновении с границей - показать 2 фрейм
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.1f);
        setLeft(worldBounds.getLeft() + MathUtils.random(0.2f, 0.4f));
        setBottom(worldBounds.getBottom());
    }

    @Override
    public void update(float delta) {
        checkBounds();
        checkAcceleration(delta);
        if (destination.x != -1 && destination.y != -1) {
            if (tmp.set(destination).sub(pos).len() < 0.01f) {
                stopAccelerate();
                moveDirection.scl(0.1f);
                destination.set(new Vector2(-1,-1));
            }
        }

        if (bumpedTime > 0) {
            bumpedTime -= delta;
        } else {
            bumpedTime = 0;
        }

        moveDirection.scl(1 - DECELERATION);
        pos.add(moveDirection);
    }

    private void checkAcceleration(float delta) {
        // если клавиша нажата и не отпущена - прибавляем ходу
        if (accelerateLeft != 0) {
            accelerateLeft += accelerateLeft * delta - accelerateRight;
            moveDirection.x -= accelerateLeft;
        }
        if (accelerateRight != 0) {
            accelerateRight += accelerateRight * delta - accelerateLeft;
            moveDirection.x += accelerateRight;
        }
        if (accelerateUp != 0) {
            accelerateUp += accelerateUp * delta - accelerateDown;
            moveDirection.y += accelerateUp;
        }
        if (accelerateDown != 0) {
            accelerateDown += accelerateDown * delta - accelerateUp;
            moveDirection.y -= accelerateDown;
        }
    }

    private void checkBounds() {
        // если выскочил за границы - сбросить ускорение до 0
        if (getTop() > worldBounds.getTop()) {
            setTop(worldBounds.getTop() - 0.001f); // без этой поправки немножко уходит за границу
            accelerateUp = 0;
            moveDirection.y = 0;
            bumpedTime = MAX_BUMPED_TIME;
        } else if (getBottom() < worldBounds.getBottom()) {
            setBottom(worldBounds.getBottom());
            accelerateDown = 0;
            moveDirection.y = 0;
            bumpedTime = MAX_BUMPED_TIME;
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            accelerateLeft = 0;
            moveDirection.x = 0;
            bumpedTime = MAX_BUMPED_TIME;
        } else if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            accelerateRight = 0;
            moveDirection.x = 0;
            bumpedTime = MAX_BUMPED_TIME;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (bumpedTime > 0) {
            batch.setColor(1, 0, 0, 1);
        }
        super.draw(batch);
        batch.setColor(1,1,1,1);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        destination.set(touch).sub(pos);
        moveDirection.set(destination).setLength(0.001f);

        setAccelerateDirection();

        return false;
    }

    private void setAccelerateDirection() {
        if (moveDirection.x < 0) {
            accelerateLeft = -moveDirection.x; // left
//            accelerateRight = 0;
        } else  if (moveDirection.x > 0) {
            accelerateRight = moveDirection.x; // right
//            accelerateLeft = 0;
        }

        if (moveDirection.y > 0) {
            accelerateUp = moveDirection.y; // up
//            accelerateDown = 0;
        } else if (moveDirection.y < 0) {
            accelerateDown = -moveDirection.y; // up
//            accelerateUp = 0;
        }
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        stopAccelerate();
        return false;
    }

    private void stopAccelerate() {
        accelerateLeft = 0;
        accelerateRight = 0;
        accelerateUp = 0;
        accelerateDown = 0;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        destination.set(touch).sub(pos);
        moveDirection.set(destination).setLength(0.001f);
        stopAccelerate();
        setAccelerateDirection();
        return false;
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case 21:
                accelerateLeft = ACCELERATE_VAL; // left
                accelerateRight = 0;
                break;
            case 22:
                accelerateRight = ACCELERATE_VAL; // right
                accelerateLeft = 0;
                break;
            case 19:
                accelerateUp = ACCELERATE_VAL; // up
                accelerateDown = 0;
                break;
            case 20:
                accelerateDown = ACCELERATE_VAL; // down
                accelerateUp = 0;
                break;
            case 62:
                System.out.println("FIRE!");
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case 21: accelerateLeft = 0;
                break;
            case 22: accelerateRight = 0;
                break;
            case 19: accelerateUp = 0;
                break;
            case 20: accelerateDown = 0;
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
