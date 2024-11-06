package jsd.project.tank90.model.powerups;

import jsd.project.tank90.model.environments.BrickWall;
import jsd.project.tank90.model.environments.SteelWall;
import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShovelPowerUp extends PowerUp {
    private static final Image SHOVEL_IMAGE = new ImageIcon("src/jsd/project/tank90/resources/images/powerup_shovel.png").getImage();

    public ShovelPowerUp(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    protected Image getImage() {
        return SHOVEL_IMAGE;
    }

    @Override
    public String getType() {
        return "Shovel";
    }

    @Override
    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        gamePanel.activateShovelEffect();
    }
}
