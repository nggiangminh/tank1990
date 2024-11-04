package jsd.project.tank90.model.tanks;

import javax.swing.*;
import java.awt.*;

public class ArmorTank extends EnemyTank {

    private final Image TANK_UP = new ImageIcon("src/jsd/project/tank90/images/tank_armor_up.png").getImage();
    private final Image TANK_DOWN = new ImageIcon("src/jsd/project/tank90/images/tank_armor_down.png").getImage();
    private final Image TANK_LEFT = new ImageIcon("src/jsd/project/tank90/images/tank_armor_left.png").getImage();
    private final Image TANK_RIGHT = new ImageIcon("src/jsd/project/tank90/images/tank_armor_right.png").getImage();
    private final int speed = 1;
    private final int bulletSize = 5;
    private final int bulletSpeed = 2;
    private final int fireSpeed = 2;
    private final int bulletDamage = 1;
    private int life = 4;

    public ArmorTank(int x, int y, int size, Direction direction) {
        super(x, y, size, direction);
    }

    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    public int getFireSpeed() {
        return fireSpeed;
    }

    @Override
    public int getBulletDamage() {
        return bulletDamage;
    }

    @Override
    protected int getBulletSize() {
        return bulletSize;
    }

    @Override
    protected int getBulletSpeed() {
        return bulletSpeed;
    }

    @Override
    public int getSpeed() {
        return speed;
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


}
