package ru.isakov.space.shooter.game.sprite.button;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.screen.GameScreen;

public class ButtonPlay extends ButtonTemplate {

    private static final float PADDING = 0.03f;

    private final Game game;

    public ButtonPlay(TextureAtlas atlas, String textTitle, Game game) {
        super(atlas, new Color(0,1,0,0.5f), textTitle, -1, 1f);
        this.game = game;
    }

    public void resize(Rect worldBounds) {
        super.resize(worldBounds,
                worldBounds.getLeft() + PADDING + this.getHalfWidth(),
                worldBounds.getBottom() + PADDING + this.getHalfHeight());
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }
}
