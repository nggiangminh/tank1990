package jsd.project.tank90.ui;

import jsd.project.tank90.model.powerups.ShovelPowerUp;
import jsd.project.tank90.utils.MapLoader;
import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.environments.*;
import jsd.project.tank90.model.powerups.PowerUp;
import jsd.project.tank90.model.powerups.TankPowerUp;
import jsd.project.tank90.model.powerups.TimerPowerUp;
import jsd.project.tank90.model.tanks.*;
import jsd.project.tank90.utils.CollisionHandling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GamePanel extends JPanel implements KeyListener, Runnable {
    private final int FPS = 60;
    private final List<String> mapData;
    private final PlayerTank playerTank;
    private final List<EnemyTank> enemyTanks = new ArrayList<>(); // List to hold multiple EnemyTank enemies
    private final List<PowerUp> powerUps = new ArrayList<>();
    public int freezeTimer = 0;
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



    private int shovelEffectTimer; // Timer for the shovel effect duration
    private List<GameObject> originalWalls = new ArrayList<>(); // To store the original brick walls

    public GamePanel() {
        setBackground(Color.BLACK);

        MapLoader mapLoader = new MapLoader();
        mapLoader.loadMap("src/jsd/project/tank90/map_stage/map.txt");
        mapData = mapLoader.getMapData();

        initializeMapObjects();
        playerTank = new PlayerTank(100, 100, 35);

        // Spawn multiple BasicTanks at different locations
        spawnEnemyTanks();

        setFocusable(true);
        addKeyListener(this);

        // Example power-up spawning
        powerUps.add(new TankPowerUp(200, 280, 30)); // Position and size for Tank power-up
        powerUps.add(new TimerPowerUp(200, 250, 30)); // Position and size for Timer power-up
        powerUps.add(new ShovelPowerUp(100, 150, 30)); // Position and size for Timer power-up

        // Start the game loop in a new thread
        new Thread(this).start();
    }

    public void activateFreeze() {
        freezeTimer = 300; // 300 frame
    }


    private void initializeMapObjects() {
        environmentObjects = new ArrayList<>();
        int tileSize = 20;

        for (int y = 0; y < mapData.size(); y++) {
            String line = mapData.get(y);
            for (int x = 0; x < line.length(); x++) {
                char tile = line.charAt(x);
                switch (tile) {
                    case '1' -> environmentObjects.add(new BrickWall(x * tileSize, y * tileSize, tileSize));
                    case '2' -> environmentObjects.add(new Water(x * tileSize, y * tileSize, tileSize));
                    case '5' -> environmentObjects.add(new SteelWall(x * tileSize, y * tileSize, tileSize));
                    case '4' -> environmentObjects.add(new Tree(x * tileSize, y * tileSize, tileSize));
                    case '9' -> environmentObjects.add(new Base(x * tileSize, y * tileSize, tileSize * 2));
                }
            }
        }
    }

    // Method to spawn multiple BasicTanks
    private void spawnEnemyTanks() {
        enemyTanks.add(new BasicTank(50, 20, 20, Direction.UP));
        enemyTanks.add(new ArmorTank(300, 20, 20, Direction.RIGHT));
        enemyTanks.add(new FastTank(400, 20, 20, Direction.LEFT));
        enemyTanks.add(new PowerTank(150, 20, 20, Direction.DOWN));
    }

    public void updateGame() {
        if (freezeTimer > 0) {
            freezeTimer--;
        }
        // Player movement
        if (isUp) {
            playerTank.setDirection(Direction.UP);
            playerTank.move();
            if (CollisionHandling.checkTankEnvironmentCollision(playerTank, environmentObjects)) {
                playerTank.undoMove(); // Undo movement if collision is detected
            }
        } else if (isDown) {
            playerTank.setDirection(Direction.DOWN);
            playerTank.move();
            if (CollisionHandling.checkTankEnvironmentCollision(playerTank, environmentObjects)) {
                playerTank.undoMove();
            }
        } else if (isLeft) {
            playerTank.setDirection(Direction.LEFT);
            playerTank.move();
            if (CollisionHandling.checkTankEnvironmentCollision(playerTank, environmentObjects)) {
                playerTank.undoMove();
            }
        } else if (isRight) {
            playerTank.setDirection(Direction.RIGHT);
            playerTank.move();
            if (CollisionHandling.checkTankEnvironmentCollision(playerTank, environmentObjects)) {
                playerTank.undoMove();
            }
        }
        CollisionHandling.checkClaimPowerup(playerTank, powerUps, this);
        // Update each enemy tank's movement and check collisions
        Iterator<EnemyTank> enemyIterator = enemyTanks.iterator();
        while (enemyIterator.hasNext()) {
            EnemyTank enemy = enemyIterator.next();
            if (freezeTimer == 0) {
                enemy.move();
                enemy.changeDirection();

                if (CollisionHandling.checkTankEnvironmentCollision(enemy, environmentObjects)) {
                    enemy.undoMove();
                    enemy.turn();
                }

                // Check for bullet collisions with the player tank
                enemy.shoot();
            }
            enemy.updateBullets();
            CollisionHandling.checkBulletEnvironmentCollision(enemy, environmentObjects);
            CollisionHandling.checkBulletTankCollision(enemy.getBullets(), playerTank);

            // Remove dead enemy tanks
            if (enemy.isDead()) {
                enemyIterator.remove(); // Safely remove the dead enemy
            }
        }

        // Handle continuous firing with cooldown
        if (isFire && fireCooldown <= 0) {
            playerTank.shoot();
            fireCooldown = Math.max(5, 50 - playerTank.getFireSpeed());
        }

        playerTank.updateBullets();
        CollisionHandling.checkBulletEnvironmentCollision(playerTank, environmentObjects);
        for (EnemyTank enemy : enemyTanks) {
            CollisionHandling.checkBulletTankCollision(playerTank.getBullets(), enemy);
        }

        if (fireCooldown > 0) {
            fireCooldown--;
        }

        // thử active lại
        activateShovelEffect();
        // Shovel effect duration handling
        if (shovelEffectTimer > 0) {
            shovelEffectTimer--;

            if (shovelEffectTimer == 0) {
                // Revert steel walls back to original brick walls after effect ends
                for (GameObject originalBrick : originalWalls) {
                    for (int i = 0; i < environmentObjects.size(); i++) {
                        GameObject obj = environmentObjects.get(i);
                        if (obj instanceof SteelWall && isNearFortress(obj)) {
                            environmentObjects.set(i, originalBrick);
                        }
                    }
                }
                originalWalls.clear(); // Clear stored walls after reverting
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Render environment objects except Tree
        for (GameObject obj : environmentObjects) {
            if (!(obj instanceof Tree)) {
                obj.render(g);
            }
        }

        // Render player tank and bullets
        playerTank.render(g);
        playerTank.renderBullets(g);

        // Render each enemy tank and its bullets
        for (EnemyTank enemy : enemyTanks) {
            enemy.render(g);
            enemy.renderBullets(g);
        }

        // Render Tree objects last to make them appear on top of the tank
        for (GameObject obj : environmentObjects) {
            if (obj instanceof Tree) {
                obj.render(g);
            }
        }

        // Render all power-ups
        for (PowerUp powerUp : powerUps) {
            powerUp.render(g);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP -> isUp = true;
            case KeyEvent.VK_DOWN -> isDown = true;
            case KeyEvent.VK_LEFT -> isLeft = true;
            case KeyEvent.VK_RIGHT -> isRight = true;
            case KeyEvent.VK_SPACE -> isFire = true;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP -> isUp = false;
            case KeyEvent.VK_DOWN -> isDown = false;
            case KeyEvent.VK_LEFT -> isLeft = false;
            case KeyEvent.VK_RIGHT -> isRight = false;
            case KeyEvent.VK_SPACE -> isFire = false;
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
                Thread.sleep(1000 / FPS); // Pause to maintain 60 FPS
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopGame() {
        running = false;
    }


    public void activateShovelEffect() {
        shovelEffectTimer = 300; // Duration in frames (adjust as needed)

        // Replace brick walls around the fortress with steel walls
        for (int i = 0; i < environmentObjects.size(); i++) {
            GameObject obj = environmentObjects.get(i);
            if (obj instanceof BrickWall && isNearFortress(obj)) {
                // Store the original brick wall for later restoration
                originalWalls.add(obj);
                // Replace with steel wall
                environmentObjects.set(i, new SteelWall(obj.getX(), obj.getY(), obj.getSize()));
            }
        }
    }

    private boolean isNearFortress(GameObject wall) {
        // Coordinates around the base `9` in the provided map
        int[][] fortressArea = {
                 {12, 24}, {12, 25},{12, 26},
                 {13, 24},
                 {14, 24},
                 {15, 24}, {15, 25},{15, 26}
        };


        for (int[] coordinate : fortressArea) {
            int fortressX = coordinate[0] * 20; // Assuming each tile is 20 pixels wide
            int fortressY = coordinate[1] * 20; // Assuming each tile is 20 pixels tall
            if (wall.getX() == fortressX && wall.getY() == fortressY) {
                return true;
            }
        }
        return false;
    }

}
