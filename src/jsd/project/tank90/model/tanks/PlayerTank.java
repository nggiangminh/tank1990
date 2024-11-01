package jsd.project.tank90.model.tanks;

import javax.swing.*;
import java.awt.*;

public class PlayerTank extends Tank {
    private final Image TANK_UP = new ImageIcon("src/jsd/project/tank90/images/tank_up.png").getImage();
    private final Image TANK_DOWN = new ImageIcon("src/jsd/project/tank90/images/tank_down.png").getImage();
    private final Image TANK_LEFT = new ImageIcon("src/jsd/project/tank90/images/tank_left.png").getImage();
    private final Image TANK_RIGHT = new ImageIcon("src/jsd/project/tank90/images/tank_right.png").getImage();

    public int life = 4;
    public int speed = 1;
    public int bulletSize = 7;
    public int bulletSpeed = 2;
    public int fireSpeed = 45;

    public PlayerTank(int x, int y, int size) {
        super(x, y, size, Direction.UP);
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
