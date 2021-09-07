package ru.isakov.space.shooter.game.sprite.button;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseButton;
import ru.isakov.space.shooter.game.math.Rect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ButtonTemplate extends BaseButton {

    private static final float HEIGHT = 0.2f;

    private final List<ButtonElement> buttonElements = new ArrayList<>();
    private Color textColor;
    private Color backColor;

    public ButtonTemplate(TextureAtlas atlas, Color color, String textTitle, int rotateClockwise, float scale) {

        super(atlas.findRegion("circle"));
        this.scaleX = scale;
        this.scaleY = scale;
        this.initialScale = scale;
        this.textColor = new Color(color).add(0.7f, 0.7f, 0.7f, 1f);
        this.backColor = new Color(color).sub(0.4f,0.4f,0.4f,0f);
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
                new ButtonElement(atlas, "CirclesOuter2", pos, scale, 0, 0, 0f, 1f, 0, color)
        ));

        if (!textTitle.equals("")) {
            buttonElements.add(new ButtonElement(atlas, textTitle, pos, 1.3f, 0, 0, 0f, 0, 0, textColor));
        }
    }

    public void resize(Rect worldBounds, float x, float y) {
        setHeightProportion(HEIGHT);
        this.pos.set(x, y);
        for (ButtonElement buttonElement : buttonElements) {
            buttonElement.setHeightProportion(HEIGHT);
            buttonElement.pos.set(x, y);
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
        batch.setColor(backColor);
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
}
