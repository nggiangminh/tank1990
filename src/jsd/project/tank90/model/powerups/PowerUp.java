package jsd.project.tank90.model.powerups;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.tanks.PlayerTank;
import jsd.project.tank90.ui.GamePanel;

import java.awt.*;

public abstract class PowerUp extends GameObject {

    protected PlayerTank playerTank;
    protected GamePanel gamePanel;
    private boolean active = true; // Track if the power-up is active
    private int spawnFrame; // Frame when the power-up was spawned
    private static final int DURATION_FRAMES = 600; // Total duration in frames (10 seconds at 60 FPS)
    private static final int BLINK_START_FRAME = 300; // Start blinking in the last 5 seconds
    private boolean visible = true; // toggle visibility for blinking effect
    private int frameCounter = 0; // Track frames for blinking effect

    public PowerUp(int x, int y, int size) {
        super(x, y, size);
        this.spawnFrame = 0; // Start from frame 0
    }

    public void render(Graphics g) {
        // Display normally for the first 5s, blinking in the last 5s
        if (isActive()) {
            // Check if power-up should be blinking in the last 5 seconds
            if (spawnFrame >= BLINK_START_FRAME) {
                // toggle visibility every 12 sec
                if (frameCounter % 12 == 0) {
                    visible = !visible;
                }
                frameCounter++;
            } else {
                visible = true; // Remain visible for the initial duration
            }

            // Draw the power-up only if it's visible
            if (visible) {
                g.drawImage(getImage(), x, y, size, size, null);
            }

            spawnFrame++; // Increment the spawn frame counter
        }
    }

    protected abstract Image getImage();

    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        playerTank.increasePoints(500);
        this.active = false; // Deactivate if claimed
    }

    // Check if the power-up should still be active
    public boolean isActive() {
        if (spawnFrame >= DURATION_FRAMES) {
            active = false; // Deactivate after 10 seconds
        }
        return active;
    }
}
