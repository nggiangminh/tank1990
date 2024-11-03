package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tank extends GameObject {
    protected Direction direction; // Current direction of the tank
    protected List<Bullet> bullets = new ArrayList<>(); // List of bullets fired by the tank

    protected Image tankImage;



    public Tank(int x, int y, int size, Direction direction) {
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
        Bullet bullet = new Bullet(bulletX, bulletY, bulletSize, bulletSpeed, direction, getBulletDamage());
        bullets.add(bullet);
        return bullet;
    }

    // Abstract methods for bullet size and speed
    protected abstract int getBulletSize();

    protected abstract int getBulletSpeed();

    // Update bullets and remove those out of bounds
    public void updateBullets() {
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

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
        switch (newDirection) {
            case UP -> tankImage = getTankUpImage();
            case DOWN -> tankImage = getTankDownImage();
            case LEFT -> tankImage = getTankLeftImage();
            case RIGHT -> tankImage = getTankRightImage();
        }
    }

    public void move() {
        switch (direction) {
            case UP -> y -= getSpeed();
            case DOWN -> y += getSpeed();
            case LEFT -> x -= getSpeed();
            case RIGHT -> x += getSpeed();
        }
        setDirection(direction);
    }

    public void undoMove() {
        switch (this.direction) {
            case UP -> y += getSpeed();
            case DOWN -> y -= getSpeed();
            case LEFT -> x += getSpeed();
            case RIGHT -> x -= getSpeed();
        }
    }

    public abstract int getSpeed();

    public abstract Image getTankUpImage();

    public abstract Image getTankDownImage();

    public abstract Image getTankLeftImage();

    public abstract Image getTankRightImage();

    public abstract int getBulletDamage();

    // Abstract methods to be implemented in subclasses
    public void update() {
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tankImage, x, y, size, size, null);
        renderBullets(g); // Render bullets fired by the player tank
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public Image getTankImage() {
        return tankImage;
    }

    public void setTankImage(Image tankImage) {
        this.tankImage = tankImage;
    }

    public abstract int getLife();

    public abstract void setLife(int life);
    public void takeDamage(int damage) {
        int newLife = Math.max(0, getLife() - damage);
        setLife(newLife);
    }
    public boolean isDead() {
        return getLife() == 0;
    }
}
