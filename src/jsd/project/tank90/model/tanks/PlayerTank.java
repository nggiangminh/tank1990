package jsd.project.tank90.model.tanks;

import javax.swing.*;
import java.awt.*;

public class PlayerTank extends Tank {
    private final Image TANK_UP = new ImageIcon("src/jsd/project/tank90/images/tank_up.png").getImage();
    private final Image TANK_DOWN = new ImageIcon("src/jsd/project/tank90/images/tank_down.png").getImage();
    private final Image TANK_LEFT = new ImageIcon("src/jsd/project/tank90/images/tank_left.png").getImage();
    private final Image TANK_RIGHT = new ImageIcon("src/jsd/project/tank90/images/tank_right.png").getImage();

    public int speed = 1;
    public int bulletSize = 7;
    public int bulletSpeed = 2;
    public int fireSpeed = 2;
    private Image tankImage;

    public PlayerTank(int x, int y, int size, int speed) {
        super(x, y, size, speed, Direction.UP);
        this.tankImage = TANK_UP;
    }

    // Define unique bullet size and speed for PlayerTank
    @Override
    protected int getBulletSize() {
        return bulletSize;
    }

    @Override
    protected int getBulletSpeed() {
        return bulletSpeed;
    }

    @Override
    public void move() {
        switch (direction) {
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
        }
    }

    @Override
    public void undoMove() {
        switch (this.direction) {
            case UP -> y += speed;
            case DOWN -> y -= speed;
            case LEFT -> x += speed;
            case RIGHT -> x -= speed;
        }
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
        switch (newDirection) {
            case UP -> tankImage = TANK_UP;
            case DOWN -> tankImage = TANK_DOWN;
            case LEFT -> tankImage = TANK_LEFT;
            case RIGHT -> tankImage = TANK_RIGHT;
        }
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tankImage, x, y, size, size, null);
        renderBullets(g); // Render bullets fired by the player tank
    }
}
