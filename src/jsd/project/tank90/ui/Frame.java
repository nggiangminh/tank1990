package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.PlayerTank;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {
        // Set up the game window
        setupWindow();

        // Initialize game components
        initComponents();
    }

    // Set up the JFrame properties
    private void setupWindow() {
        setTitle("Tank 1990");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(900, 600)); // Adjust width to fit side panel
        setResizable(false);              // Prevent resizing
        setLocationRelativeTo(null);      // Center the window
    }

    // Initialize game components
    private void initComponents() {
        // Create the game panel
        GamePanel gamePanel = new GamePanel();


        // Use BorderLayout to position panels
        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame();
            frame.setVisible(true);
        });
    }
}
