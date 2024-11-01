package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class Tree extends GameObject {

    private final Image waterImage = new ImageIcon("src/jsd/project/tank90/images/trees2.png").getImage();
    public Tree(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(waterImage,x,y,size,size,null);
    }
}
