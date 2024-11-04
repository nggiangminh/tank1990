package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class Base extends GameObject {

    private final Image baseImage = new ImageIcon("src/jsd/project/tank90/images/base.png").getImage();

    public Base(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(baseImage, x, y, size, size, null);
    }
}
