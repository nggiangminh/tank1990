package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.utils.Images;

import java.awt.*;


public class Base extends GameObject {

    private final Image BASE_IMAGE = Images.BASE;
    private final Image BASE_DESTROYED_IMAGE = Images.BASE_DESTROYED;
    private boolean isdestroyed = false;

    public Base(int x, int y, int size) {
        super(x, y, size);
    }

    public void destroy() {

        this.isdestroyed = true;
    }

    @Override
    public void render(Graphics g) {
        if (!isdestroyed) g.drawImage(BASE_IMAGE, x, y, size, size, null);
        if (isdestroyed) g.drawImage(BASE_DESTROYED_IMAGE, x, y, size, size, null);
    }
}
