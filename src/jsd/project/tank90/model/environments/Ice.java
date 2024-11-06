package jsd.project.tank90.model.environments;

import java.awt.*;
import javax.swing.ImageIcon;
import jsd.project.tank90.model.GameObject;

public class Ice extends GameObject {
    private final Image ICE_IMAGE = new ImageIcon("src/jsd/project/tank90/resources/images/ice.png").getImage();

    public Ice(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(ICE_IMAGE, x, y, size, size, null);
    }
}
