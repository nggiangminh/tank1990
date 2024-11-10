package jsd.project.tank90.ui;

import jsd.project.tank90.utils.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuPanel extends JPanel {
    private Image titleImage;
    private Image tankIcon;
    private int titleY = 700;
    private int playerY = 900;
    private int copyrightY = 950;
    private SoundManager soundManager;

    public MenuPanel() {
        soundManager = new SoundManager();
        soundManager.playMenuBackgroundMusic();

        // Load images
        titleImage = new ImageIcon("src/jsd/project/tank90/resources/images/battle_city.png").getImage();
        tankIcon = new ImageIcon("src/jsd/project/tank90/resources/images/tank_player_right_1_s1.png").getImage();

        // Timer for smooth animation
        Timer timer = new Timer(20, e -> {
            if (titleY > 100) titleY -= 3;
            if (playerY > 300) playerY -= 3;
            if (copyrightY > 350) copyrightY -= 3;
            repaint();
        });
        timer.start();

        // Key listener for Enter key
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    openLevelSelection();
                }
            }
        });
    }

    private void openLevelSelection() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new LevelSelectionPanel());
        soundManager.stopBackgroundMusic();
        frame.revalidate();
        frame.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        // Draw title image
        int titleX = (getWidth() - titleImage.getWidth(null)) / 2;
        g.drawImage(titleImage, titleX, titleY, this);

        // Draw "1 PLAYER" text and tank icon
        g.setFont(new Font("Monospaced", Font.BOLD, 16));
        int playerX = getWidth() / 2 - 40;
        g.drawImage(tankIcon, playerX - 30, playerY - 15, this);
        g.drawString("Play Game", playerX, playerY);

        // Draw copyright text
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        int copyrightX = getWidth() / 2 - 70;
        g.drawString("© YS 1990", copyrightX, copyrightY);
    }
}
