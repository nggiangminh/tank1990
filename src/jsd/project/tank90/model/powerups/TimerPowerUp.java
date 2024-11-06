package jsd.project.tank90.model.powerups;

import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;

import javax.swing.*;
import java.awt.*;

public class TimerPowerUp extends PowerUp {
    private static final Image TIMER_IMAGE = new ImageIcon("src/jsd/project/tank90/resources/images/powerup_timer.png").getImage();

    public TimerPowerUp(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    protected Image getImage() {
        return TIMER_IMAGE;
    }

    @Override
    public String getType() {
        return "Timer";
    }

    @Override
    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        gamePanel.activateFreeze();
    }
}
