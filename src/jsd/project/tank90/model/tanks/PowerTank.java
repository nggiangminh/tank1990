package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.Images;

import javax.swing.*;
import java.awt.*;

public class PowerTank extends EnemyTank {
    private final Image TANK_UP_1 = Images.POWER_UP_1;
    private final Image TANK_DOWN_1 = Images.POWER_DOWN_1;
    private final Image TANK_LEFT_1 = Images.POWER_LEFT_1;
    private final Image TANK_RIGHT_1 = Images.POWER_RIGHT_1;
    private final Image TANK_UP_2 = Images.POWER_UP_2;
    private final Image TANK_DOWN_2 = Images.POWER_DOWN_2;
    private final Image TANK_LEFT_2 = Images.POWER_LEFT_2;
    private final Image TANK_RIGHT_2 = Images.POWER_RIGHT_2;

    private final Image TANK_UP_1_F = Images.POWER_UP_1_F;
    private final Image TANK_DOWN_1_F = Images.POWER_DOWN_1_F;
    private final Image TANK_LEFT_1_F = Images.POWER_LEFT_1_F;
    private final Image TANK_RIGHT_1_F = Images.POWER_RIGHT_1_F;
    private final Image TANK_UP_2_F = Images.POWER_UP_2_F;
    private final Image TANK_DOWN_2_F = Images.POWER_DOWN_2_F;
    private final Image TANK_LEFT_2_F = Images.POWER_LEFT_2_F;
    private final Image TANK_RIGHT_2_F = Images.POWER_RIGHT_2_F;

    private final int points = 300;
    private final int speed = 2;
    private final int bulletSize = 5;
    private final int bulletSpeed = 3;
    private final int fireSpeed = 2;
    private int life = 1;

    public PowerTank(int x, int y, int size, Direction direction) {
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
    protected Image getTankUpImage1() {
        return TANK_UP_1;
    }

    @Override
    protected Image getTankDownImage1() {
        return TANK_DOWN_1;
    }

    @Override
    protected Image getTankLeftImage1() {
        return TANK_LEFT_1;
    }

    @Override
    protected Image getTankRightImage1() {
        return TANK_RIGHT_1;
    }

    @Override
    protected Image getTankUpFlashImage1() {
        return TANK_UP_1_F;
    }

    @Override
    protected Image getTankDownFlashImage1() {
        return TANK_DOWN_1_F;
    }

    @Override
    protected Image getTankLeftFlashImage1() {
        return TANK_LEFT_1_F;
    }

    @Override
    protected Image getTankRightFlashImage1() {
        return TANK_RIGHT_1_F;
    }

    @Override
    protected Image getTankUpImage2() {
        return TANK_UP_2;
    }

    @Override
    protected Image getTankDownImage2() {
        return TANK_DOWN_2;
    }

    @Override
    protected Image getTankLeftImage2() {
        return TANK_LEFT_2;
    }

    @Override
    protected Image getTankRightImage2() {
        return TANK_RIGHT_2;
    }

    @Override
    protected Image getTankUpFlashImage2() {
        return TANK_UP_2_F;
    }

    @Override
    protected Image getTankDownFlashImage2() {
        return TANK_DOWN_2_F;
    }

    @Override
    protected Image getTankLeftFlashImage2() {
        return TANK_LEFT_2_F;
    }

    @Override
    protected Image getTankRightFlashImage2() {
        return TANK_RIGHT_2_F;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int getBulletDamage() {
        return 2;
    }
}
