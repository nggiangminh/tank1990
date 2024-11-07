package jsd.project.tank90.ui;

import jsd.project.tank90.utils.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
    private Image gameOverImage;
    private int yPosition;
    private final int targetY;
    private final JButton retryButton;
    private final JButton exitButton;
    public final String mapFile;
    private SoundManager soundManager;
    public GameOverPanel(String mapFile) {
        this.mapFile = mapFile;
        setBackground(Color.BLACK);


        gameOverImage = new ImageIcon("src/jsd/project/tank90/resources/images/game_over.png").getImage();
        gameOverImage = gameOverImage.getScaledInstance(180, 80, Image.SCALE_SMOOTH);

        yPosition = 600;
        targetY = 200;

        setLayout(null);

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
