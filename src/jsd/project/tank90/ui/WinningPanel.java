package jsd.project.tank90.ui;

import javax.swing.*;
import java.awt.*;

public class WinningPanel extends JPanel {

    public WinningPanel() {
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Congratulations! You’ve won!", 100, 200);
    }

    public void displayWinningScreen(JFrame gameFrame) {
        gameFrame.getContentPane().removeAll();
        gameFrame.getContentPane().add(this);
        gameFrame.revalidate();
        gameFrame.repaint();
    }
}
