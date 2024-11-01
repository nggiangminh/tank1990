package jsd.project.tank90.utils;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.environments.BrickWall;
import jsd.project.tank90.model.environments.SteelWall;
import jsd.project.tank90.model.environments.Water;
import jsd.project.tank90.model.tanks.Bullet;
import jsd.project.tank90.model.tanks.PlayerTank;

import java.awt.*;
import java.util.List;

public class CollisionHandling {

    // Checks collisions between bullets and SteelWalls
    public static void checkBulletCollisionWithSteel(PlayerTank playerTank, List<GameObject> environmentObjects) {
        List<Bullet> bullets = playerTank.getBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();

            for (GameObject environmentObj : environmentObjects) {
                if (environmentObj instanceof SteelWall) {
                    if (bulletBounds.intersects(environmentObj.getBounds())) {
                        bullets.remove(i);
                        i--; // Adjust index after removal
                        break;
                    }
                }
            }
        }
    }

    // Checks collisions between bullets and BrickWalls
    public static void checkBulletCollisionWithBricks(PlayerTank playerTank, List<GameObject> environmentObjects) {
        List<Bullet> bullets = playerTank.getBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();

            for (int j = 0; j < environmentObjects.size(); j++) {
                GameObject environmentObj = environmentObjects.get(j);

                if (environmentObj instanceof BrickWall) {
                    if (bulletBounds.intersects(environmentObj.getBounds())) {
                        bullets.remove(i);
                        environmentObjects.remove(j);
                        i--; // Adjust bullet index after removal
                        break;
                    }
                }
            }
        }
    }

    // Checks collisions between the tank and solid environment objects
    public static boolean checkCollisionWithEnvironment(PlayerTank tank, List<GameObject> environmentObjects) {
        Rectangle tankBounds = tank.getBounds();

        for (GameObject environmentObj : environmentObjects) {
            if (environmentObj instanceof BrickWall || environmentObj instanceof SteelWall || environmentObj instanceof Water) {
                if (tankBounds.intersects(environmentObj.getBounds())) {
                    return true; // Collision detected
                }
            }
        }
        return false; // No collision
    }
}
