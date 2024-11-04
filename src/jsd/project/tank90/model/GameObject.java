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
    public int getSize() {return size;}

    // Get the bounding rectangle for collision detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    // Check if the object is out of screen bounds
    public boolean isOutOfBounds(int width, int height) {
        return x < 0 || y < 0 || x + size > width || y + size > height;
    }

    public abstract void render(Graphics g);
}
