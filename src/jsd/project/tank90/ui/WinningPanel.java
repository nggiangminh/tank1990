package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.*;
import jsd.project.tank90.utils.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WinningPanel extends JPanel {

    public final int mapLevel;
    private final Image gameOverImage;
    private final List<EnemyTank> killedEnemies; // Store the killed enemies list
    private final PlayerTank playerTank;
    private SoundManager soundManager;

    public WinningPanel(int maplLevel, List<EnemyTank> killedEnemies, PlayerTank playerTank) {
        this.mapLevel = maplLevel;
        this.killedEnemies = killedEnemies;
        this.playerTank = playerTank;

        setBackground(new Color(20, 20, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        gameOverImage = new ImageIcon("src/jsd/project/tank90/resources/images/Game_Win.png").getImage();
        Image scaledGameOverImage = gameOverImage.getScaledInstance(500, 200, Image.SCALE_SMOOTH);

        // Create and style killed tanks information labels
        JLabel titleLabel = new JLabel(new ImageIcon(scaledGameOverImage));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(50));
        add(titleLabel);

        int killedBasicTanks = 0, killedFastTanks = 0, killedPowerTanks = 0, killedArmorTanks = 0;
        for (EnemyTank enemy : killedEnemies) {
            if (enemy instanceof BasicTank) killedBasicTanks++;
            else if (enemy instanceof FastTank) killedFastTanks++;
            else if (enemy instanceof PowerTank) killedPowerTanks++;
            else if (enemy instanceof ArmorTank) killedArmorTanks++;
        }
        int totalKilled = killedBasicTanks + killedFastTanks + killedPowerTanks + killedArmorTanks;

        Font labelFont = new Font("Monospaced", Font.BOLD, 16);
        Color labelColor = Color.WHITE;

        // Label panel for tank kill counts and player points
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setOpaque(false);

        // Add player points label
        JLabel pointsLabel = new JLabel("Points: " + playerTank.getPoints());
        pointsLabel.setFont(labelFont);
        pointsLabel.setForeground(labelColor);
        pointsLabel.setAlignmentX(CENTER_ALIGNMENT);
        labelPanel.add(pointsLabel);
        labelPanel.add(Box.createVerticalStrut(20));

        // Tank kill labels
        String[] labels = {"Basic Tanks Killed: " + killedBasicTanks, "Fast Tanks Killed: " + killedFastTanks, "Power Tanks Killed: " + killedPowerTanks, "Armor Tanks Killed: " + killedArmorTanks, "Total Tanks Killed: " + totalKilled};

        for (String text : labels) {
            JLabel label = new JLabel(text);
            label.setFont(labelFont);
            label.setForeground(labelColor);
            label.setAlignmentX(CENTER_ALIGNMENT);
            labelPanel.add(label);
            labelPanel.add(Box.createVerticalStrut(10));
        }

        add(Box.createVerticalStrut(20));
        add(labelPanel);
        add(Box.createVerticalStrut(30));

        // Next Level Button
        JButton nextLevelButton = new JButton("Next Level");
        styleButton(nextLevelButton);
        nextLevelButton.setAlignmentX(CENTER_ALIGNMENT); // Center alignment
        add(nextLevelButton);
        nextLevelButton.addActionListener(e -> nextLevel());
        add(Box.createVerticalStrut(20)); // Vertical spacing below button
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(60, 60, 60));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Extra padding for better vertical styling
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void nextLevel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel(mapLevel+1);  // Pass the map file path to GamePanel
        gamePanel.setPlayerTank(playerTank);
        playerTank.spawn();
        StatusPanel statusPanel = new StatusPanel(playerTank, gamePanel);// Pass the map file path to GamePanel
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.EAST);
        Timer timer = new Timer(100, e -> statusPanel.repaint());
        timer.start();
        gamePanel.requestFocusInWindow();
        frame.revalidate();
        frame.repaint();
    }
}
