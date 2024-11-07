package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.Images;

import javax.swing.*;
import java.awt.*;

public class Water extends GameObject {

    private final Image WATER_IMAGE = Images.WATER;

    public Water(int x, int y, int size) {
        super(x, y, size);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(WATER_IMAGE, x, y, size, size, null);
    }
}
