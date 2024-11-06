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
    private static final int BLINK_START_FRAME = 300; // Start blinking in the last 5 seconds (5 * 60 frames)
    private boolean visible = true; // Used to toggle visibility for blinking effect
    private int frameCounter = 0; // Track frames for blinking effect

    public PowerUp(int x, int y, int size) {
        super(x, y, size);
        this.spawnFrame = 0; // Start from frame 0
    }

    // Method to render the power-up on the screen
    public void render(Graphics g) {
        if (isActive()) {
            // Check if power-up should be blinking in the last 5 seconds
            if (spawnFrame >= BLINK_START_FRAME) {
                // toggle visibility de nhay moi 12 frames
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

    // Abstract method to get the specific image for each power-up type
    protected abstract Image getImage();

    // Abstract method to identify the power-up type
    public abstract String getType();

    public void activate(PlayerTank playerTank, GamePanel gamePanel) {
        playerTank.increasePoints(500);
        this.active = false; // Deactivate the power-up after it is used
    }

    // Method to check if the power-up should still be active
    public boolean isActive() {
        // Check if the duration has passed in frames
        if (spawnFrame >= DURATION_FRAMES) {
            active = false; // Deactivate the power-up after 10 seconds
        }
        return active;
    }
}
