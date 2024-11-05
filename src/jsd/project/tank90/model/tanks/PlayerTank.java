package jsd.project.tank90.model.tanks;

import jsd.project.tank90.ui.GamePanel;

import javax.swing.*;
import java.awt.*;

public class PlayerTank extends Tank {

    private final Image TANK_UP = new ImageIcon("src/jsd/project/tank90/images/tank_up.png").getImage();
    private final Image TANK_DOWN = new ImageIcon("src/jsd/project/tank90/images/tank_down.png").getImage();
    private final Image TANK_LEFT = new ImageIcon("src/jsd/project/tank90/images/tank_left.png").getImage();
    private final Image TANK_RIGHT = new ImageIcon("src/jsd/project/tank90/images/tank_right.png").getImage();
    private int points = 0;

    private int life = 4;
    private int speed = 1;
    private int bulletSize = 7;
    private int bulletSpeed = 2;
    private int fireSpeed = 10;

    private int bulletDamage = 1;

    public PlayerTank(int x, int y, int size) {
        super(x, y, size, Direction.UP);
        this.tankImage = TANK_UP;
    }

    // Define unique bullet size and speed for PlayerTank
    @Override
    protected int getBulletSize() {
        return bulletSize;
    }

    public void setBulletSize(int bulletSize) {
        this.bulletSize = bulletSize;
    }

    @Override
    protected int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    @Override
    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Image getTankUpImage() {
        return TANK_UP;
    }

    @Override
    public Image getTankDownImage() {
        return TANK_DOWN;
    }

    @Override
    public Image getTankLeftImage() {
        return TANK_LEFT;
    }

    @Override
    public Image getTankRightImage() {
        return TANK_RIGHT;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getFireSpeed() {
        return fireSpeed;
    }

    public void setFireSpeed(int fireSpeed) {
        this.fireSpeed = fireSpeed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void increasePoints(int p) {
        setPoints(getPoints() + p);
    }
}
