package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bullet extends GameObject {

    private final Image BULLET_UP = new ImageIcon("src/jsd/project/tank90/images/bullet_up.png").getImage();
    private final Image BULLET_DOWN = new ImageIcon("src/jsd/project/tank90/images/bullet_down.png").getImage();
    private final Image BULLET_LEFT = new ImageIcon("src/jsd/project/tank90/images/bullet_left.png").getImage();
    private final Image BULLET_RIGHT = new ImageIcon("src/jsd/project/tank90/images/bullet_right.png").getImage();



    private final int speed;
    private final Direction direction;

    private Image bulletImage;

    private int damage;


    public Bullet(int x, int y, int size, int speed, Direction direction, int damage) {
        super(x, y, size);
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
        return new Rectangle(x, y, 5, 10); // Adjust `width` and `height` as per bullet size
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
        g.drawImage(bulletImage, x, y, size, size, null);
    }
}

