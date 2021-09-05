package ru.isakov.space.shooter.game.sprite.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.isakov.space.shooter.game.math.Rect;

public class ButtonExit extends ButtonTemplate {

    private static final float PADDING = 0.03f;

    public ButtonExit(TextureAtlas atlas, String textTitle) {
        super(atlas, new Color(1,0,0,0.5f), textTitle, 1, 1f);
    }

    public void resize(Rect worldBounds) {
        super.resize(worldBounds,
                worldBounds.getRight() - PADDING - this.getHalfWidth(),
                worldBounds.getBottom() + PADDING + this.getHalfHeight());
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
