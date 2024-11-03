package jsd.project.tank90.model.powerups;

import java.awt.*;

public abstract class PowerUp {
    protected int x, y; // Position of the power-up
    protected int size; // Size of the power-up
    protected boolean isVisible = true; // Controls visibility

    public PowerUp(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    // Method to render the power-up on the screen
    public void render(Graphics g) {
        if (isVisible) {
            g.drawImage(getImage(), x, y, size, size, null);
        }
    }

    // Abstract method to get the specific image for each power-up type
    protected abstract Image getImage();

    // Abstract method to identify the power-up type
    public abstract String getType();

    // Optional: method to control flashing effects
    public void toggleVisibility() {
        isVisible = !isVisible; // Toggle visibility for flashing effect
    }
}
