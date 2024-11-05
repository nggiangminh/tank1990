package jsd.project.tank90.model.powerups;

import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;

import javax.swing.*;
import java.awt.*;

public class ShieldPowerUp extends PowerUp {
    private static final Image SHIELD_IMAGE_1 = new ImageIcon("src/jsd/project/tank90/images/shield_1.png").getImage();
    private static final Image SHIELD_IMAGE_2 = new ImageIcon("src/jsd/project/tank90/images/shield_2.png").getImage();

    private boolean toggle; // To alternate between the two images

    public ShieldPowerUp(int x, int y, int size) {
        super(x, y, size);
        toggle = false; // Start with the first image
    }

    @Override
    protected Image getImage() {
        toggle = !toggle; // Switch the toggle
        return toggle ? SHIELD_IMAGE_1 : SHIELD_IMAGE_2; // Return the image based on the toggle
    }

    @Override
    public String getType() {
        return "Shield";
    }

    @Override
    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        int shieldDuration = 300; // Duration in frames (or adjust as needed)
        playerTank.activateShield(shieldDuration);
    }

}
