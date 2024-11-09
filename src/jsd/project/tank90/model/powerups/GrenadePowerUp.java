package jsd.project.tank90.model.powerups;

import jsd.project.tank90.utils.Images;
import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;

import java.awt.*;

public class GrenadePowerUp extends PowerUp {

    private final Image GRENADE_IMAGE = Images.GRENADE_PU;

    public GrenadePowerUp(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    protected Image getImage() {
        return GRENADE_IMAGE;
    }

    @Override
    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        super.activate(playerTank,gamePanel);
        gamePanel.killAllEnemies();
    }
}
