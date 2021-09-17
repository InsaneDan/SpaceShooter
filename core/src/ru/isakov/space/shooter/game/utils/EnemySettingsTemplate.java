package ru.isakov.space.shooter.game.utils;

import com.badlogic.gdx.math.Vector2;

public class EnemySettingsTemplate {

    protected String shipRegionName;
    protected float height;
    protected Vector2 v;
    protected int hp;
    protected String bulletRegionName;
    protected float bulletHight;
    protected Vector2 bulletV;
    protected int bulletDamage;
    protected float shotsPerSecond;
    protected float shotVolume;

    protected String explosionRegionName;

    public EnemySettingsTemplate(String shipRegionName, float height, Vector2 v, int hp, String bulletRegionName, float bulletHight, Vector2 bulletV, int bulletDamage, float shotsPerSecond, float shotVolume, String explosionRegionName) {
        this.shipRegionName = shipRegionName;
        this.height = height;
        this.v = v;
        this.hp = hp;
        this.bulletRegionName = bulletRegionName;
        this.bulletHight = bulletHight;
        this.bulletV = bulletV;
        this.bulletDamage = bulletDamage;
        this.shotsPerSecond = shotsPerSecond;
        this.shotVolume = shotVolume;
        this.explosionRegionName = explosionRegionName;
    }

    public String getShipRegionName() {
        return shipRegionName;
    }

    public float getHeight() {
        return height;
    }

    public Vector2 getV() {
        return v;
    }

    public int getHp() {
        return hp;
    }

    public String getBulletRegionName() {
        return bulletRegionName;
    }

    public float getBulletHight() {
        return bulletHight;
    }

    public Vector2 getBulletV() {
        return bulletV;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public float getShotsPerSecond() {
        return shotsPerSecond;
    }

    public float getShotVolume() {
        return shotVolume;
    }

    public String getExplosionRegionName() {
        return explosionRegionName;
    }
}
