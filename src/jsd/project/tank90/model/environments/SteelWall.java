package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.utils.Images;

import java.awt.*;

public class SteelWall extends GameObject {

    private final Image STEEL_IMAGE = Images.STEEL;

    private boolean isDestructable = true;

    public SteelWall(int x, int y, int size) {
        super(x, y, size);
    }

    public SteelWall(int x, int y, int size, boolean isDestructable) {
        super(x, y, size);
        this.isDestructable = isDestructable;
    }

    public boolean isDestructable() {
        return isDestructable;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(STEEL_IMAGE, x, y, size, size, null);
    }
}
