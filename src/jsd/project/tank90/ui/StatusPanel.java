package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.PlayerTank;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private final PlayerTank playerTank;
    private final GamePanel gamePanel;
    private final Color textColor = new Color(236, 240, 241);
    private final Color accentColor = new Color(52, 152, 219);

    public StatusPanel(PlayerTank playerTank, GamePanel gamePanel) {
        this.playerTank = playerTank;
        this.gamePanel = gamePanel;
        setPreferredSize(new Dimension(225, 600));
        Color backgroundColor = new Color(0, 0, 0);
        setBackground(backgroundColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw panel title
        g2d.setColor(accentColor);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 24));
        g2d.drawString("GAME STATUS", 20, 40);

        // Draw separator line
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(20, 50, getWidth() - 20, 50);



        // Display player stats
        g2d.setColor(textColor);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 15));
        g2d.drawString("Lives: " + playerTank.getLife(), 20, 90);
        g2d.drawString("Points: " + playerTank.getPoints(), 20, 120);

        //Display enemy left
        g2d.drawString("Enemy Left: " + gamePanel.getEnemyLeft(), 20, 150);

        // Draw controls guide
        g2d.setColor(accentColor);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 20));
        g2d.drawString("Controls", 20, 190);

        g2d.setColor(textColor);
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 14));
        String[] controls = {
                "↑   Move Up",
                "↓   Move Down",
                "←  Move Left",
                "→  Move Right",
                "Space : Shoot",
                "P : Pause"
        };

        int y = 200;
        for (String control : controls) {
            g2d.drawString(control, 20, y);
            y += 25;
        }

        // Draw decorative elements
        g2d.setColor(accentColor);
        g2d.drawRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 20, 20);
        g2d.drawRoundRect(15, 15, getWidth() - 30, getHeight() - 30, 15, 15);
    }
}