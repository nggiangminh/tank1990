package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.utils.Images;

import java.awt.*;

public class Base extends GameObject {

    private final Image BASE_IMAGE = Images.BASE;

    public Base(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(BASE_IMAGE, x, y, size, size, null);
    }
}
