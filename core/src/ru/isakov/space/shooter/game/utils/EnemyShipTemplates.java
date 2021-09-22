package ru.isakov.space.shooter.game.utils;

import com.badlogic.gdx.math.Vector2;

public class EnemyShipTemplates {

    private final EnemySettingsTemplate[] enemySettingsTemplate = new EnemySettingsTemplate[8];

    public EnemyShipTemplates() {
        enemySettingsTemplate[0] = new EnemySettingsTemplate("enemy1", 0.05f, new Vector2(0, -0.25f), 1, "bulletEnemy", 0.01f, new Vector2(0, -0.5f), 1, 0.5f, 0.001f, "explosion");
        enemySettingsTemplate[1] = new EnemySettingsTemplate("enemy2", 0.1f, new Vector2(0, -0.185f), 3, "bulletEnemy", 0.012f, new Vector2(0, -0.5f), 3, 0.75f, 0.001f, "explosion");
        enemySettingsTemplate[2] = new EnemySettingsTemplate("enemy3", 0.15f, new Vector2(0, -0.175f), 10, "bulletEnemy", 0.013f, new Vector2(0, -0.4f), 5, 0.5f, 0.001f, "explosion");
        enemySettingsTemplate[3] = new EnemySettingsTemplate("enemy4", 0.15f, new Vector2(0, -0.1f), 15, "bulletEnemy", 0.015f, new Vector2(0, -0.3f), 7, 0.75f, 0.001f, "explosion");
        enemySettingsTemplate[4] = new EnemySettingsTemplate("enemy5", 0.12f, new Vector2(0, -0.1f), 20, "bulletEnemy", 0.017f, new Vector2(0, -0.27f), 10, 0.5f, 0.001f, "explosion");
        enemySettingsTemplate[5] = new EnemySettingsTemplate("enemy6", 0.15f, new Vector2(0, -0.08f), 20, "bulletEnemy", 0.02f, new Vector2(0, -0.25f), 12, 0.4f, 0.005f, "explosion");
        enemySettingsTemplate[6] = new EnemySettingsTemplate("enemy7", 0.2f, new Vector2(0, -0.075f), 25, "bulletEnemy", 0.03f, new Vector2(0, -0.3f), 15, 0.5f, 0.006f, "explosion");
        enemySettingsTemplate[7] = new EnemySettingsTemplate("enemy8", 0.3f, new Vector2(0, -0.05f), 100, "bulletEnemy", 0.075f, new Vector2(0, -0.2f), 50, 0.25f, 0.007f, "explosion");
    }

    public EnemySettingsTemplate get(int template) {
        return enemySettingsTemplate[template];
    }
}
