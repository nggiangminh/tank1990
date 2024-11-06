package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.GameObject;

import javax.swing.*;
import java.awt.*;


public class Explosion extends GameObject {
    private final Image[] images;
    private boolean isFinished = false;
    private Image currentImage;
    private int currentFrame = 0;
    private long lastUpdateTime = 0; // Track time to control animation speed
    private final int animationDelay = 100; // Time in milliseconds between frames

    public Explosion(int centerX, int centerY, int size) {
        super(centerX - size / 2, centerY - size / 2, size); // Adjust so explosion is centered
        images = new Image[]{
                new ImageIcon("src/jsd/project/tank90/resources/images/exp1.png").getImage(),
                new ImageIcon("src/jsd/project/tank90/resources/images/exp2.png").getImage(),
                new ImageIcon("src/jsd/project/tank90/resources/images/exp3.png").getImage(),
                new ImageIcon("src/jsd/project/tank90/resources/images/exp4.png").getImage(),
                new ImageIcon("src/jsd/project/tank90/resources/images/exp5.png").getImage(),
                new ImageIcon("src/jsd/project/tank90/resources/images/bullet.png").getImage()
        };
        currentImage = images[0];
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void update() {
        if (isFinished) return;

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= animationDelay) {
            lastUpdateTime = currentTime;

            // Move to the next frame
            currentFrame++;
            if (currentFrame < images.length) {
                currentImage = images[currentFrame];
            } else {
                isFinished = true; // End animation when all frames are shown
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (!isFinished) {
            g.drawImage(currentImage, x, y, size, size, null);
        }
    }
}
