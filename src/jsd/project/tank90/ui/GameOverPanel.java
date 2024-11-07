package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.*;
import jsd.project.tank90.utils.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameOverPanel extends JPanel {
    private Image gameOverImage;
    private int yPosition;
    private final int targetY;
    private final JButton retryButton;
    private final JButton exitButton;
    public final String mapFile;
    private final List<EnemyTank> killedEnemies; // Store the killed enemies list
    private SoundManager soundManager;
    public GameOverPanel(String mapFile, List<EnemyTank> killedEnemies) {
        this.mapFile = mapFile;
        this.killedEnemies = killedEnemies;

        setBackground(Color.BLACK);


        gameOverImage = new ImageIcon("src/jsd/project/tank90/resources/images/game_over.png").getImage();
        gameOverImage = gameOverImage.getScaledInstance(180, 80, Image.SCALE_SMOOTH);

        yPosition = 600;
        targetY = 200;

        setLayout(null);

        int killedBasicTanks = 0;
        int killedFastTanks = 0;
        int killedPowerTanks = 0;
        int killedArmorTanks = 0;
        for (EnemyTank enemy : killedEnemies){
            if (enemy instanceof BasicTank) killedBasicTanks++;
            if (enemy instanceof FastTank) killedFastTanks++;
            if (enemy instanceof PowerTank) killedPowerTanks++;
            if (enemy instanceof ArmorTank) killedArmorTanks++;
        }
        int totalKilled = killedBasicTanks + killedFastTanks + killedPowerTanks + killedArmorTanks;

        // Create labels to display killed tanks information
        JLabel basicTankLabel = new JLabel("Basic Tanks Killed: " + killedBasicTanks);
        JLabel fastTankLabel = new JLabel("Fast Tanks Killed: " + killedFastTanks);
        JLabel powerTankLabel = new JLabel("Power Tanks Killed: " + killedPowerTanks);
        JLabel armorTankLabel = new JLabel("Armor Tanks Killed: " + killedArmorTanks);
        JLabel totalKilledLabel = new JLabel("Total Tanks Killed: " + totalKilled);

        Font labelFont = new Font("Monospaced", Font.BOLD, 14);
        basicTankLabel.setFont(labelFont);
        basicTankLabel.setForeground(Color.WHITE);
        fastTankLabel.setFont(labelFont);
        fastTankLabel.setForeground(Color.WHITE);
        powerTankLabel.setFont(labelFont);
        powerTankLabel.setForeground(Color.WHITE);
        armorTankLabel.setFont(labelFont);
        armorTankLabel.setForeground(Color.WHITE);
        totalKilledLabel.setFont(labelFont);
        totalKilledLabel.setForeground(Color.WHITE);

        // Position labels below the game-over image
        int labelYPosition = targetY + 100;
        basicTankLabel.setBounds(getWidth() / 2 , labelYPosition, 200, 30);
        fastTankLabel.setBounds(getWidth() / 2 , labelYPosition + 30, 200, 30);
        powerTankLabel.setBounds(getWidth() / 2 , labelYPosition + 60, 200, 30);
        armorTankLabel.setBounds(getWidth() / 2 , labelYPosition + 90, 200, 30);
        totalKilledLabel.setBounds(getWidth() / 2 , labelYPosition + 120, 200, 30);

        add(basicTankLabel);
        add(fastTankLabel);
        add(powerTankLabel);
        add(armorTankLabel);
        add(totalKilledLabel);

        retryButton = new JButton("Retry");
        exitButton = new JButton("Exit");

        Font buttonFont = new Font("Monospaced", Font.BOLD, 16);
        retryButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        // Initial button positions below the image and shifted left
        retryButton.setBounds(getWidth() / 2 - 80, yPosition + 100, 90, 40);
        exitButton.setBounds(getWidth() / 2 + 40, yPosition + 100, 90, 40);

        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retryGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(retryButton);
        add(exitButton);

        Timer timer = new Timer(20, e -> moveImageAndButtonsUp());
        timer.start();
    }

    private void moveImageAndButtonsUp() {
        if (yPosition > targetY) {
            yPosition -= 3;

            retryButton.setLocation(getWidth() / 2 - 100, yPosition + 100);
            exitButton.setLocation(getWidth() / 2 + 10, yPosition + 100);

            repaint();
        }
    }

    private void retryGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new LevelSelectionPanel());
        frame.revalidate();
        frame.repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gameOverImage, getWidth() / 2 - gameOverImage.getWidth(null) / 2, yPosition, this);
    }
}
