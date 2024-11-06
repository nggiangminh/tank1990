package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class Water extends GameObject {

    private final Image WATER_IMAGE = new ImageIcon("src/jsd/project/tank90/resources/images/water.png").getImage();

    public Water(int x, int y, int size) {
        super(x, y, size);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(WATER_IMAGE, x, y, size, size, null);
    }
}
