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

        // Display the player's life
        g.drawString("Lives: " + lifeCount, 20, 50);
        g.drawString("Points: " + playerTank.getPoints(), 20, 100);
    }
}
