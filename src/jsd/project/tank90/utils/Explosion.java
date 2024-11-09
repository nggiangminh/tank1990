package jsd.project.tank90.utils;

import jsd.project.tank90.model.GameObject;

import java.awt.*;


public class Explosion extends GameObject {
    private final Image[] images;
    private final int animationDelay = 100; // Time in milliseconds between frames
    private boolean isFinished = false;
    private Image currentImage;
    private int currentFrame = 0;
    private long lastUpdateTime = 0; // Track time to control animation speed

    public Explosion(int centerX, int centerY, int size) {
        super(centerX - size / 2, centerY - size / 2, size); // Adjust so explosion is centered
        images = new Image[]{Images.EXPLO_1, Images.EXPLO_2, Images.EXPLO_3, Images.EXPLO_4, Images.EXPLO_5};
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
