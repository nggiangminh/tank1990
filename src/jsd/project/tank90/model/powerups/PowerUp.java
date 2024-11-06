package jsd.project.tank90.model.powerups;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;

import java.awt.*;

public abstract class PowerUp extends GameObject {

    protected PlayerTank playerTank;
    protected GamePanel gamePanel;
    public PowerUp(int x, int y, int size) {
        super(x, y, size);
    }


    // Method to render the power-up on the screen
    public void render(Graphics g) {
        g.drawImage(getImage(), x, y, size, size, null);
    }

    // Abstract method to get the specific image for each power-up type
    protected abstract Image getImage();

    // Abstract method to identify the power-up type
    public abstract String getType();

    public void activate(PlayerTank playerTank, GamePanel gamePanel){
        playerTank.increasePoints(500);
    };
}
