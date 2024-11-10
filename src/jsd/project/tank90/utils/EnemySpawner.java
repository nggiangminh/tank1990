package jsd.project.tank90.utils;

import jsd.project.tank90.model.tanks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class EnemySpawner {
    private final List<EnemyTank> enemyTanks; // List to hold active enemies
    private final List<SpawnEffect> spawnQueue = new CopyOnWriteArrayList<>(); // Temporary list for spawn effects
    private final int DELAY_FRAMES = 30;
    //Spawn points for enemy tanks
    private final Point spawnPoint1 = new Point(20, 20);
    private final Point spawnPoint2 = new Point(265, 20);
    private final Point spawnPoint3 = new Point(510, 20);
    private final Point[] spawnPoints = new Point[]{spawnPoint1, spawnPoint2, spawnPoint3};
    private final int maxEnemies;
    private final Random random = new Random();
    // Spawn effect images
    private final Image[] spawnImages = {Images.APPEAR_1, Images.APPEAR_2, Images.APPEAR_3, Images.APPEAR_4};
    private int enemyLeft = 20; // number of enemies to spawn
    private int spawnCountDown;

    public EnemySpawner(List<EnemyTank> enemyTanks, int maxEnemies) {
        this.enemyTanks = enemyTanks;
        this.maxEnemies = maxEnemies;
    }

    // Spawn an enemy tank at a random spawn point
    public void spawnEnemy() {
        if (enemyLeft > 0 && enemyTanks.size() + spawnQueue.size() < maxEnemies && spawnCountDown <= 0) {
            Point spawnPoint = spawnPoints[random.nextInt(spawnPoints.length)];
            Direction direction = Direction.values()[random.nextInt(Direction.values().length)];

            EnemyTank newEnemy;
            switch (random.nextInt(4)) {
                case 0 -> newEnemy = new BasicTank(spawnPoint.x, spawnPoint.y, 30, direction);
                case 1 -> newEnemy = new ArmorTank(spawnPoint.x, spawnPoint.y, 30, direction);
                case 2 -> newEnemy = new FastTank(spawnPoint.x, spawnPoint.y, 30, direction);
                default -> newEnemy = new PowerTank(spawnPoint.x, spawnPoint.y, 30, direction);
            }

            spawnQueue.add(new SpawnEffect(newEnemy, spawnPoint));
            enemyLeft--;
            spawnCountDown = DELAY_FRAMES; // Reset countdown
        } else if (spawnCountDown > 0) {
            spawnCountDown--;
        }
    }

    // Render spawn effects for enemies in spawnQueue
    public void renderSpawnEffects(Graphics g) {
        List<SpawnEffect> toRemove = new ArrayList<>();
        for (SpawnEffect spawnEffect : spawnQueue) {
            spawnEffect.render(g);
            if (spawnEffect.isFinished()) {
                enemyTanks.add(spawnEffect.tank);
                toRemove.add(spawnEffect);
            }
        }
        spawnQueue.removeAll(toRemove); // Remove after iteration
    }


    public int getEnemyLeft() {
        return enemyLeft + enemyTanks.size() + spawnQueue.size();
    }


    // Inner class to handle spawning effect and cooldown
    private class SpawnEffect {
        EnemyTank tank;
        Point position;
        int spawnFrames = DELAY_FRAMES;
        int imageIndex = 0; // Track the current image

        SpawnEffect(EnemyTank tank, Point position) {
            this.tank = tank;
            this.position = position;
        }

        void render(Graphics g) {
            // Draw the current image in the cycle
            g.drawImage(spawnImages[imageIndex], position.x, position.y, tank.getSize(), tank.getSize(), null);

            // Cycle to the next image every few frames
            if (spawnFrames % (DELAY_FRAMES / spawnImages.length) == 0) {
                imageIndex = (imageIndex + 1) % spawnImages.length;
            }
            spawnFrames--;
        }

        boolean isFinished() {
            return spawnFrames <= 0;
        }
    }

}
