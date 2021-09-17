package ru.isakov.space.shooter.game.utils;

import com.badlogic.gdx.math.Vector2;

public class EnemyShipTemplates {

    private EnemySettingsTemplate[] enemySettingsTemplate = new EnemySettingsTemplate[9];

    // TODO переделать в enum? подключить БД? (антипаттерн Magic Number)
    public EnemyShipTemplates() {
        enemySettingsTemplate[0] = new EnemySettingsTemplate("enemy1", 0.05f, new Vector2(0, -0.3f), 1, "bulletEnemy", 0.01f, new Vector2(0, -0.6f), 1, 1f, 0.001f, "explosion");
        enemySettingsTemplate[1] = new EnemySettingsTemplate("enemy2", 0.15f, new Vector2(0, -0.2f), 1, "bulletEnemy", 0.03f, new Vector2(0, -0.3f), 3, 0.5f, 0.005f, "explosion");
        enemySettingsTemplate[2] = new EnemySettingsTemplate("enemy3", 0.15f, new Vector2(0, -0.175f), 1, "bulletEnemy", 0.05f, new Vector2(0, -0.3f), 5, 0.2f, 0.01f, "explosion");
        enemySettingsTemplate[3] = new EnemySettingsTemplate("enemy4", 0.1f, new Vector2(0, -0.15f), 1, "bulletEnemy", 0.05f, new Vector2(0, -0.3f), 5, 0.2f, 0.01f, "explosion");
        enemySettingsTemplate[4] = new EnemySettingsTemplate("enemy5", 0.12f, new Vector2(0, -0.125f), 1, "bulletEnemy", 0.05f, new Vector2(0, -0.3f), 5, 0.2f, 0.01f, "explosion");
        enemySettingsTemplate[5] = new EnemySettingsTemplate("enemy6", 0.15f, new Vector2(0, -0.1f), 1, "bulletEnemy", 0.05f, new Vector2(0, -0.3f), 5, 0.2f, 0.01f, "explosion");
        enemySettingsTemplate[6] = new EnemySettingsTemplate("enemy7", 0.2f, new Vector2(0, -0.075f), 1, "bulletEnemy", 0.05f, new Vector2(0, -0.3f), 5, 0.2f, 0.01f, "explosion");
        enemySettingsTemplate[7] = new EnemySettingsTemplate("enemy8", 0.25f, new Vector2(0, -0.05f), 1, "bulletEnemy", 0.05f, new Vector2(0, -0.3f), 5, 0.2f, 0.01f, "explosion");
        enemySettingsTemplate[8] = new EnemySettingsTemplate("enemy9", 0.3f, new Vector2(0, -0.005f), 1, "bulletEnemy", 0.05f, new Vector2(0, -0.3f), 5, 0.2f, 0.01f, "explosion");
    }

    public EnemySettingsTemplate get(int template) {
        return enemySettingsTemplate[template];
    }
}
