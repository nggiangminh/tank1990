package jsd.project.tank90.ui;

import jsd.project.tank90.MapLoader;
import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.environments.Base;
import jsd.project.tank90.model.environments.BrickWall;
import jsd.project.tank90.model.environments.SteelWall;
import jsd.project.tank90.model.environments.Water;
import jsd.project.tank90.model.tanks.Direction;
import jsd.project.tank90.model.tanks.PlayerTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener, Runnable {
    private final MapLoader mapLoader;
    private final List<String> mapData;
    private final PlayerTank playerTank;
    private List<GameObject> environmentObjects;
    private boolean running = true;

    // Movement and firing control booleans
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isFire = false;

    // Firing cooldown to manage fire rate
    private int fireCooldown = 0;

    public GamePanel() {
        setBackground(Color.BLACK);

        mapLoader = new MapLoader();
        mapLoader.loadMap("src/jsd/project/tank90/map_stage/map.txt");
        mapData = mapLoader.getMapData();

        initializeMapObjects();
        playerTank = new PlayerTank(200, 200, 20, 5);

        setFocusable(true);
        addKeyListener(this);

        // Start the game loop in a new thread
        new Thread(this).start();
    }

    private void initializeMapObjects() {
        environmentObjects = new ArrayList<>();
        int tileSize = 20;

        for (int y = 0; y < mapData.size(); y++) {
            String line = mapData.get(y);
            for (int x = 0; x < line.length(); x++) {
                char tile = line.charAt(x);
                switch (tile) {
                    case '1':
                        environmentObjects.add(new BrickWall(x * tileSize, y * tileSize, tileSize));
                        break;
                    case '2':
                        environmentObjects.add(new Water(x * tileSize, y * tileSize, tileSize));
                        break;
                    case '5':
                        environmentObjects.add(new SteelWall(x * tileSize, y * tileSize, tileSize));
                        break;
                    case '9':
                        environmentObjects.add(new Base(x * tileSize, y * tileSize, tileSize * 2));
                        break;
                }
            }
        }
    }

    public void updateGame() {
        if (isUp) {
            playerTank.setDirection(Direction.UP);
            playerTank.move();
            if (checkCollisionWithEnvironment(playerTank)) {
                playerTank.undoMove(); // Undo movement if collision is detected
            }
        } else if (isDown) {
            playerTank.setDirection(Direction.DOWN);
            playerTank.move();
            if (checkCollisionWithEnvironment(playerTank)) {
                playerTank.undoMove();
            }
        } else if (isLeft) {
            playerTank.setDirection(Direction.LEFT);
            playerTank.move();
            if (checkCollisionWithEnvironment(playerTank)) {
                playerTank.undoMove();
            }
        } else if (isRight) {
            playerTank.setDirection(Direction.RIGHT);
            playerTank.move();
            if (checkCollisionWithEnvironment(playerTank)) {
                playerTank.undoMove();
            }
        }

        // Handle continuous firing with cooldown
        if (isFire && fireCooldown <= 0) {
            playerTank.shoot();
            fireCooldown = Math.max(5, 20 - playerTank.fireSpeed);
        }

        playerTank.updateBullets();
        if (fireCooldown > 0) {
            fireCooldown--;
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Render map objects from cached environmentObjects
        for (GameObject obj : environmentObjects) {
            obj.render(g);
        }

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


    private boolean checkCollisionWithEnvironment(PlayerTank tank) {
        Rectangle tankBounds = tank.getBounds();

        for (GameObject environmentObj : environmentObjects) {
            if (environmentObj instanceof BrickWall || environmentObj instanceof SteelWall) {
                if (tankBounds.intersects(environmentObj.getBounds())) {
                    return true; // Collision detected
                }
            }
        }
        return false; // No collision
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
