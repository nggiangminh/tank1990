package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.PlayerTank;
import javax.swing.*;
import java.awt.*;

public class LifePanel extends JPanel {
    private final PlayerTank playerTank;

    public LifePanel(PlayerTank playerTank) {
        this.playerTank = playerTank;
        setPreferredSize(new Dimension(200, 600)); // Adjust width as needed
        setBackground(Color.BLACK);                // Match game theme
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Player Lives: " + playerTank.getLife(), 20, 30);
    }

    // Method to update the panel when the player life changes
    public void updateLife() {
        repaint();
    }
}
