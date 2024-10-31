package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class BrickWall extends GameObject {

    private final Image brickImage = new ImageIcon("src/jsd/project/tank90/images/wall_brick.png").getImage();
    public BrickWall(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(brickImage,x,y,size,size,null);
    }
}
