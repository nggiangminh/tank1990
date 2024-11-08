package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.PlayerTank;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private final PlayerTank playerTank;

    public StatusPanel(PlayerTank playerTank) {
        this.playerTank = playerTank;
        setPreferredSize(new Dimension(225, 600)); // Width for panel; height matches game panel
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int lifeCount = playerTank.getLife();

        // Set font and color for displaying player stats
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));

        // Display the player's life and points
        g.drawString("Lives: " + lifeCount, 20, 50);
        g.drawString("Points: " + playerTank.getPoints(), 20, 100);

        // Add controls guide below statistics
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Controls:", 20, 150);
        g.drawString("Up Arrow - Move Up", 20, 180);
        g.drawString("Down Arrow - Move Down", 20, 200);
        g.drawString("Left Arrow - Move Left", 20, 220);
        g.drawString("Right Arrow - Move Right", 20, 240);
        g.drawString("Space - Shoot", 20, 270);
        g.drawString("P - Pause", 20, 290);
    }
}
