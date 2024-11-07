package jsd.project.tank90.utils;

import jsd.project.tank90.model.tanks.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner {
    private int enemyLeft = 20;
    private final List<EnemyTank> enemyTanks; // List to hold active enemies
    private final List<SpawnEffect> spawnQueue = new ArrayList<>(); // Temporary list for spawn effects
    private final int DELAY_FRAMES = 30;
    private final Point spawnPoint1 = new Point(20, 20);
    private final Point spawnPoint2 = new Point(265, 20);
    private final Point spawnPoint3 = new Point(510, 20);
    private final Point[] spawnPoints = new Point[]{spawnPoint1, spawnPoint2, spawnPoint3};
    private final int maxEnemies;
    private final Random random = new Random();
    // Spawn effect images
    private final Image[] spawnImages = {new ImageIcon("src/jsd/project/tank90/resources/images/appear_1.png").getImage(), new ImageIcon("src/jsd/project/tank90/resources/images/appear_2.png").getImage(), new ImageIcon("src/jsd/project/tank90/resources/images/appear_3.png").getImage(), new ImageIcon("src/jsd/project/tank90/resources/images/appear_4.png").getImage()};
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
        spawnQueue.removeIf(spawnEffect -> {
            spawnEffect.render(g); // Render the spawning effect
            if (spawnEffect.isFinished()) {
                enemyTanks.add(spawnEffect.tank); // Move tank to active list after effect
                return true; // Remove from spawn queue
            }
            return false;
        });
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
