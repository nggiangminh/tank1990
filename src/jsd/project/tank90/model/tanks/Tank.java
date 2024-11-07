package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.utils.Images;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Tank extends GameObject {
    protected final Image DEAD_IMAGE = Images.TANK_DEAD;
    protected Direction direction; // Current direction of the tank
    protected List<Bullet> bullets = new ArrayList<>(); // List of bullets fired by the tank
    protected Image tankImage;
    protected boolean toggleImage = false;
    private boolean disabled = false;

    public Tank(int x, int y, int size, Direction direction) {
        super(x, y, size);
        this.direction = direction;
    }

    public int getCenterX() {
        return x + size / 2;
    }

    public int getCenterY() {
        return y + size / 2;
    }


    // Shoot a bullet based on the tank's direction
    public Bullet shoot() {
        if (isDisabled()) return null;
        int bulletX, bulletY;
        int bulletSize = getBulletSize();
        int bulletSpeed = getBulletSpeed() * 2;

        // Adjust bullet position based on the tank's direction
        switch (direction) {
            case UP -> {
                bulletX = x + size / 2;
                bulletY = y;
            }
            case DOWN -> {
                bulletX = x + size / 2;
                bulletY = y + size;
            }
            case LEFT -> {
                bulletX = x;
                bulletY = y + size / 2;
            }
            default -> { // RIGHT
                bulletX = x + size;
                bulletY = y + size / 2;
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

    public void toggleImage() {
        toggleImage = !toggleImage;
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
        if(toggleImage){
            switch (newDirection) {
                case UP -> tankImage = this.getTankUpImage1();
                case DOWN -> tankImage = this.getTankDownImage1();
                case LEFT -> tankImage = this.getTankLeftImage1();
                case RIGHT -> tankImage = this.getTankRightImage1();
            }
        }
        else {
            switch (newDirection) {
                case UP -> tankImage = this.getTankUpImage2();
                case DOWN -> tankImage = this.getTankDownImage2();
                case LEFT -> tankImage = this.getTankLeftImage2();
                case RIGHT -> tankImage = this.getTankRightImage2();
            }
        }
    }

    public void move() {
        if (!disabled) {
            switch (direction) {
                case UP -> y -= getSpeed();
                case DOWN -> y += getSpeed();
                case LEFT -> x -= getSpeed();
                case RIGHT -> x += getSpeed();
            }
            setDirection(direction);
            toggleImage();
        }
    }

    public void undoMove() {
        switch (this.direction) {
            case UP -> y += getSpeed();
            case DOWN -> y -= getSpeed();
            case LEFT -> x += getSpeed();
            case RIGHT -> x -= getSpeed();
        }
    }

    public void disable() {
        disabled = true;
    }

    public void enable() {
        disabled = false;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public abstract int getSpeed();

    protected abstract Image getTankUpImage1();

    protected abstract Image getTankDownImage1();

    protected abstract Image getTankLeftImage1();

    protected abstract Image getTankRightImage1();

    protected abstract Image getTankUpImage2();

    protected abstract Image getTankDownImage2();

    protected abstract Image getTankLeftImage2();

    protected abstract Image getTankRightImage2();

    @Override
    public void render(Graphics g) {
        if (isDisabled()) g.drawImage(DEAD_IMAGE, x, y, size, size, null);
        else {
            g.drawImage(tankImage, x, y, size, size, null);
            renderBullets(g); // Render bullets fired by the player tank
        }
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

    public abstract int getBulletDamage();
}
