package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;

import java.awt.*;
import java.util.Random;

public abstract class EnemyTank extends Tank {

    private boolean disabled = false;
    private int fireCooldown; // Cooldown timer for firing bullets
    private static final int FIRE_INTERVAL = 100; // Number of frames between shots
    private static final int DIRECTION_CHANGE_INTERVAL = 50; // Frames between direction changes

    private int directionChangeCooldown = DIRECTION_CHANGE_INTERVAL; // Timer for direction changes
    protected Random random = new Random(); // Random generator for movement

    private boolean showPoints = false; // Flag to show points when tank is destroyed
    private long pointsDisplayStartTime; // Time when points start displaying
    private static final int POINTS_DISPLAY_DURATION = 1000; // Points display duration in milliseconds (1 second)

    public EnemyTank(int x, int y, int size, Direction direction) {
        super(x, y, size, direction); // Initial position, size, and default direction
        this.fireCooldown = FIRE_INTERVAL;
    }

    // Return a random direction
    public Direction randomDirection() {
        Direction[] directions = Direction.values();
        return directions[random.nextInt(directions.length)];
    }

    // Change to a random direction
    public void changeDirection() {
        if (directionChangeCooldown <= 0) {
            setDirection(randomDirection());
            directionChangeCooldown = DIRECTION_CHANGE_INTERVAL;
        } else {
            directionChangeCooldown--;
        }
    }

    // Turn the tank 90 degrees, either clockwise or counterclockwise
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

    // Abstract methods for subclasses
    public abstract int getPoints();
    public abstract int getLife();
    public abstract void setLife(int life);

    // Handle damage and death
    public void takeDamage() {
        setLife(getLife() - 1);
        if (getLife() == 0)
            markAsDead();
    }

    // Check if the tank is dead (i.e., has zero life)
    public boolean isDead() {
        return getLife() == 0;
    }

    // Instantly kill the tank
    public void kill() {
        setLife(0);
    }

    public boolean isShowPoints() {
        return showPoints;
    }

    public void setShowPoints(boolean showPoints) {
        this.showPoints = showPoints;
    }

    public void disable(){
        disabled = true;
    }
    public boolean isDisabled(){
        return disabled;
    }
    // Mark the tank as dead and start showing points
    public void markAsDead() {
        showPoints = true;
        pointsDisplayStartTime = System.currentTimeMillis();
        disable();
    }

    // Check if the points display duration has passed and the tank should be removed
    public boolean shouldRemove() {
        return showPoints && (System.currentTimeMillis() - pointsDisplayStartTime > POINTS_DISPLAY_DURATION);
    }

    // Render the tank and points if dead
    @Override
    public void render(Graphics g) {
        if (showPoints) {
            // Render points in white font
            g.setColor(Color.WHITE);
            g.setFont(new Font("Monospaced", Font.BOLD, 14));

            // Display points at the center of the tank
            String points = Integer.toString(getPoints());
            g.drawString(points, getCenterX(), getCenterY());
        } else {
            // Render the tank image if it's still alive
            g.drawImage(getTankImage(), x, y, size, size, null);
        }
    }

    @Override
    public int getBulletDamage() {
        return 1;
    }
}
