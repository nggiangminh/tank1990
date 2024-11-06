package jsd.project.tank90.model.tanks;

import javax.swing.*;
import java.awt.*;

import static jsd.project.tank90.utils.SoundManager.playExplosionSound;
import static jsd.project.tank90.utils.SoundManager.playShotSound;


public class PlayerTank extends Tank {

    private static final Image SHIELD_IMAGE_1 = new ImageIcon("src/jsd/project/tank90/resources/images/shield_1.png").getImage();
    private static final Image SHIELD_IMAGE_2 = new ImageIcon("src/jsd/project/tank90/resources/images/shield_2.png").getImage();
    private final Image TANK_UP = new ImageIcon("src/jsd/project/tank90/resources/images/playerTank_up.png").getImage();
    private final Image TANK_DOWN = new ImageIcon("src/jsd/project/tank90/resources/images/playerTank_down.png").getImage();
    private final Image TANK_LEFT = new ImageIcon("src/jsd/project/tank90/resources/images/playerTank_left.png").getImage();
    private final Image TANK_RIGHT = new ImageIcon("src/jsd/project/tank90/resources/images/playerTank_right.png").getImage();
    private final int spawnX;
    private final int spawnY;
    private int lifePlusPoints = 0;
    private int points = 0;

    private int life = 4;
    private int speed = 1;
    private int bulletSize = 5;
    private int bulletSpeed = 2;
    private int maxBullets = 2;
    private int fireSpeed = 45;

    private int bulletDamage = 1;

    private int star = 0;

    private boolean shielded = false;
    private boolean shieldToggle = false; // Used to alternate between the two images
    public PlayerTank(int x, int y, int size) {
        super(x, y, size, Direction.UP);
        this.spawnX = x;
        this.spawnY = y;
        this.tankImage = TANK_UP;
        activateShield();
    }

    // Define unique bullet size and speed for PlayerTank
    @Override
    protected int getBulletSize() {
        return bulletSize;
    }

    public void setBulletSize(int bulletSize) {
        this.bulletSize = bulletSize;
    }

    @Override
    protected int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    @Override
    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Image getTankUpImage() {
        return TANK_UP;
    }

    @Override
    public Image getTankDownImage() {
        return TANK_DOWN;
    }

    @Override
    public Image getTankLeftImage() {
        return TANK_LEFT;
    }

    @Override
    public Image getTankRightImage() {
        return TANK_RIGHT;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getFireSpeed() {
        return fireSpeed;
    }

    public void setFireSpeed(int fireSpeed) {
        this.fireSpeed = fireSpeed;
    }

    public int getMaxBullets() {
        return maxBullets;
    }

    public void setMaxBullets(int maxBullets) {
        this.maxBullets = maxBullets;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLifePlusPoints() {
        return lifePlusPoints;
    }

    public void setLifePlusPoints(int lifePlusPoints) {
        this.lifePlusPoints = lifePlusPoints;
    }

    public void increasePoints(int p) {
        points += p;
        lifePlusPoints += p;
    }

    public void checkBonusLife() {
        if (lifePlusPoints >= 20000) {
            life++;
            lifePlusPoints -= 20000;
        }
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public Bullet shoot() {
        if (super.shoot() != null) playShotSound();
        return super.shoot();
    }

    public void claimStar() {
        setStar(Math.min(3, getStar() + 1));
        switch (getStar()) {
            case 1 -> setBulletSpeed(3);
            case 2 -> setMaxBullets(2);
            case 3 -> setBulletDamage(2);
        }
    }

    // Activate shield
    public void activateShield() {
        new Thread(() -> {
            this.shielded = true;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle thread interruption
            } finally {
                this.shielded = false; // Deactivate shield after the duration
            }

        }).start();
    }


    public boolean isShielded() {
        return shielded;
    }

    @Override
    public void render(Graphics g) {
        super.render(g); // Render the tank
        checkBonusLife();
        // Render shield effect if active
        if (shielded) {
            int offset = 5; // Adjust for positioning
            Image currentShieldImage = toggleShieldImage(); // Get the blinking shield image
            g.drawImage(currentShieldImage, x - offset, y - offset, size + 2 * offset, size + 2 * offset, null);
            shieldToggle = !shieldToggle;
        }
    }

    private Image toggleShieldImage() {
        // Toggle between the two images for blinking effect
        return shieldToggle ? SHIELD_IMAGE_1 : SHIELD_IMAGE_2;
    }

    private void respawn() {
        enable();
        setLife(getLife() - 1);
        this.x = spawnX;
        this.y = spawnY;
        setStar(0);
        activateShield();
    }

    public void takeDamage() {
        if (isShielded()) return;
        new Thread(() -> {
            try {
                disable();
                playExplosionSound();
                Thread.sleep(3000);
                respawn();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Life: " + getLife());
        }).start();
    }

}
