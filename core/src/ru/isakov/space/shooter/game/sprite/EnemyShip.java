package ru.isakov.space.shooter.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.isakov.space.shooter.game.base.BaseShip;
import ru.isakov.space.shooter.game.math.Rect;
import ru.isakov.space.shooter.game.pool.BulletPool;
import ru.isakov.space.shooter.game.utils.EnemySettingsTemplate;
import ru.isakov.space.shooter.game.utils.Regions;

public class EnemyShip extends BaseShip {

    public static final Vector2 startV = new Vector2(0, -0.25f);

    public EnemyShip(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletV = new Vector2();
        this.bulletPos = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getTop() < worldBounds.getTop() && getBottom() > worldBounds.getBottom()) {
            v.set(v0);
        } else {
            shotTime = 60 / shotsPerSecond * delta * 0.7f;
        }
        this.bulletPos.set(pos.x, pos.y - getHalfHeight());
    }

    public void set(TextureAtlas atlas, EnemySettingsTemplate enemySettingsTemplate, Sound bulletSound) {
        this.regions = Regions.split(atlas.findRegion(enemySettingsTemplate.getShipRegionName()), 1, 2, 2);
        setHeightProportion(enemySettingsTemplate.getHeight());
        this.v0.set(enemySettingsTemplate.getV());
        this.v.set(startV);
        this.hp = enemySettingsTemplate.getHp();
        this.bulletRegion = atlas.findRegion(enemySettingsTemplate.getBulletRegionName());
        this.bulletHeight = enemySettingsTemplate.getBulletHight();
        this.bulletV.set(enemySettingsTemplate.getBulletV());
        this.bulletDamage = enemySettingsTemplate.getBulletDamage();
        this.shotsPerSecond = enemySettingsTemplate.getShotsPerSecond();
        this.shotVolume = enemySettingsTemplate.getShotVolume();
        this.bulletSound = bulletSound;
    }

}
