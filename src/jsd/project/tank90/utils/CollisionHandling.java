package jsd.project.tank90.utils;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.environments.BrickWall;
import jsd.project.tank90.model.environments.SteelWall;
import jsd.project.tank90.model.environments.Water;
import jsd.project.tank90.model.powerups.PowerUp;
import jsd.project.tank90.model.tanks.Bullet;
import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.model.tanks.Tank;
import jsd.project.tank90.ui.GamePanel;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class CollisionHandling {

    // Checks collisions between bullets and environment objects (BrickWall and SteelWall)
    public static void checkBulletEnvironmentCollision(Tank tank, List<GameObject> environmentObjects) {
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
                    bulletIterator.remove();
                    envIterator.remove();
                    break; // Stop checking this bullet as it has been removed
                } else if (environmentObj instanceof SteelWall && bulletBounds.intersects(environmentObj.getBounds())) {
                    // Collision with SteelWall: Only remove the bullet
                    bulletIterator.remove();
                    break; // Stop checking this bullet as it has been removed
                }
            }
        }
    }

    // Checks collisions between the tank and solid environment objects
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


    public static void checkBulletTankCollision(List<Bullet> bullets, Tank tank) {
        Rectangle tankBounds = tank.getBounds();
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Rectangle bulletBounds = bullet.getBounds();

            if (bulletBounds.intersects(tankBounds)) {
                bulletIterator.remove(); // Remove the bullet upon collision

                // Check if the tank is shielded
                if (tank instanceof PlayerTank && ((PlayerTank) tank).isShielded()) {
                    // The tank is shielded, so no damage is applied
                    break; // Exit, as we’ve removed the bullet
                }

                // Apply damage if the tank is not shielded
                tank.takeDamage(bullet.getDamage());
                break; // Stop checking this bullet as it has been removed
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
