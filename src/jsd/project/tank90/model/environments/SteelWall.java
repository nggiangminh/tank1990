package jsd.project.tank90.model.environments;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class SteelWall extends GameObject {

    private final Image steelImage = new ImageIcon("src/jsd/project/tank90/images/wall_steel.png").getImage();
    public SteelWall(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(steelImage,x,y,size,size,null);
    }
}
