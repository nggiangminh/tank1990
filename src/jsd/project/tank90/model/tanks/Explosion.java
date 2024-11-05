package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Explosion extends GameObject {
    private boolean isFinished = false;
    private final Image EXPLODE_1 = new ImageIcon("src/jsd/project/tank90/images/exp1.png").getImage();
    private final Image EXPLODE_2 = new ImageIcon("src/jsd/project/tank90/images/exp2.png").getImage();
    private final Image EXPLODE_3 = new ImageIcon("src/jsd/project/tank90/images/exp3.png").getImage();
    private final Image EXPLODE_4 = new ImageIcon("src/jsd/project/tank90/images/exp4.png").getImage();
    public Explosion(int x, int y, int size) {
        super(x, y, size);
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public void render(Graphics g) {
        List<Image> explodeImages = new ArrayList<>(){};
        explodeImages.add(EXPLODE_1);
        explodeImages.add(EXPLODE_2);
        explodeImages.add(EXPLODE_3);
        explodeImages.add(EXPLODE_4);
        new Thread(() -> {
            try {
                for (Image explodeImage : explodeImages){
                    g.drawImage(explodeImage,x, y, size,size,null);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle thread interruption
            } finally {
                isFinished = true;
            }
        }).start();
    }
}
