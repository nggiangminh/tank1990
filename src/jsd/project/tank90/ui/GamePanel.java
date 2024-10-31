package jsd.project.tank90.ui;

import jsd.project.tank90.MapLoader;
import jsd.project.tank90.model.environments.Base;
import jsd.project.tank90.model.environments.BrickWall;
import jsd.project.tank90.model.environments.SteelWall;
import jsd.project.tank90.model.environments.Water;
import jsd.project.tank90.model.tanks.Direction;
import jsd.project.tank90.model.tanks.PlayerTank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener, Runnable {
    private final MapLoader mapLoader;
    private final List<String> mapData;
    private final PlayerTank playerTank;
    private Image wallImage;
    private Image waterImage;
    private Image borderImage;
    private Image baseImage;
    private boolean running = true;

    // Movement and firing control booleans
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isFire = false;

    // Firing cooldown to manage fire rate
    private int fireCooldown = 0; // Counts down to zero for each fire

    public GamePanel() {
        setBackground(Color.BLACK);

        mapLoader = new MapLoader();
        mapLoader.loadMap("src/jsd/project/tank90/map_stage/map.txt");
        mapData = mapLoader.getMapData();

        loadImages();
        playerTank = new PlayerTank(200, 200, 20, 5);

        setFocusable(true);
        addKeyListener(this);

        // Start the game loop in a new thread
        new Thread(this).start();
    }

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

    private Image loadImage(String path) throws IOException {
        File imgFile = new File(path);
        if (!imgFile.exists()) {
            throw new IOException("File not found: " + path);
        }
        return ImageIO.read(imgFile);
    }

    private void drawMap(Graphics g) {
        int tileSize = 20;

        for (int y = 0; y < mapData.size(); y++) {
            String line = mapData.get(y);
            for (int x = 0; x < line.length(); x++) {
                char tile = line.charAt(x);
                switch (tile) {
                    case '1':
                        BrickWall br = new BrickWall(x*tileSize, y*tileSize, tileSize);
                        br.render(g);
                        break;
                    case '2' :
                        Water water = new Water(x*tileSize, y*tileSize, tileSize);
                        water.render(g);
                        break;
                    case '5' :
                        SteelWall st = new SteelWall(x*tileSize, y*tileSize, tileSize);
                        st.render(g);
                        break;
                    case '9' :
                        Base base = new Base(x*tileSize, y*tileSize, tileSize*2);
                        base.render(g);
                        break;
                }
            }
        }
    }

    private void drawTile(Graphics g, Image img, int x, int y, int tileSize) {
        if (img != null) {
            g.drawImage(img, x * tileSize, y * tileSize, tileSize, tileSize, this);
        }
    }

    public void updateGame() {
        // Move based on movement booleans
        if (isUp) {
            playerTank.setDirection(Direction.UP);
            playerTank.move();
        } else if (isDown) {
            playerTank.setDirection(Direction.DOWN);
            playerTank.move();
        } else if (isLeft) {
            playerTank.setDirection(Direction.LEFT);
            playerTank.move();
        } else if (isRight) {
            playerTank.setDirection(Direction.RIGHT);
            playerTank.move();
        }

        // Handle continuous firing with cooldown
        if (isFire && fireCooldown <= 0) {
            playerTank.shoot(); // Fire a bullet
            fireCooldown = 20 - playerTank.fireSpeed;  // Reset cooldown (adjust this value to control firing rate)
        }

        // Update bullets and decrease cooldown
        playerTank.updateBullets();
        if (fireCooldown > 0) {
            fireCooldown--;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
        playerTank.render(g);
        playerTank.renderBullets(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                isUp = true;
                break;
            case KeyEvent.VK_DOWN:
                isDown = true;
                break;
            case KeyEvent.VK_LEFT:
                isLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                isRight = true;
                break;
            case KeyEvent.VK_SPACE:
                isFire = true;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                isUp = false;
                break;
            case KeyEvent.VK_DOWN:
                isDown = false;
                break;
            case KeyEvent.VK_LEFT:
                isLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                isRight = false;
                break;
            case KeyEvent.VK_SPACE:
                isFire = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void run() {
        while (running) {
            updateGame();   // Update game elements
            repaint();      // Redraw the screen

            try {
                Thread.sleep(16); // Pause for 16 ms (~60 FPS)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopGame() {
        running = false;
    }
}
