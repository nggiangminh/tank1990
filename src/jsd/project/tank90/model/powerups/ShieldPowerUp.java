package jsd.project.tank90.model.powerups;

import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;
import jsd.project.tank90.utils.Images;

import java.awt.*;

public class ShieldPowerUp extends PowerUp {
    private static final Image SHIELD_POWER_UP = Images.SHIELD_PU;

    public ShieldPowerUp(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    protected Image getImage() {
        return SHIELD_POWER_UP;
    }

    @Override
    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        super.activate(playerTank, gamePanel);
        playerTank.activateShield(); // Enable the shield
    }
}
