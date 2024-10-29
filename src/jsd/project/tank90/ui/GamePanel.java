package jsd.project.tank90.ui;

import jsd.project.tank90.MapLoader;

import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;

public class GamePanel extends JPanel {
    private MapLoader mapLoader;
    private List<String> mapData;

    // Images for different tiles
    private Image wallImage;
    private Image waterImage;
    private Image borderImage;
    private Image baseImage;

    public GamePanel() {
        setBackground(Color.BLACK);

        // Load the map
        mapLoader = new MapLoader();
        mapLoader.loadMap("src/jsd/project/tank90/map_stage/map.txt"); // Update with actual file path
        mapData = mapLoader.getMapData();

        // Load images for tiles
        loadImages();
    }

    // Load images for map elements
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the map
        drawMap(g);
    }

    // Draw the map with images
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
}
