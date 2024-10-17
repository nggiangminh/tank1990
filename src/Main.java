import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel implements ActionListener {
    private Timer timer;
    
    public Main() {
        timer = new Timer(20, this);
        timer.start();
    }
    
    public void actionPerformed(ActionEvent e) {
        // This method will be called every 20 milliseconds
        // Update game logic here
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw game objects here
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tank 1990");
        Main game = new Main();
        frame.add(game);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}