package jsd.project.tank90.model.powerups;

import jsd.project.tank90.utils.Images;
import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;

import java.awt.*;

public class TankPowerUp extends PowerUp {
    private static final Image TANK_IMAGE = Images.TANK_PU;

    public TankPowerUp(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    protected Image getImage() {
        return TANK_IMAGE;
    }

    @Override
    public String getType() {
        return "Tank";
    }

    @Override
    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        playerTank.setLife(playerTank.getLife() + 1);
    }
}
