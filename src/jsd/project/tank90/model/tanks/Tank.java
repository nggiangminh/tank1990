package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.utils.Direction;
import jsd.project.tank90.utils.Images;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public abstract class Tank extends GameObject {
    protected final Image DEAD_IMAGE = Images.TANK_DEAD; //Image for dead tank

    protected Direction direction; // Current direction of the tank
    protected List<Bullet> bullets = new CopyOnWriteArrayList<>(); // List of bullets fired by the tank
    protected Image tankImage;
    protected boolean toggleImage = false;
    private boolean disabled = false;

    public Tank(int x, int y, int size, Direction direction) {
        super(x, y, size);
        this.direction = direction;
    }


    // Shoot a bullet based on the tank's direction
    public Bullet shoot() {
        if (isDisabled()) return null;
        int centerBulletX, centerBulletY;
        int bulletSize = getBulletSize();
        int bulletSpeed = getBulletSpeed() * 2;

        // Adjust bullet position based on the tank's direction
        switch (direction) {
            case UP -> {
                centerBulletX = getCenterX();
                centerBulletY = y;
            }
            case DOWN -> {
                centerBulletX = getCenterX();
                centerBulletY = y + size;
            }
            case LEFT -> {
                centerBulletX = x;
                centerBulletY = getCenterY();
            }
            default -> { // RIGHT
                centerBulletX = x + size;
                centerBulletY = getCenterY();
            }
        }
        // Create and add the bullet
        Bullet bullet = new Bullet(centerBulletX, centerBulletY, bulletSize, bulletSpeed, direction, getBulletDamage());
        bullets.add(bullet);
        return bullet;
    }

    // Abstract methods for bullet size and speed
    protected abstract int getBulletSize();

    protected abstract int getBulletSpeed();

    // Update bullets and remove those out of bounds
    public void updateBullets() {
        bullets.forEach(Bullet::move);
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

    //Set image based on direction
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

    //Undo move (if collide)
    public void undoMove() {
        switch (this.direction) {
            case UP -> y += getSpeed();
            case DOWN -> y -= getSpeed();
            case LEFT -> x += getSpeed();
            case RIGHT -> x -= getSpeed();
        }
    }

    // Disable the tank
    public void disable() {
        disabled = true;
    }

    // Enable the tank
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
        if (isDisabled()) g.drawImage(DEAD_IMAGE, x, y, size, size, null); //dead tank image
        else {
            g.drawImage(tankImage, x, y, size, size, null);
            renderBullets(g); // Render bullets fired by the player tank
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }


    public Image getTankImage() {
        return tankImage;
    }

    public abstract int getBulletDamage();
}
