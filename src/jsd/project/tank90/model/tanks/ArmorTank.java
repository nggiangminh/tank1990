package jsd.project.tank90.model.tanks;

import javax.swing.*;
import java.awt.*;

public class ArmorTank extends EnemyTank {

    private final Image TANK_UP = new ImageIcon("src/jsd/project/tank90/resources/images/tank_armor_up.png").getImage();
    private final Image TANK_DOWN = new ImageIcon("src/jsd/project/tank90/resources/images/tank_armor_down.png").getImage();
    private final Image TANK_LEFT = new ImageIcon("src/jsd/project/tank90/resources/images/tank_armor_left.png").getImage();
    private final Image TANK_RIGHT = new ImageIcon("src/jsd/project/tank90/resources/images/tank_armor_right.png").getImage();
    private final Image TANK_UP_FLASH = new ImageIcon("src/jsd/project/tank90/resources/images/tank_armor_up_f.png").getImage();
    private final Image TANK_DOWN_FLASH = new ImageIcon("src/jsd/project/tank90/resources/images/tank_armor_down_f.png").getImage();
    private final Image TANK_LEFT_FLASH = new ImageIcon("src/jsd/project/tank90/resources/images/tank_armor_left_f.png").getImage();
    private final Image TANK_RIGHT_FLASH = new ImageIcon("src/jsd/project/tank90/resources/images/tank_armor_right_f.png").getImage();
    private final int points = 400;
    private final int speed = 1;
    private final int bulletSize = 5;
    private final int bulletSpeed = 2;
    private final int fireSpeed = 2;
    private int life = 4;

    public ArmorTank(int x, int y, int size, Direction direction) {
        super(x, y, size, direction);
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
    protected Image getTankUpImage() {
        return TANK_UP;
    }

    @Override
    protected Image getTankDownImage() {
        return TANK_DOWN;
    }

    @Override
    protected Image getTankLeftImage() {
        return TANK_LEFT;
    }

    @Override
    protected Image getTankRightImage() {
        return TANK_RIGHT;
    }

    @Override
    protected Image getTankUpFlashImage() {
        return TANK_UP_FLASH;
    }

    @Override
    protected Image getTankDownFlashImage() {
        return TANK_DOWN_FLASH;
    }

    @Override
    protected Image getTankLeftFlashImage() {
        return TANK_LEFT_FLASH;
    }

    @Override
    protected Image getTankRightFlashImage() {
        return TANK_RIGHT_FLASH;
    }

    public int getPoints() {
        return points;
    }
}
