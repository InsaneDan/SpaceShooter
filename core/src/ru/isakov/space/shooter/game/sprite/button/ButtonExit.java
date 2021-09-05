package ru.isakov.space.shooter.game.sprite.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseButton;
import ru.isakov.space.shooter.game.math.Rect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonExit extends BaseButton {

    private static final float HEIGHT = 0.2f;
    private static final float PADDING = 0.03f;

    private final List<ButtonElement> buttonElements = new ArrayList<>();
    private Color color = new Color(1,0,0,0.5f);

    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("circle"));
        float scale = 1f;
        int rotateClockwise = 1;
        buttonElements.addAll(Arrays.asList(
                new ButtonElement(atlas, "Blades30", pos, scale, rotateClockwise * -0.2f, 0, 0, 0, 0, color),
                new ButtonElement(atlas, "Blades45", pos, scale, rotateClockwise * 0.4f, 0, 0, 0, 0, color),
                new ButtonElement(atlas, "Blades45", pos, scale, rotateClockwise * 0.6f, 0, 0, 0, 0, color),
                new ButtonElement(atlas, "Blades60", pos, scale, rotateClockwise * -0.3f, 0, 0, 0, 0, color),
                new ButtonElement(atlas, "Blades90", pos, scale, rotateClockwise * 0.1f, 0, 0, 0, 0, color),
                new ButtonElement(atlas, "Blades90", pos, scale, rotateClockwise * -0.5f, 0, 0, 0, 0, color),

                new ButtonElement(atlas, "CirclesGradient", pos, scale, 0, 0, 0, 0, 0, color),
                new ButtonElement(atlas, "CirclesInner", pos, scale, 0, 0, 0, 0, 0, color),

                new ButtonElement(atlas, "CirclesOuter1", pos, scale, 0, 0, 1f, 0f, 0.5f, color),
                new ButtonElement(atlas, "CirclesOuter2", pos, scale, 0, 0, 0f, 1f, 0, color),

                new ButtonElement(atlas, "Exit", pos, 1.3f, 0, 0, 0f, 0, 0, new Color(1, 0.7f,0.7f, 1))
        ));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setRight(worldBounds.getRight() - PADDING);
        setBottom(worldBounds.getBottom() + PADDING);
        for (ButtonElement buttonElement : buttonElements) {
            buttonElement.setHeightProportion(HEIGHT);
            buttonElement.pos.set(new Vector2(0, 0f));
            buttonElement.setRight(worldBounds.getRight() - PADDING);
            buttonElement.setBottom(worldBounds.getBottom() + PADDING);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        for (ButtonElement buttonElement : buttonElements) {
            buttonElement.update(delta);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(0.7f,0,0,1);
        super.draw(batch);
        batch.setColor(1,1,1,1f);
        for (ButtonElement buttonElement : buttonElements) {
            buttonElement.draw(batch);
        }
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
    public void action() {
        Gdx.app.exit();
    }
}
