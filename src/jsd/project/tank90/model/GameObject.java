package jsd.project.tank90.model;

import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int size;

    public GameObject(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getCenterX() {
        return x + size / 2;
    }

    public int getCenterY() {
        return y + size / 2;
    }
    public int getSize() {return size;}

    // Get the bounding rectangle for collision detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public abstract void render(Graphics g);
}
