package jsd.project.tank90.ui;

import jsd.project.tank90.MapLoader;
import jsd.project.tank90.Tank;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {
    // Instance variables
    private MapLoader mapLoader;
    private List<String> mapData;

    // Images for different tiles
    private Image wallImage;
    private Image waterImage;
    private Image borderImage;
    private Image baseImage;

    // Tank instance
    private Tank tank;

    // Constructor
    public GamePanel() {
        // Set up the panel
        setBackground(Color.BLACK);

        // Load the map
        mapLoader = new MapLoader();
        mapLoader.loadMap("src/jsd/project/tank90/map_stage/map.txt"); // Update with actual file path
        mapData = mapLoader.getMapData();

        // Load images for tiles
        loadImages();

        // Initialize the tank at starting position
        tank = new Tank(100, 100); // Set initial tank position

        // Enable KeyListener for tank movement
        setFocusable(true);
        addKeyListener(this);
    }

    // Method to load images for map elements
    private void loadImages() {
        try {
            wallImage = loadImage("src/jsd/project/tank90/images/wall_brick.png");
            waterImage = loadImage("src/jsd/project/tank90/images/water.png");
            borderImage = loadImage("src/jsd/project/tank90/images/wall_steel.png");
            baseImage = loadImage("src/jsd/project/tank90/images/base.png");
        } catch (IOException e) {
            System.err.println("Error loading images: " + e.getMessage());
        }
    }

    // Helper method to load an image
    private Image loadImage(String path) throws IOException {
        File imgFile = new File(path);
        if (!imgFile.exists()) {
            throw new IOException("File not found: " + path);
        }
        return ImageIO.read(imgFile);
    }

    // Method to draw the map with images
    private void drawMap(Graphics g) {
        int tileSize = 20; // Size of each tile

        for (int y = 0; y < mapData.size(); y++) {
            String line = mapData.get(y);
            for (int x = 0; x < line.length(); x++) {
                char tile = line.charAt(x);
                switch (tile) {
                    case '1':
                        drawTile(g, wallImage, x, y, tileSize);
                        break;
                    case '2':
                        drawTile(g, waterImage, x, y, tileSize);
                        break;
                    case '5':
                        drawTile(g, borderImage, x, y, tileSize);
                        break;
                    case '9':
                        drawTile(g, baseImage, x, y, tileSize);
                        break;
                    // No image for open space ('0')
                }
            }
        }
    }

    // Helper method to draw a tile
    private void drawTile(Graphics g, Image img, int x, int y, int tileSize) {
        if (img != null) {
            g.drawImage(img, x * tileSize, y * tileSize, tileSize, tileSize, this);
        }
    }

    // Override paintComponent method to draw the game elements
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the map
        drawMap(g);

        // Draw the tank
        tank.draw(g);
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Move tank based on arrow key presses
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                tank.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                tank.moveLeft();
                break;
            case KeyEvent.VK_UP:
                tank.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                tank.moveDown();
                break;
        }

        repaint(); // Redraw the tank after moving
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}