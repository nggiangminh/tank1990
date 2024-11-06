package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.PlayerTank;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {
        setupWindow();
        initComponents();
    }

    private void setupWindow() {
        setTitle("Tank 1990");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600)); // Adjust width to fit both panels
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Create GamePanel and access PlayerTank
        GamePanel gamePanel = new GamePanel();
        PlayerTank playerTank = gamePanel.getPlayerTank();

        // Create StatusPanel and pass PlayerTank to it
        StatusPanel statusPanel = new StatusPanel(playerTank);

        // Use BorderLayout to position panels
        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.EAST);

        Timer timer = new Timer(100, e -> statusPanel.repaint());
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame();
            frame.setVisible(true);
        });
    }
}
