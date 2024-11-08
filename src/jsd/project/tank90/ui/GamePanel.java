package jsd.project.tank90.ui;

import jsd.project.tank90.model.GameObject;
import jsd.project.tank90.model.environments.*;
import jsd.project.tank90.model.powerups.GrenadePowerUp;
import jsd.project.tank90.model.powerups.PowerUp;
import jsd.project.tank90.model.tanks.*;
import jsd.project.tank90.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GamePanel extends JPanel implements KeyListener, Runnable {
    private final int FPS = 60;
    private final int tileSize = 20;
    private final int tankSize = tileSize * 3 / 2;
    private final List<String> mapData;
    private final PlayerTank playerTank;
    private final int MAX_SLIDE_MOMENTUM = 30; // Maximum frames for sliding
    private final int[] playerSpawnPos = new int[]{200, 500};
    private final List<EnemyTank> enemyTanks = new ArrayList<>(); // List to hold multiple EnemyTank enemies
    private final List<EnemyTank> killedEnemies = new ArrayList<>(); // List to hold multiple EnemyTank enemies
    private final EnemySpawner enemySpawner = new EnemySpawner(enemyTanks, 4);
    private final List<PowerUp> powerUps = new ArrayList<>();
    private final PowerUpSpawner powerUpSpawner = new PowerUpSpawner(powerUps);
    private final List<Explosion> explosions = new ArrayList<>();
    private final SoundManager soundManager;
    private final int mapLevel;
    private final PauseOverlay pauseOverlay;
    public int freezeTimer = 0;
    private Direction previousDirection = null; // Track the previous direction
    private int slideMomentum = 0; // Number of frames to continue sliding
    private List<GameObject> environmentObjects;
    private boolean running = true;
    private boolean winning = false;
    // Movement and firing control booleans
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isFire = false;
    // Firing cooldown to manage fire rate
    private int fireCooldown = 0;
    private boolean isPaused = false;
    private boolean pPressed = false;


    public GamePanel(int mapLevel) {
        System.out.println("Level" + mapLevel);
        setBackground(Color.BLACK);
        this.mapLevel = mapLevel;
        addKeyListener(this);
        setFocusable(true);  // Ensure GamePanel can gain focus
        requestFocusInWindow();  // Request focus immediately upon creation

        MapLoader mapLoader = new MapLoader();
        mapLoader.loadMap(mapLevel);  // Load the specific map file
        mapData = mapLoader.getMapData();

        initializeMapObjects();
        this.playerTank = new PlayerTank(playerSpawnPos[0], playerSpawnPos[1], tankSize);

        soundManager = new SoundManager();
        soundManager.playBackgroundMusic("src/jsd/project/tank90/resources/sounds/soundtrack.wav"); // Đường dẫn đến tệp âm thanh
        soundManager.setVolume(-35.0f);

        powerUps.add(new GrenadePowerUp(220, 450, 30));

        // Initialize and add the pause overlay
        pauseOverlay = new PauseOverlay();
        pauseOverlay.setBounds(0, 0, getWidth(), getHeight());
        add(pauseOverlay);


        setFocusable(true);
        addKeyListener(this);
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
                    case '3' -> environmentObjects.add(new Ice(x * tileSize, y * tileSize, tileSize));
                    case '5' -> environmentObjects.add(new SteelWall(x * tileSize, y * tileSize, tileSize));
                    case '6' -> environmentObjects.add(new SteelWall(x * tileSize, y * tileSize, tileSize, false));
                    case '4' -> environmentObjects.add(new Tree(x * tileSize, y * tileSize, tileSize));
                    case '9' -> environmentObjects.add(new Base(x * tileSize, y * tileSize, tileSize * 2));
                }
            }
        }
    }

    public void updateGame() {

        if (isPaused) {
            return;
        }


        enemySpawner.spawnEnemy();
        for (Explosion explosion : explosions) {
            explosion.update();
        }

        if (freezeTimer > 0) {
            freezeTimer--;
        }
        // Player movement
        boolean onIce = CollisionHandling.checkPlayerOnIce(playerTank, environmentObjects);

        // Check if movement keys are pressed
        if (isUp || isDown || isLeft || isRight) {
            // Determine the current intended direction
            Direction currentDirection = null;
            if (isUp) currentDirection = Direction.UP;
            else if (isDown) currentDirection = Direction.DOWN;
            else if (isLeft) currentDirection = Direction.LEFT;
            else if (isRight) currentDirection = Direction.RIGHT;

            // If direction has changed or it's the first movement, reset sliding momentum
            if (currentDirection != previousDirection) {
                slideMomentum = 0;
                previousDirection = currentDirection;
            }

            // Move in the current direction
            playerTank.setDirection(currentDirection);
            playerTank.move();
            if (CollisionHandling.checkTankEnvironmentCollision(playerTank, environmentObjects)) {
                playerTank.undoMove(); // Undo movement if collision is detected
            }

            // Update sliding momentum if on ice
            if (onIce) {
                slideMomentum = MAX_SLIDE_MOMENTUM;
            }
        } else if (slideMomentum > 0 && onIce) {
            // Apply sliding when no keys are pressed and there's momentum left
            playerTank.move();
            if (CollisionHandling.checkTankEnvironmentCollision(playerTank, environmentObjects)) {
                playerTank.undoMove(); // Stop sliding if collision occurs
                slideMomentum = 0;
            }
            slideMomentum--;
        } else {
            // No movement keys pressed and no slide momentum left
            previousDirection = null;
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
                killedEnemies.add(enemy);
                if (enemy.isFlashing()) powerUpSpawner.spawnPowerUp();

            } else if (!enemy.isDead()) {
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
                for (Bullet bullet : enemy.getBullets()) {
                    bullet.setOnIce(CollisionHandling.checkBulletOnIce(bullet, environmentObjects));
                }

            }
        }
        powerUps.removeIf(powerUp -> !powerUp.isActive());
        // Handle continuous firing with cooldown
        if (isFire && fireCooldown <= 0 && playerTank.getBullets().size() < playerTank.getMaxBullets()) {
            playerTank.shoot();
            fireCooldown = Math.max(5, 50 - playerTank.getFireSpeed());
        }

        playerTank.updateBullets();
        for (Bullet bullet : playerTank.getBullets()) {
            bullet.setOnIce(CollisionHandling.checkBulletOnIce(bullet, environmentObjects));
        }
        CollisionHandling.checkBulletEnvironmentCollision(playerTank, environmentObjects, explosions);
        if (fireCooldown > 0) {
            fireCooldown--;
        }

        checkStopGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        // Render environment objects except Tree
        for (GameObject environmentObj : new ArrayList<>(environmentObjects)) {
            if (!(environmentObj instanceof Tree)) {
                environmentObj.render(g);
            }
        }


        // Render player tank and bullets
        playerTank.render(g);
        playerTank.renderBullets(g);
        enemySpawner.renderSpawnEffects(g);
        for (EnemyTank enemy : enemyTanks) {
            enemy.render(g);
            enemy.renderBullets(g);
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

        // Render Tree
        for (GameObject environmentObj : new ArrayList<>(environmentObjects)) {
            if ((environmentObj instanceof Tree)) {
                environmentObj.render(g);
            }
        }
        pauseOverlay.setBounds(0, 0, getWidth(), getHeight());
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
            case KeyEvent.VK_P -> {
                if (!pPressed) {  // Only trigger if "P" wasn't already pressed
                    isPaused = !isPaused;
                    pauseOverlay.togglePause(isPaused);// Toggle pause state
                    System.out.println("OK");  // Print "OK"
                    pPressed = true;  // Set the flag to prevent repeat actions
                }
            }

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
            case KeyEvent.VK_P -> pPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void run() {
        while (running) {
            if (!isPaused) {
                updateGame();   // Update game elements
                repaint();      // Redraw the screen
            }
            try {
                Thread.sleep(1000 / FPS); // Pause to maintain 60 FPS
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopGame() {
        stopMusic();
        running = false;
        JFrame gameFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        gameFrame.getContentPane().removeAll();
        if (winning) gameFrame.getContentPane().add(new WinningPanel(mapLevel, killedEnemies, playerTank));
        else gameFrame.getContentPane().add(new GameOverPanel(mapLevel, killedEnemies, playerTank));
        gameFrame.revalidate();
        gameFrame.repaint();
    }

    public void stopMusic() {
        if (soundManager != null) {
            soundManager.stopBackgroundMusic(); // Ensure the background music stops
        }
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
            explosions.add(new Explosion(enemy.getCenterX(), enemy.getCenterY(), enemy.getSize()));
            enemy.markAsDead();
        }
    }

    public PlayerTank getPlayerTank() {
        return playerTank;
    }

    private void checkStopGame() {
        for (EnemyTank enemy : enemyTanks)
            if (CollisionHandling.checkBulletBaseCollision(enemy, environmentObjects, explosions)) stopGame();
        if (CollisionHandling.checkBulletBaseCollision(playerTank, environmentObjects, explosions)) {
            winning = true;
            stopGame();
        }
        if (playerTank.getLife() == 0) stopGame();

        if (enemyTanks.size() == 0 && enemySpawner.getEnemyLeft() == 0) {
            winning = true;
            stopGame();
        }
    }


}
