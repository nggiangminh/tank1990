package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.PlayerTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LevelSelectionPanel extends JPanel {
    private final JLabel[] levelLabels;
    private final JLabel arrowLabel;
    private final JLabel titleLabel;  // Add title label
    private int currentIndex = 1;

    public LevelSelectionPanel() {
        setBackground(Color.BLACK);
        setLayout(null);
        setFocusable(true);
        requestFocusInWindow();

        // Title label setup
        titleLabel = new JLabel("Select Map");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        titleLabel.setBounds(80, 20, 200, 30);  // Position at the top of the panel
        add(titleLabel);

        arrowLabel = new JLabel("→");
        arrowLabel.setForeground(Color.WHITE);
        arrowLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        arrowLabel.setBounds(50, 80, 20, 20);
        add(arrowLabel);

        levelLabels = new JLabel[10];
        String[] levelNames = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7", "Level 8", "Level 9", "Level 10"};
        for (int i = 0; i < 10; i++) {
            levelLabels[i] = new JLabel(levelNames[i]);
            levelLabels[i].setForeground(Color.LIGHT_GRAY);
            levelLabels[i].setFont(new Font("Monospaced", Font.BOLD, 16));
            levelLabels[i].setBounds(80, 80 + i * 30, 100, 20);
            add(levelLabels[i]);
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moveSelectionUp();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    moveSelectionDown();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    openGamePlay(currentIndex);
                }
            }
        });
    }

    private void moveSelectionUp() {
        if (currentIndex > 1) {
            currentIndex--;
            updateArrowPosition();
        }
    }

    private void moveSelectionDown() {
        if (currentIndex < levelLabels.length) {
            currentIndex++;
            updateArrowPosition();
        }
    }

    private void updateArrowPosition() {
        arrowLabel.setLocation(50, 80 + (currentIndex - 1) * 30);
    }

    private void openGamePlay(int mapLevel) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel(mapLevel);
        PlayerTank playerTank = gamePanel.getPlayerTank();
        StatusPanel statusPanel = new StatusPanel(playerTank, gamePanel);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.EAST);
        Timer timer = new Timer(100, e -> statusPanel.repaint());
        timer.start();
        gamePanel.requestFocusInWindow();
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }
}
