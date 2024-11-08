package jsd.project.tank90.ui;

import javax.swing.*;
import java.awt.*;

public class PauseOverlay extends JPanel {

    private final JLabel pauseLabel;
    private final JLabel guideLabel ;

    public PauseOverlay() {
        setLayout(new GridBagLayout());  // Center components within the panel
        setOpaque(false); // Transparent background

        // Configure the pause label
        pauseLabel = new JLabel("PAUSE");
        pauseLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setOpaque(true);
        pauseLabel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent background with black overlay        pauseLabel = new JLabel("PAUSE");


        guideLabel = new JLabel("Press P to continue...");
        guideLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        guideLabel.setForeground(Color.WHITE);
        guideLabel.setOpaque(true);
        guideLabel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent background with black overlay

        // Center the label in the overlay
        GridBagConstraints pl = new GridBagConstraints();
        GridBagConstraints gl = new GridBagConstraints();
        pl.gridx = 0;
        pl.gridy = 0;
        pl.insets = new Insets(250, 140, 300, 200);

        gl.gridx = 0;
        gl.gridy = 0;
        gl.insets = new Insets(250, 150, 200, 200);

        add(pauseLabel, pl);
        add(guideLabel, gl);

        setVisible(false); // Initially hidden
    }

    // Method to toggle the pause overlay
    public void togglePause(boolean isPaused) {
        setVisible(isPaused);
    }
}
