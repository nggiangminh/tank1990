package jsd.project.tank90.model.tanks;

import jsd.project.tank90.utils.Direction;
import jsd.project.tank90.utils.Images;

import java.awt.*;

public class ArmorTank extends EnemyTank {

    // Get images
    private final Image TANK_UP_1_F = Images.ARMOR_UP_1_F;
    private final Image TANK_DOWN_1_F = Images.ARMOR_DOWN_1_F;
    private final Image TANK_LEFT_1_F = Images.ARMOR_LEFT_1_F;
    private final Image TANK_RIGHT_1_F = Images.ARMOR_RIGHT_1_F;
    private final Image TANK_UP_2_F = Images.ARMOR_UP_2_F;
    private final Image TANK_DOWN_2_F = Images.ARMOR_DOWN_2_F;
    private final Image TANK_LEFT_2_F = Images.ARMOR_LEFT_2_F;
    private final Image TANK_RIGHT_2_F = Images.ARMOR_RIGHT_2_F;
    private final int points = 400;
    private final int speed = 1;
    private final int bulletSize = 5;
    private final int bulletSpeed = 2;
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


    // Get images based on remaining lives
    @Override
    protected Image getTankUpImage1() {
        switch (life) {
            case 1 -> {
                return Images.ARMOR_UP_1_H1;
            }
            case 2 -> {
                return Images.ARMOR_UP_1_H2;
            }
            default -> {
                return Images.ARMOR_UP_1_H3;
            }
        }
    }

    @Override
    protected Image getTankDownImage1() {
        switch (life) {
            case 1 -> {
                return Images.ARMOR_DOWN_1_H1;
            }
            case 2 -> {
                return Images.ARMOR_DOWN_1_H2;
            }
            default -> {
                return Images.ARMOR_DOWN_1_H3;
            }
        }
    }

    @Override
    protected Image getTankLeftImage1() {
        switch (life) {
            case 1 -> {
                return Images.ARMOR_LEFT_1_H1;
            }
            case 2 -> {
                return Images.ARMOR_LEFT_1_H2;
            }
            default -> {
                return Images.ARMOR_LEFT_1_H3;
            }
        }
    }

    @Override
    protected Image getTankRightImage1() {
        switch (life) {
            case 1 -> {
                return Images.ARMOR_RIGHT_1_H1;
            }
            case 2 -> {
                return Images.ARMOR_RIGHT_1_H2;
            }
            default -> {
                return Images.ARMOR_RIGHT_1_H3;
            }
        }
    }

    @Override
    protected Image getTankUpImage2() {
        switch (life) {
            case 1 -> {
                return Images.ARMOR_UP_2_H1;
            }
            case 2 -> {
                return Images.ARMOR_UP_2_H2;
            }
            default -> {
                return Images.ARMOR_UP_2_H3;
            }
        }
    }

    @Override
    protected Image getTankDownImage2() {
        switch (life) {
            case 1 -> {
                return Images.ARMOR_DOWN_2_H1;
            }
            case 2 -> {
                return Images.ARMOR_DOWN_2_H2;
            }
            default -> {
                return Images.ARMOR_DOWN_2_H3;
            }
        }
    }

    @Override
    protected Image getTankLeftImage2() {
        switch (life) {
            case 1 -> {
                return Images.ARMOR_LEFT_2_H1;
            }
            case 2 -> {
                return Images.ARMOR_LEFT_2_H2;
            }
            default -> {
                return Images.ARMOR_LEFT_2_H3;
            }
        }
    }

    @Override
    protected Image getTankRightImage2() {
        switch (life) {
            case 1 -> {
                return Images.ARMOR_RIGHT_2_H1;
            }
            case 2 -> {
                return Images.ARMOR_RIGHT_2_H2;
            }
            default -> {
                return Images.ARMOR_RIGHT_2_H3;
            }
        }
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
}
