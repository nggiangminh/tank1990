package jsd.project.tank90.utils;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.environments.Base;
import jsd.project.tank90.model.environments.BrickWall;
import jsd.project.tank90.model.environments.SteelWall;
import jsd.project.tank90.model.environments.Water;
import jsd.project.tank90.model.powerups.PowerUp;
import jsd.project.tank90.model.tanks.*;
import jsd.project.tank90.ui.GamePanel;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class CollisionHandling {

    public static boolean checkBulletBaseCollision(Tank tank, List<GameObject> environmentObjects, List<Explosion> explosions) {
        List<Bullet> bullets = tank.getBullets();
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Rectangle bulletBounds = bullet.getBounds();

            Iterator<GameObject> envIterator = environmentObjects.iterator();
            while (envIterator.hasNext()) {
                GameObject environmentObj = envIterator.next();

                if (environmentObj instanceof Base && bulletBounds.intersects(environmentObj.getBounds())) {
                    // Collision with BrickWall: Remove both the bullet and the brick
                    bullet.setDamage(bullet.getDamage() - 1);
                    if (bullet.getDamage() == 0) {
                        explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2)); // Add explosion
                        bulletIterator.remove();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // Checks collisions between bullets and environment objects (BrickWall and SteelWall)
    public static void checkBulletEnvironmentCollision(Tank tank, List<GameObject> environmentObjects, List<Explosion> explosions) {
        List<Bullet> bullets = tank.getBullets();
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Rectangle bulletBounds = bullet.getBounds();

            Iterator<GameObject> envIterator = environmentObjects.iterator();
            while (envIterator.hasNext()) {
                GameObject environmentObj = envIterator.next();

                if (environmentObj instanceof BrickWall && bulletBounds.intersects(environmentObj.getBounds())) {
                    // Collision with BrickWall: Remove both the bullet and the brick
                    bullet.setDamage(bullet.getDamage() - 1);
                    if (bullet.getDamage() == 0) {
                        explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2)); // Add explosion
                        bulletIterator.remove();
                    }
                    envIterator.remove();
                    break; // Stop checking this bullet as it has been removed
                } else if (environmentObj instanceof SteelWall steelWall && bulletBounds.intersects(environmentObj.getBounds())) {
                    // Collision with SteelWall: Only remove the bullet
                    if (bullet.getDamage() == 2 && steelWall.isDestructable()) envIterator.remove();
                    explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2)); // Add explosion
                    bulletIterator.remove();
                    break; // Stop checking this bullet as it has been removed
                }
            }
        }
    }

    // Checks collisions between the tank and solid environment objects


    public static void checkBulletEnemyTankCollision(List<Bullet> bullets, EnemyTank tank, List<Explosion> explosions) {
        Rectangle tankBounds = tank.getBounds();
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Rectangle bulletBounds = bullet.getBounds();
            if (bulletBounds.intersects(tankBounds)) {
                explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2)); // Add explosion
                bulletIterator.remove();
                tank.takeDamage();
                break; // Stop checking this bullet as it has been removed
            }
        }
    }

    public static void checkBulletPlayerTankCollision(List<Bullet> bullets, PlayerTank tank, List<Explosion> explosions) {
        Rectangle tankBounds = tank.getBounds();
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Rectangle bulletBounds = bullet.getBounds();
            if (bulletBounds.intersects(tankBounds)) {
                tank.takeDamage();
                explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2)); // Add explosion
                bulletIterator.remove();
                break; // Stop checking this bullet as it has been removed
            }
        }
    }

    public static void checkBulletEnemyBulletCollision(List<Bullet> bullets, List<Bullet> enemyBullets, List<Explosion> explosions) {
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Rectangle bulletBounds = bullet.getBounds();
            Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
            while (enemyBulletIterator.hasNext()) {
                Bullet enemyBullet = enemyBulletIterator.next();
                Rectangle enemyBulletBounds = enemyBullet.getBounds();
                if (bulletBounds.intersects(enemyBulletBounds)) {
                    explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2)); // Add explosion
                    explosions.add(new Explosion(enemyBullet.getCenterX(), enemyBullet.getCenterY(), bullet.getSize() * 2)); // Add explosion
                    bulletIterator.remove();
                    enemyBulletIterator.remove();
                }
            }
        }
    }

    public static boolean checkTankEnvironmentCollision(Tank tank, List<GameObject> environmentObjects) {
        Rectangle tankBounds = tank.getBounds();

        for (GameObject environmentObj : environmentObjects) {
            if (environmentObj instanceof BrickWall || environmentObj instanceof SteelWall || environmentObj instanceof Water) {
                if (tankBounds.intersects(environmentObj.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void checkPlayerEnemyCollision(PlayerTank playerTank, List<EnemyTank> enemyTanks, List<Explosion> explosions) {
        Rectangle playerTankBounds = playerTank.getBounds();
        for (EnemyTank enemy : enemyTanks) {
                if (playerTankBounds.intersects(enemy.getBounds())) {
                    enemy.takeDamage();
                    playerTank.takeDamage();
                    explosions.add(new Explosion(enemy.getCenterX(),enemy.getCenterY(),enemy.getSize()));
                    explosions.add(new Explosion(playerTank.getCenterX(),playerTank.getCenterY(),playerTank.getSize()));
            }
        }
    }

    public static void checkClaimPowerup(PlayerTank tank, List<PowerUp> powerUps, GamePanel gamePanel) {
        Rectangle tankBounds = tank.getBounds();
        Iterator<PowerUp> powerUpIterator = powerUps.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();
            if (tankBounds.intersects(powerUp.getBounds())) {
                powerUp.activate(tank, gamePanel);
                powerUpIterator.remove();
            }
        }
    }
}
