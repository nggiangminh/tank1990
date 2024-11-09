package jsd.project.tank90.utils;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class Bullet extends GameObject {

    private final Image BULLET_UP = new ImageIcon("src/jsd/project/tank90/resources/images/bullet_up.png").getImage();
    private final Image BULLET_DOWN = new ImageIcon("src/jsd/project/tank90/resources/images/bullet_down.png").getImage();
    private final Image BULLET_LEFT = new ImageIcon("src/jsd/project/tank90/resources/images/bullet_left.png").getImage();
    private final Image BULLET_RIGHT = new ImageIcon("src/jsd/project/tank90/resources/images/bullet_right.png").getImage();
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


    public int getCenterX() {
        return x + size / 2;
    }

    public int getCenterY() {
        return y + size / 2;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size); // Adjust `width` and `height` as per bullet size
    }


    // Move the bullet based on its direction and speed

    public void update() {
        move();
    }


    public void move() {
        switch (direction) {
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public Image getBulletImage() {
        return bulletImage;
    }

    public void setBulletImage(Image bulletImage) {
        this.bulletImage = bulletImage;
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
            // Apply transparency or some effect when rendering
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g.drawImage(bulletImage, x, y, size, size, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        } else {
            g.drawImage(bulletImage, x, y, size, size, null);
        }
    }

    public void setOnIce(boolean onIce) {
        isOnIce = onIce;
    }
}

