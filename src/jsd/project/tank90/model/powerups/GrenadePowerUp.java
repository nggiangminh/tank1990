package jsd.project.tank90.model.powerups;

import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GrenadePowerUp extends PowerUp{

    private final Image GRENADE_IMAGE = new ImageIcon("src/jsd/project/tank90/images/powerup_grenade.png").getImage();
    public GrenadePowerUp(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    protected Image getImage() {
        return GRENADE_IMAGE;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        gamePanel.killAllEnemies();
    }
}
