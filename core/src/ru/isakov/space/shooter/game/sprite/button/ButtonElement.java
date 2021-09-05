package ru.isakov.space.shooter.game.sprite.button;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseSprite;
import ru.isakov.space.shooter.game.math.Rect;

public class ButtonElement extends BaseSprite {

    private static final float HEIGHT = 0.2f;
    private float scale;
    private float rotationVelocity;
    private float startAngle;
    private Rect worldBounds;
    private float globalTime;
    private float flipXV, flipYV, flipShift;
    private Color color = new Color(1,1,1,1);

    public ButtonElement(TextureAtlas atlas) {
        super(atlas.findRegion("picture"));
        setMainParameters(pos, 1f, 1f, 0);
        setFlipParameters(0f, 0f, 0);
    }

    public ButtonElement(TextureAtlas atlas, String detailName, Vector2 position, float scale, float rotationVelocity) {
        super(atlas.findRegion(detailName));
        setMainParameters(position, scale, rotationVelocity, 0);
    }

    public ButtonElement(TextureAtlas atlas, String detailName, Vector2 position, float scale, float rotationVelocity, float startAngle,
                         float flipXV, float flipYV, float flipShift, Color color) {
        super(atlas.findRegion(detailName));
        setMainParameters(position, scale, rotationVelocity, startAngle);
        setFlipParameters(flipXV, flipYV, flipShift);
        this.color = color;
    }

    private void setMainParameters(Vector2 position, float scale, float rotationVelocity, float startAngle) {
        pos.set(position);
        this.scale = scale;
        this.rotationVelocity = rotationVelocity;
        this.startAngle = startAngle;
    }

    private void setFlipParameters(float flipXV, float flipYV, float flipShift) {
        this.flipXV = flipXV;
        this.flipYV = flipYV;
        this.flipShift = flipShift;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
    }

    @Override
    public void update(float delta) {
        globalTime += delta;
        angle = globalTime * rotationVelocity * 360 + startAngle;
        if (flipXV != 0) {
            super.setScaleX((float) Math.sin(globalTime * flipXV - flipShift) * scale);
        } else {
            super.setScaleX(scale);
        }
        if (flipYV != 0) {
            super.setScaleY((float) Math.sin(globalTime * flipYV - flipShift) * scale);
        } else {
            super.setScaleY(scale);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(color);
        super.draw(batch);
        batch.setColor(1,1,1,1);
    }
}
