package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Tank extends GameObject {
    protected Direction direction; // Current direction of the tank

    // List to store bullets fired by the tank
    protected List<Bullet> bullets = new ArrayList<>();

    public Tank(int x, int y, int size, int speed, Direction direction) {
        super(x, y, size);
        this.direction = direction;
    }

    // Move the tank in the current direction based on its speed
    public void move() {
    }

    // Implement the shoot() method
    public Bullet shoot() {
        int bulletX;
        int bulletY;
        int bulletSize = getBulletSize();
        int bulletSpeed = getBulletSpeed();

        // Adjust bullet's initial position based on tank's direction
        switch (direction) {
            case UP:
                bulletX = x + (size - bulletSize) / 2;
                bulletY = y - bulletSize;
                break;
            case DOWN:
                bulletX = x + (size - bulletSize) / 2;
                bulletY = y + size;
                break;
            case LEFT:
                bulletX = x - bulletSize;
                bulletY = y + (size -bulletSize)/2;
                break;
            default:
                bulletX = x + size;
                bulletY = y + (size -bulletSize)/2;
                break;
        }

        Bullet bullet = new Bullet(bulletX, bulletY, bulletSize, bulletSpeed, direction);
        bullets.add(bullet); // Add the bullet to the list of bullets
        bullet.update();
        return bullet;
    }

    // Abstract methods to get bullet size and speed, implemented in subclasses
    protected abstract int getBulletSize();

    protected abstract int getBulletSpeed();

    public void updateBullets() {
        for (Bullet bullet : bullets) {
            bullet.update();
        }
        bullets.removeIf(bullet -> bullet.isOutOfBounds(800, 600));
    }

    public void renderBullets(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.render(g);
        }
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render(Graphics g);

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
