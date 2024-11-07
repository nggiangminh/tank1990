package jsd.project.tank90.model.environments;

import java.awt.*;
import javax.swing.ImageIcon;
import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.Images;

public class Ice extends GameObject {
    private final Image ICE_IMAGE = Images.ICE;

    public Ice(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(ICE_IMAGE, x, y, size, size, null);
    }
}
