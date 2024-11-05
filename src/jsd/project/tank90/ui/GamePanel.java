package jsd.project.tank90.ui;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.environments.*;
import jsd.project.tank90.model.powerups.*;
import jsd.project.tank90.model.tanks.*;
import jsd.project.tank90.utils.CollisionHandling;
import jsd.project.tank90.utils.MapLoader;

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
    private final int[] playerSpawnPos = new int[]{200, 500};
    private final List<EnemyTank> enemyTanks = new ArrayList<>(); // List to hold multiple EnemyTank enemies
    private final List<PowerUp> powerUps = new ArrayList<>();
    private final List<GameObject> originalWalls = new ArrayList<>(); // To store the original brick walls
    private final int tileSize = 20;
    private final List<Explosion> explosions = new ArrayList<>();
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


    public GamePanel() {
        setBackground(Color.BLACK);

        MapLoader mapLoader = new MapLoader();
        mapLoader.loadMap("src/jsd/project/tank90/map_stage/map.txt");
        mapData = mapLoader.getMapData();

        initializeMapObjects();
        playerTank = new PlayerTank(playerSpawnPos[0], playerSpawnPos[1], tileSize * 2);
        // Spawn multiple BasicTanks at different locations
        spawnEnemyTanks();

        setFocusable(true);
        addKeyListener(this);

        // Example power-up spawning
        powerUps.add(new TankPowerUp(200, 280, 30)); // Position and size for Tank power-up
        powerUps.add(new TimerPowerUp(200, 250, 30)); // Position and size for Timer power-up
        powerUps.add(new ShovelPowerUp(100, 150, 30)); // Position and size for Shovel power-up
        powerUps.add(new StarPowerUp(300, 150, 30)); // Position and size for Star power-up
        powerUps.add(new StarPowerUp(450, 250, 30)); // Position and size for Star power-up
        powerUps.add(new StarPowerUp(100, 450, 30)); // Position and size for Star power-up
        powerUps.add(new GrenadePowerUp(220, 450, 30)); // Position and size for Star power-up
        powerUps.add(new ShieldPowerUp(150, 150, 30)); // Position and size for Star power-up

        // Start the game loop in a new thread
        new Thread(this).start();
    }

    public void activateFreeze() {
        freezeTimer = 300; // 300 frame
    }


    private void initializeMapObjects() {
        environmentObjects = new ArrayList<>();

        for (int y = 0; y < mapData.size(); y++) {
            String line = mapData.get(y);
            for (int x = 0; x < line.length(); x++) {
                char tile = line.charAt(x);
                switch (tile) {
                    case '1' -> environmentObjects.add(new BrickWall(x * tileSize, y * tileSize, tileSize));
                    case '2' -> environmentObjects.add(new Water(x * tileSize, y * tileSize, tileSize));
                    case '5' -> environmentObjects.add(new SteelWall(x * tileSize, y * tileSize, tileSize));
                    case '6' -> environmentObjects.add(new SteelWall(x * tileSize, y * tileSize, tileSize, false));
                    case '4' -> environmentObjects.add(new Tree(x * tileSize, y * tileSize, tileSize));
                    case '9' -> environmentObjects.add(new Base(x * tileSize, y * tileSize, tileSize * 2));
                }
            }
        }
    }

    // Method to spawn multiple BasicTanks
    private void spawnEnemyTanks() {
        enemyTanks.add(new BasicTank(50, 20, tileSize * 2, Direction.UP));
        enemyTanks.add(new ArmorTank(300, 20, tileSize * 2, Direction.RIGHT));
        enemyTanks.add(new FastTank(400, 20, tileSize * 2, Direction.LEFT));
        enemyTanks.add(new PowerTank(150, 20, tileSize * 2, Direction.DOWN));
    }

    public void updateGame() {
        for (Explosion explosion : explosions)
            explosion.update();
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
            if (enemy.isDead() && !enemy.isShowPoints()) {
                enemy.markAsDead();
                explosions.add(new Explosion(enemy.getCenterX(), enemy.getCenterY(), enemy.getSize()));
            } else if (enemy.shouldRemove()) {
                playerTank.increasePoints(enemy.getPoints());
                enemyIterator.remove(); // Safely remove the dead enemy
            } else if (!enemy.isDead()){
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
                CollisionHandling.checkBulletEnvironmentCollision(enemy, environmentObjects, explosions);
                CollisionHandling.checkBulletEnemyTankCollision(playerTank.getBullets(), enemy, explosions);
                CollisionHandling.checkBulletPlayerTankCollision(enemy.getBullets(), playerTank, explosions);
                CollisionHandling.checkBulletEnemyBulletCollision(playerTank.getBullets(), enemy.getBullets(), explosions);
                CollisionHandling.checkPlayerEnemyCollision(playerTank, enemyTanks, explosions);
                if (CollisionHandling.checkBulletBaseCollision(enemy, environmentObjects, explosions)) stopGame();
            }
        }

        // Handle continuous firing with cooldown
        if (isFire && fireCooldown <= 0 && playerTank.getBullets().size() < playerTank.getMaxBullets()) {
            playerTank.shoot();
            fireCooldown = Math.max(5, 50 - playerTank.getFireSpeed());
        }

        playerTank.updateBullets();
        CollisionHandling.checkBulletEnvironmentCollision(playerTank, environmentObjects, explosions);
        if (fireCooldown > 0) {
            fireCooldown--;
        }
        if (CollisionHandling.checkBulletBaseCollision(playerTank, environmentObjects, explosions)) stopGame();
        if (playerTank.getLife() == 0) stopGame();

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
        Iterator<Explosion> explosionIterator = explosions.iterator();
        while (explosionIterator.hasNext()) {
            Explosion explosion = explosionIterator.next();
            explosion.render(g);
            if (explosion.isFinished()) explosionIterator.remove();
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
        List<Point> fortressTiles = getFortressAreaCoordinates();

        // Create a list to hold the replaced walls for later restoration
        List<GameObject> replacedWalls = new ArrayList<>();

        // Replace brick walls with steel walls in a separate thread
        new Thread(() -> {
            // Replace brick walls with steel walls
            for (Point tile : fortressTiles) {
                int x = tile.x;
                int y = tile.y;
                boolean brickFound = false;

                // Find the wall object at this location
                for (int i = 0; i < environmentObjects.size(); i++) {
                    GameObject obj = environmentObjects.get(i);

                    // Check if the object is a BrickWall at the target location
                    if (obj instanceof BrickWall && obj.getX() == x && obj.getY() == y) {
                        // Store the original BrickWall for later restoration
                        replacedWalls.add(obj);

                        // Replace with a SteelWall at the same position
                        environmentObjects.set(i, new SteelWall(x, y, obj.getSize()));
                        brickFound = true;
                        break;
                    }
                }

                // If no BrickWall was found, create a new SteelWall
                if (!brickFound) {
                    SteelWall newWall = new SteelWall(x, y, 20); // Assuming tile size is 20
                    environmentObjects.add(newWall);
                    replacedWalls.add(newWall); // Track newly added wall for removal
                }
            }

            // Wait for the effect duration
            try {
                Thread.sleep(5000); // Wait for 5 seconds (or desired duration in milliseconds)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Revert steel walls back to their original state
            for (GameObject wall : replacedWalls) {
                if (wall instanceof BrickWall) {
                    // Restore original brick walls
                    for (int i = 0; i < environmentObjects.size(); i++) {
                        GameObject obj = environmentObjects.get(i);
                        if (obj instanceof SteelWall && obj.getX() == wall.getX() && obj.getY() == wall.getY()) {
                            environmentObjects.set(i, wall);
                            break;
                        }
                    }
                } else if (wall instanceof SteelWall) {
                    // Remove newly added steel walls
                    environmentObjects.remove(wall);
                }
            }
        }).start();
    }

    public List<Point> getFortressAreaCoordinates() {
        List<Point> fortressCoordinates = new ArrayList<>();

        int[][] fortressArea = {{12, 24}, {12, 25}, {12, 26}, {13, 24}, {14, 24}, {15, 24}, {15, 25}, {15, 26}};

        // Convert each coordinate to a Point and scale by tile size
        int tileSize = 20; // Assuming each tile is 20x20 pixels
        for (int[] coordinate : fortressArea) {
            int x = coordinate[0] * tileSize; // Column index (x-coordinate)
            int y = coordinate[1] * tileSize; // Row index (y-coordinate)
            fortressCoordinates.add(new Point(x, y));
        }

        return fortressCoordinates;
    }

    public void killAllEnemies() {
        Iterator<EnemyTank> enemyTankIterator = enemyTanks.iterator();
        while (enemyTankIterator.hasNext()) {
            EnemyTank enemy = enemyTankIterator.next();
            playerTank.increasePoints(enemy.getPoints());
            enemy.kill();
        }
    }
}
