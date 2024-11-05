package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public abstract class EnemyTank extends Tank {
    private int fireCooldown; // Cooldown timer for firing bullets
    private static final int FIRE_INTERVAL = 100; // Number of frames between shots
    private static final int DIRECTION_CHANGE_INTERVAL = 500; // Frames between direction changes

    private int directionChangeCooldown = DIRECTION_CHANGE_INTERVAL; // Timer for direction changes
    protected Random random = new Random(); // Random generator for movement

    public EnemyTank(int x, int y, int size, Direction direction) {
        super(x, y, size, direction); // Initial position, size, and default direction
        this.fireCooldown = FIRE_INTERVAL;
    }
    public Direction randomDirection(){
        Direction[] directions = Direction.values();
        return directions[random.nextInt(directions.length)];
    }
    // Change to a random direction
    public void changeDirection() {
        if (directionChangeCooldown <= 0) {
            Direction[] directions = Direction.values();
            setDirection(directions[random.nextInt(directions.length)]);
            directionChangeCooldown = DIRECTION_CHANGE_INTERVAL;
        } else {
            directionChangeCooldown--;
        }
    }

    public void turn() {
        if (random.nextBoolean()) {
            // Turn clockwise
            switch (direction) {
                case UP -> direction = Direction.RIGHT;
                case RIGHT -> direction = Direction.DOWN;
                case DOWN -> direction = Direction.LEFT;
                case LEFT -> direction = Direction.UP;
            }
        } else {
            // Turn counterclockwise
            switch (direction) {
                case UP -> direction = Direction.LEFT;
                case LEFT -> direction = Direction.DOWN;
                case DOWN -> direction = Direction.RIGHT;
                case RIGHT -> direction = Direction.UP;
            }
        }
    }

    // Shoot method with cooldown management
    @Override
    public Bullet shoot() {
        if (fireCooldown <= 0) {
            fireCooldown = FIRE_INTERVAL; // Reset cooldown
            return super.shoot(); // Use the shoot method from Tank to create a bullet
        }
        updateCooldown();
        return null;
    }

    // Update cooldown for shooting and other enemy behaviors
    public void updateCooldown() {
        if (fireCooldown > 0) {
            fireCooldown--;
        }
    }
    public abstract int getPoints();

    public abstract int getLife();

    public abstract void setLife(int life);
    public void takeDamage() {
        setLife(getLife()-1);
    }
    public boolean isDead() {
        return getLife() == 0;
    }

    @Override
    public int getBulletDamage() {
        return 1;
    }
}
