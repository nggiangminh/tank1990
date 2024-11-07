package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.utils.Images;

import java.awt.*;

public class BrickWall extends GameObject {

    private final Image BRICK_IMAGE = Images.BRICK;

    public BrickWall(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(BRICK_IMAGE, x, y, size, size, null);
    }
}
