package jsd.project.tank90.utils;

import jsd.project.tank90.model.powerups.*;
import java.util.List;
import java.util.Random;

public class PowerUpSpawner {
    private final List<PowerUp> powerUps; // List to hold active power-ups
    private final int gameWidth = 540; // Width of the game area
    private final int gameHeight = 540; // Height of the game area
    private final Random random = new Random(); // Random generator for positions and power-up types

    public PowerUpSpawner(List<PowerUp> powerUps) {
        this.powerUps = powerUps;
    }

    // Method to spawn a random power-up at a random position within game boundaries
    public void spawnPowerUp() {
        int x = 20 + random.nextInt(gameWidth - 50); // Adjust to ensure power-up fits within the width
        int y = 20 + random.nextInt(gameHeight - 50); // Adjust to ensure power-up fits within the height

        PowerUp newPowerUp;
        switch (random.nextInt(5)) { // Assuming 5 types of power-ups
            case 0 -> newPowerUp = new TankPowerUp(x, y, 30);
            case 1 -> newPowerUp = new TimerPowerUp(x, y, 30);
            case 2 -> newPowerUp = new ShovelPowerUp(x, y, 30);
            case 3 -> newPowerUp = new StarPowerUp(x, y, 30);
            case 4 -> newPowerUp = new GrenadePowerUp(x, y, 30);
            default -> throw new IllegalStateException("Unexpected power-up type");
        }

        powerUps.add(newPowerUp); // Add the new power-up to the active list
    }
}
