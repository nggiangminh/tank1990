package jsd.project.tank90.model.tanks;

import jsd.project.tank90.model.Images;

import java.awt.*;

import static jsd.project.tank90.utils.SoundManager.playExplosionSound;
import static jsd.project.tank90.utils.SoundManager.playShotSound;


public class PlayerTank extends Tank {

    private static final Image SHIELD_IMAGE_1 = Images.SHIELD_1;
    private static final Image SHIELD_IMAGE_2 = Images.SHIELD_2;
    private final Image TANK_UP_1 = Images.PLAYER_UP_1;
    private final Image TANK_DOWN_1 = Images.PLAYER_DOWN_1;
    private final Image TANK_LEFT_1 = Images.PLAYER_LEFT_1;
    private final Image TANK_RIGHT_1 = Images.PLAYER_RIGHT_1;
    private final Image TANK_UP_2 = Images.PLAYER_UP_2;
    private final Image TANK_DOWN_2 = Images.PLAYER_DOWN_2;
    private final Image TANK_LEFT_2 = Images.PLAYER_LEFT_2;
    private final Image TANK_RIGHT_2 = Images.PLAYER_RIGHT_2;
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
        this.tankImage = TANK_UP_1;
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
    public Image getTankUpImage1() {
        return TANK_UP_1;
    }

    @Override
    public Image getTankDownImage1() {
        return TANK_DOWN_1;
    }

    @Override
    public Image getTankLeftImage1() {
        return TANK_LEFT_1;
    }

    @Override
    public Image getTankRightImage1() {
        return TANK_RIGHT_1;
    }

    @Override
    protected Image getTankUpImage2() {
        return TANK_UP_2;
    }

    @Override
    protected Image getTankDownImage2() {
        return TANK_DOWN_2;
    }

    @Override
    protected Image getTankLeftImage2() {
        return TANK_LEFT_2;
    }

    @Override
    protected Image getTankRightImage2() {
        return TANK_RIGHT_2;
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
