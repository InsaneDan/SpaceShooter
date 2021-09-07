package ru.isakov.space.shooter.game.utils;

import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.math.Rect;

public class MovementController {

    private BaseShip ship;

    public static final float MOVE_INTERVAL = 0.00005f;
    public static final float BRAKING = 0.15f;

    private Rect worldBounds;
    private Vector2 destination = new Vector2();
    private Vector2 tmp = new Vector2();
    private Vector2 touch;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;

    public MovementController(BaseShip ship) {
        this.ship = ship;
    }

    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        destination.setZero();
    }

    public void update(float delta) {
        System.out.println(destination + " " + destination.len());
        if (touch == null) {
            if (moveLeft) destination.x -= MOVE_INTERVAL / delta;
            if (moveRight) destination.x += MOVE_INTERVAL / delta;
            if (moveUp) destination.y += MOVE_INTERVAL / delta;
            if (moveDown) destination.y -= MOVE_INTERVAL / delta;
            if (destination.len() < 0.001f)  destination.setZero();
        } else {
            tmp.set(touch).sub(ship.pos).scl(delta);
            destination.add(tmp);
            if (destination.len() < 0.00001f) {
                destination.setZero();
                touch = null;
            }
        }
        ship.pos.add(destination.scl(1 - BRAKING));

        checkBoundsPointer();
    }

    private void checkBoundsPointer() {
        // проверка указателя
        if (destination.y > worldBounds.getTop()) {
            destination.y = worldBounds.getTop();
        } else if (destination.y < worldBounds.getBottom()) {
            destination.y =worldBounds.getBottom();
        }
        if (destination.x < worldBounds.getLeft()) {
            destination.x = worldBounds.getLeft();
        } else if (destination.x > worldBounds.getRight()) {
            destination.x = worldBounds.getRight();
        }
        // проверка корабля
        if (ship.getTop() > worldBounds.getTop()) {
            ship.setTop(worldBounds.getTop());
        } else if (ship.getBottom() < worldBounds.getBottom()) {
            ship.setBottom(worldBounds.getBottom());
        }
        if (ship.getLeft() < worldBounds.getLeft()) {
            ship.setLeft(worldBounds.getLeft());
        } else if (ship.getRight() > worldBounds.getRight()) {
            ship.setRight(worldBounds.getRight());
        }

    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        setToZero();
        this.touch = touch;
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        this.touch = null;
        setToZero();
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        this.touch = touch;
        return false;
    }


    public boolean keyDown(int keycode) {
        switch (keycode) {
            case 21:
                moveLeft = true;
                break;
            case 22:
                moveRight = true;
                break;
            case 19:
                moveUp = true;
                break;
            case 20:
                moveDown = true;
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case 21: moveLeft = false;
                break;
            case 22: moveRight = false;
                break;
            case 19: moveUp = false;
                break;
            case 20: moveDown = false;
                break;
        }
        return false;
    }

    private void setToZero() {
        moveLeft = false;
        moveRight = false;
        moveUp = false;
        moveDown = false;
    }

}
