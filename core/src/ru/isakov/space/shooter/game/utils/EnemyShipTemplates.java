package ru.isakov.space.shooter.game.utils;

import com.badlogic.gdx.math.Vector2;

public class EnemyShipTemplates {

    private EnemySettingsTemplate[] enemySettingsTemplate = new EnemySettingsTemplate[3];

    public EnemyShipTemplates() {
        enemySettingsTemplate[0] = new EnemySettingsTemplate("enemy0", 0.1f, new Vector2(0, -0.2f), 1, "bulletEnemy", 0.01f, new Vector2(0, -0.6f), 1, 1f, 0.001f);
        enemySettingsTemplate[1] = new EnemySettingsTemplate("enemy1", 0.15f, new Vector2(0, -0.03f), 5, "bulletEnemy", 0.03f, new Vector2(0, -0.3f), 3, 0.5f, 0.005f);
        enemySettingsTemplate[2] = new EnemySettingsTemplate("enemy2", 0.2f, new Vector2(0, -0.005f), 10, "bulletEnemy", 0.05f, new Vector2(0, -0.3f), 5, 0.2f, 0.01f);
    }

    public EnemySettingsTemplate get(int template) {
        return enemySettingsTemplate[template];
    }
}
