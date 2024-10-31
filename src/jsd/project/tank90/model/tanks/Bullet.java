package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class Bullet extends GameObject {

    private final Image bulletImage = new ImageIcon("src/jsd/project/tank90/images/bullet.png").getImage();
    private int speed;
    private Direction direction;

    public Bullet(int x, int y, int size, int speed, Direction direction) {
        super(x, y, size);
        this.speed = speed;
        this.direction = direction;
    }

    // Move the bullet based on its direction and speed

    @Override
    public void update(){
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

    // Render the bullet on the screen
    @Override
    public void render(Graphics g) {
        g.drawImage(bulletImage,x,y,size,size,null);
    }
}

