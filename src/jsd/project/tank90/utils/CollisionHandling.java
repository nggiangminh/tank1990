package jsd.project.tank90.utils;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.environments.*;
import jsd.project.tank90.model.powerups.PowerUp;
import jsd.project.tank90.model.tanks.Bullet;
import jsd.project.tank90.model.tanks.EnemyTank;
import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.model.tanks.Tank;
import jsd.project.tank90.ui.GamePanel;

import java.awt.*;
import java.util.List;

public class CollisionHandling {

    public static boolean checkBulletBaseCollision(Tank tank, List<GameObject> environmentObjects, List<Explosion> explosions) {
        List<Bullet> bullets = tank.getBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();

            for (int j = 0; j < environmentObjects.size(); j++) {
                GameObject environmentObj = environmentObjects.get(j);

                if (environmentObj instanceof Base && bulletBounds.intersects(environmentObj.getBounds())) {
                    ((Base) environmentObj).destroy();
                    explosions.add(new Explosion(environmentObj.getCenterX(), environmentObj.getCenterY(), environmentObj.getSize()));
                    explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2));
                    bullets.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public static void checkBulletEnvironmentCollision(Tank tank, List<GameObject> environmentObjects, List<Explosion> explosions) {
        List<Bullet> bullets = tank.getBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();

            for (int j = 0; j < environmentObjects.size(); j++) {
                GameObject environmentObj = environmentObjects.get(j);

                if (environmentObj instanceof BrickWall && bulletBounds.intersects(environmentObj.getBounds())) {
                    bullet.setDamage(bullet.getDamage() - 1);
                    if (bullet.getDamage() == 0) {
                        explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2));
                        bullets.remove(i);
                        i--; // Adjust index to continue with the updated list
                    }
                    environmentObjects.remove(j);
                    break;
                } else if (environmentObj instanceof SteelWall steelWall && bulletBounds.intersects(environmentObj.getBounds())) {
                    if (tank instanceof PlayerTank && bullet.getDamage() == 2 && steelWall.isDestructable()) {
                        environmentObjects.remove(j);
                    }
                    explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2));
                    bullets.remove(i);
                    i--; // Adjust index to continue with the updated list
                    break;
                }
            }
        }
    }

    public static void checkBulletEnemyTankCollision(List<Bullet> bullets, EnemyTank enemy, List<Explosion> explosions) {
        Rectangle tankBounds = enemy.getBounds();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();
            if (bulletBounds.intersects(tankBounds)) {
                explosions.add(new Explosion(enemy.getCenterX(), enemy.getCenterY(), enemy.getSize()));
                explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2));
                bullets.remove(i);
                enemy.takeDamage();
                break;
            }
        }
    }

    public static void checkBulletPlayerTankCollision(List<Bullet> bullets, PlayerTank playerTank, List<Explosion> explosions) {
        Rectangle tankBounds = playerTank.getBounds();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();
            if (bulletBounds.intersects(tankBounds) && !playerTank.isDisabled()) {
                playerTank.takeDamage();
                explosions.add(new Explosion(playerTank.getCenterX(), playerTank.getCenterY(), playerTank.getSize()));
                explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2));
                bullets.remove(i);
                break;
            }
        }
    }

    public static void checkBulletEnemyBulletCollision(List<Bullet> bullets, List<Bullet> enemyBullets, List<Explosion> explosions) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();

            for (int j = 0; j < enemyBullets.size(); j++) {
                Bullet enemyBullet = enemyBullets.get(j);
                Rectangle enemyBulletBounds = enemyBullet.getBounds();

                if (bulletBounds.intersects(enemyBulletBounds)) {
                    explosions.add(new Explosion(bullet.getCenterX(), bullet.getCenterY(), bullet.getSize() * 2));
                    explosions.add(new Explosion(enemyBullet.getCenterX(), enemyBullet.getCenterY(), bullet.getSize() * 2));
                    bullets.remove(i);
                    enemyBullets.remove(j);
                    i--; // Adjust index to continue with the updated list
                    break;
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

    public static boolean checkPlayerOnIce(PlayerTank playerTank, List<GameObject> environmentObjects) {
        Rectangle playerTankBounds = playerTank.getBounds();

        for (GameObject environmentObj : environmentObjects) {
            if (environmentObj instanceof Ice) {
                if (playerTankBounds.intersects(environmentObj.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkBulletOnIce(Bullet bullet, List<GameObject> environmentObjects) {
        Rectangle bulletBounds = bullet.getBounds();

        for (GameObject environmentObj : environmentObjects) {
            if (environmentObj instanceof Ice) {
                if (bulletBounds.intersects(environmentObj.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void checkPlayerEnemyCollision(PlayerTank playerTank, List<EnemyTank> enemyTanks, List<Explosion> explosions) {
        Rectangle playerTankBounds = playerTank.getBounds();

        for (EnemyTank enemy : enemyTanks) {
            if (playerTankBounds.intersects(enemy.getBounds()) && !playerTank.isDisabled() && !enemy.isDisabled()) {
                enemy.markAsDead();
                playerTank.takeDamage();
                explosions.add(new Explosion(enemy.getCenterX(), enemy.getCenterY(), enemy.getSize()));
                explosions.add(new Explosion(playerTank.getCenterX(), playerTank.getCenterY(), playerTank.getSize()));
            }
        }
    }

    public static void checkClaimPowerup(PlayerTank tank, List<PowerUp> powerUps, GamePanel gamePanel) {
        Rectangle tankBounds = tank.getBounds();

        for (PowerUp powerUp : powerUps) {
            if (tankBounds.intersects(powerUp.getBounds())) {
                powerUp.activate(tank, gamePanel);
            }
        }
        powerUps.removeIf(powerUp -> !powerUp.isActive());
    }
}
