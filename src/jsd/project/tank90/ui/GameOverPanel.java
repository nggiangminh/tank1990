package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.*;
import jsd.project.tank90.utils.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameOverPanel extends JPanel {
    private final Image gameOverImage;
    private final JButton retryButton;
    private final JButton exitButton;
    public final String mapFile;
    private final List<EnemyTank> killedEnemies; // Store the killed enemies list
    private SoundManager soundManager;
    private final PlayerTank playerTank;

    public GameOverPanel(String mapFile, List<EnemyTank> killedEnemies, PlayerTank playerTank) {
        this.mapFile = mapFile;
        this.killedEnemies = killedEnemies;
        this.playerTank = playerTank;

        setBackground(new Color(20, 20, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        gameOverImage = new ImageIcon("src/jsd/project/tank90/resources/images/game_over.png").getImage();
        Image scaledGameOverImage = gameOverImage.getScaledInstance(250, 100, Image.SCALE_SMOOTH);

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
        String[] labels = {
                "Basic Tanks Killed: " + killedBasicTanks,
                "Fast Tanks Killed: " + killedFastTanks,
                "Power Tanks Killed: " + killedPowerTanks,
                "Armor Tanks Killed: " + killedArmorTanks,
                "Total Tanks Killed: " + totalKilled
        };

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

        // Buttons for retry and exit with improved styling
        retryButton = new JButton("Retry");
        exitButton = new JButton("Exit");

        styleButton(retryButton);
        styleButton(exitButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(retryButton);
        buttonPanel.add(exitButton);

        add(buttonPanel);
        add(Box.createVerticalStrut(20));

        retryButton.addActionListener(e -> retryGame());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void retryGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new LevelSelectionPanel());
        frame.revalidate();
        frame.repaint();
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(60, 60, 60));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
