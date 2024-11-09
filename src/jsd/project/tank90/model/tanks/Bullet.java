package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.utils.Direction;
import jsd.project.tank90.utils.Images;

import javax.swing.*;
import java.awt.*;

public class Bullet extends GameObject {

    //Get images
    private final Image BULLET_UP = Images.BULLET_UP;
    private final Image BULLET_DOWN = Images.BULLET_DOWN;
    private final Image BULLET_LEFT = Images.BULLET_LEFT;
    private final Image BULLET_RIGHT = Images.BULLET_RIGHT;
    private final int speed;
    private final Direction direction;
    private boolean isOnIce = false;
    private Image bulletImage;
    private int damage;


    public Bullet(int centerX, int centerY, int size, int speed, Direction direction, int damage) {
        super(centerX - size / 2, centerY - size / 2, size);
        this.speed = speed;
        this.direction = direction;
        this.damage = damage;
        switch (direction) {
            case UP -> bulletImage = BULLET_UP;
            case DOWN -> bulletImage = BULLET_DOWN;
            case LEFT -> bulletImage = BULLET_LEFT;
            case RIGHT -> bulletImage = BULLET_RIGHT;
        }
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size); // Adjust `width` and `height` as per bullet size
    }


    // Move the bullet based on its direction and speed
    public void move() {
        switch (direction) {
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Render the bullet on the screen
    @Override
    public void render(Graphics g) {
        if (isOnIce) {
            // Apply transparency when on ice
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g.drawImage(bulletImage, x, y, size, size, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        } else {
            g.drawImage(bulletImage, x, y, size, size, null);
        }
    }

    // Set onIce status
    public void setOnIce(boolean onIce) {
        isOnIce = onIce;
    }
}

