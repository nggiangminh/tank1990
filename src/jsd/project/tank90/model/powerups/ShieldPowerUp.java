// ShieldPowerUp.java
package jsd.project.tank90.model.powerups;

import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;

import javax.swing.*;
import java.awt.*;

public class ShieldPowerUp extends PowerUp {
    private static final Image SHIELD_POWER_UP = new ImageIcon("src/jsd/project/tank90/resources/images/powerup_helmet.png").getImage();

    public ShieldPowerUp(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    protected Image getImage() {
        return SHIELD_POWER_UP;
    }

    @Override
    public String getType() {
        return "Shield";
    }

    // Activate shield with a duration, managed by a separate thread
    @Override
    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        playerTank.activateShield(); // Enable the shield

    }
}
