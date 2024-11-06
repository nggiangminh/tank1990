package jsd.project.tank90.utils;

import jsd.project.tank90.model.tanks.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner {
    private final List<EnemyTank> enemyTanks;

    private final int DELAY_FRAMES = 60;

    private int spawnCountDown;
    private final Point spawnPoint1 = new Point(20, 20);
    private final Point spawnPoint2 = new Point(265, 20);
    private final Point spawnPoint3 = new Point(510, 20);
    private final Point[] spawnPoints = new Point[]{spawnPoint1, spawnPoint2};
    private final int maxEnemies;
    private final Random random = new Random();

    public EnemySpawner(List<EnemyTank> enemyTanks, int maxEnemies) {
        this.enemyTanks = enemyTanks;
        this.maxEnemies = maxEnemies;
    }

    // Spawn an enemy tank at a random spawn point
    public void spawnEnemy() {
        if (enemyTanks.size() < maxEnemies && spawnCountDown<=0) {
            Point spawnPoint = spawnPoints[random.nextInt(spawnPoints.length)];
            Direction direction = Direction.values()[random.nextInt(Direction.values().length)];

            EnemyTank newEnemy;
            switch (random.nextInt(4)) {
                case 0 -> newEnemy = new BasicTank(spawnPoint.x, spawnPoint.y, 30, direction);
                case 1 -> newEnemy = new ArmorTank(spawnPoint.x, spawnPoint.y, 30, direction);
                case 2 -> newEnemy = new FastTank(spawnPoint.x, spawnPoint.y, 30, direction);
                default -> newEnemy = new PowerTank(spawnPoint.x, spawnPoint.y, 30, direction);
            }

            enemyTanks.add(newEnemy);
            spawnCountDown = DELAY_FRAMES;
        }
        else if (spawnCountDown > 0){
            spawnCountDown --;
        }
    }
}
