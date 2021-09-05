package ru.isakov.space.shooter.game.sprite.button;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.screen.TestScreen;

public class ButtonTest extends ButtonTemplate {

    private static final float PADDING = 0.03f;

    private final Game game;

    public ButtonTest(TextureAtlas atlas, Game game) {
        super(atlas, new Color(0,0,1,0.5f), "", 2, 0.3f);
        this.game = game;
    }

    public void resize(Rect worldBounds) {
        System.out.println("getWidth: " + worldBounds.getWidth() + " getHalfWidth: " + worldBounds.getHalfWidth() + " getLeft: " + worldBounds.getLeft() + " getRight: " + worldBounds.getRight());
        super.resize(worldBounds,
                0,
                worldBounds.getBottom() + PADDING + this.getHalfHeight());
    }

    @Override
    public void action() {
        game.setScreen(new TestScreen(game));
    }
}
