package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tank extends GameObject {
    protected Direction direction; // Current direction of the tank
    protected int speed;
    protected List<Bullet> bullets = new ArrayList<>(); // List of bullets fired by the tank

    public Tank(int x, int y, int size, int speed, Direction direction) {
        super(x, y, size);
        this.direction = direction;
    }

    // Shoot a bullet based on the tank's direction
    public Bullet shoot() {
        int bulletX, bulletY;
        int bulletSize = getBulletSize();
        int bulletSpeed = getBulletSpeed();

        // Adjust bullet position based on the tank's direction
        switch (direction) {
            case UP -> {
                bulletX = x + (size - bulletSize) / 2;
                bulletY = y - bulletSize;
            }
            case DOWN -> {
                bulletX = x + (size - bulletSize) / 2;
                bulletY = y + size;
            }
            case LEFT -> {
                bulletX = x - bulletSize;
                bulletY = y + (size - bulletSize) / 2;
            }
            default -> { // RIGHT
                bulletX = x + size;
                bulletY = y + (size - bulletSize) / 2;
            }
        }

        // Create and add the bullet
        Bullet bullet = new Bullet(bulletX, bulletY, bulletSize, bulletSpeed, direction);
        bullets.add(bullet);
        return bullet;
    }

    // Abstract methods for bullet size and speed
    protected abstract int getBulletSize();
    protected abstract int getBulletSpeed();

    // Update bullets and remove those out of bounds
    public void updateBullets() {
        bullets.removeIf(bullet -> bullet.isOutOfBounds(800, 600)); // Screen size example; adjust as needed
        bullets.forEach(Bullet::update);
    }

    // Render bullets on the screen
    public void renderBullets(Graphics g) {
        bullets.forEach(bullet -> bullet.render(g));
    }

    // Getters and setters for tank direction
    public Direction getDirection() {
        return direction;
    }

    public abstract void move();

    public abstract void undoMove();

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Abstract methods to be implemented in subclasses
    @Override
    public abstract void update();
    @Override
    public abstract void render(Graphics g);

    public List<Bullet> getBullets() {
        return  bullets;
    }
}
