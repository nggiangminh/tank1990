package jsd.project.tank90;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tank {
    private int x, y; // Position of the tank
    private Image currentImage; // Current tank image
    private Image upImage, downImage, leftImage, rightImage; // Tank images for directions
    private final int SIZE = 40; // Size of the tank

    public Tank(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        loadImages(); // Load tank images
        currentImage = upImage; // Set default image as facing up
    }

    // Load images for the tank in different directions
    private void loadImages() {
        try {
            upImage = ImageIO.read(new File("src/jsd/project/tank90/images/tank_up.png"));
            downImage = ImageIO.read(new File("src/jsd/project/tank90/images/tank_down.png"));
            leftImage = ImageIO.read(new File("src/jsd/project/tank90/images/tank_left.png"));
            rightImage = ImageIO.read(new File("src/jsd/project/tank90/images/tank_right.png"));
        } catch (IOException e) {
            System.err.println("Error loading tank images: " + e.getMessage());
        }
    }

    // Move tank right
    public void moveRight() {
        x += 5;
        currentImage = rightImage;
    }

    // Move tank left
    public void moveLeft() {
        x -= 5;
        currentImage = leftImage;
    }

    // Move tank up
    public void moveUp() {
        y -= 5;
        currentImage = upImage;
    }

    // Move tank down
    public void moveDown() {
        y += 5;
        currentImage = downImage;
    }

    // Draw the tank
    public void draw(Graphics g) {
        if (currentImage != null) {
            g.drawImage(currentImage, x, y, SIZE, SIZE, null); // Draw the current image
        }
    }
}

