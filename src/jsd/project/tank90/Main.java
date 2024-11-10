package jsd.project.tank90;

import jsd.project.tank90.ui.Frame;
import jsd.project.tank90.ui.MenuPanel;

public class Main {
    public static void main(String[] arguments) {
        // Create an instance of the GUI
        Frame frame = new Frame();
        frame.getContentPane().add(new MenuPanel());
        // Display the game window
        frame.setVisible(true);
    }
}
