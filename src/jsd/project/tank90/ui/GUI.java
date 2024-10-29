package jsd.project.tank90.ui;

import javax.swing.JFrame;
import java.awt.Dimension;

public class GUI extends JFrame {

    public GUI() {
        // Set up the game window
        setupWindow();

        // Initialize game components
        initComponents();
    }

    // Set up the JFrame properties
    private void setupWindow() {
        setTitle("Tank 1990");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600)); // Set window size to 800x600
        setResizable(false);             // Prevent resizing
        setLocationRelativeTo(null);     // Center the window
    }

    // Initialize game components
    private void initComponents() {
        // Create and add the game panel here
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
    }
}
