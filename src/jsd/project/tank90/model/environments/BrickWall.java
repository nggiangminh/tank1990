package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class BrickWall extends GameObject {

    private final Image BRICK_IMAGE = new ImageIcon("src/jsd/project/tank90/resources/images/wall_brick.png").getImage();

    public BrickWall(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(BRICK_IMAGE, x, y, size, size, null);
    }
}
