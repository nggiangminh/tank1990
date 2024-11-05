package jsd.project.tank90.model.tanks;

import jsd.project.tank90.ui.GamePanel;

import javax.swing.*;
import java.awt.*;
public class PlayerTank extends Tank {
    private final Image TANK_UP = new ImageIcon("src/jsd/project/tank90/images/tank_up.png").getImage();
    private final Image TANK_DOWN = new ImageIcon("src/jsd/project/tank90/images/tank_down.png").getImage();
    private final Image TANK_LEFT = new ImageIcon("src/jsd/project/tank90/images/tank_left.png").getImage();
    private final Image TANK_RIGHT = new ImageIcon("src/jsd/project/tank90/images/tank_right.png").getImage();
    private static final Image SHIELD_IMAGE_1 = new ImageIcon("src/jsd/project/tank90/images/shield_1.png").getImage();
    private static final Image SHIELD_IMAGE_2 = new ImageIcon("src/jsd/project/tank90/images/shield_2.png").getImage();


    private int points = 0;
    private int life = 4;
    private int speed = 1;
    private int bulletSize = 7;
    private int bulletSpeed = 2;
    private int fireSpeed = 10;
    private int bulletDamage = 1;

    // Shield properties
    private boolean shielded = false;
    private int shieldTimer = 0;
    private boolean shieldToggle = false; // Used to alternate between the two images
    private int shieldToggleCounter = 0; // Used to control the toggle speed

    public PlayerTank(int x, int y, int size) {
        super(x, y, size, Direction.UP);
        this.tankImage = TANK_UP;
    }

    // Activate shield with a set duration
    public void activateShield(int duration) {
        this.shielded = true;
        this.shieldTimer = duration;
    }

    // Update shield status each frame
    public void updateShield() {
        if (shielded && shieldTimer > 0) {
            shieldTimer--;
            if (shieldTimer == 0) {
                shielded = false; // Disable shield when timer expires
            }
        }

        // Toggle the shield image every few frames (e.g., every 10 frames)
        shieldToggleCounter++;
        if (shieldToggleCounter >= 4) {
            shieldToggle = !shieldToggle; // Switch between images
            shieldToggleCounter = 0;
        }
    }

    public boolean isShielded() {
        return shielded;
    }

    @Override
    public int getBulletSize() {
        return bulletSize;
    }

    public void setBulletSize(int bulletSize) {
        this.bulletSize = bulletSize;
    }

    @Override
    public int getBulletSpeed() {
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void increasePoints(int p) {
        setPoints(getPoints() + p);
    }


    // Override the render method to include the shield effect
    @Override
    public void render(Graphics g) {
        super.render(g); // Render the tank itself

        // Render the shield overlay if active
        if (shielded) {
            int offset = 3; // Adjust for shield positioning around the tank
            Image currentShieldImage = shieldToggle ? SHIELD_IMAGE_1 : SHIELD_IMAGE_2;
            g.drawImage(currentShieldImage, x - offset, y - offset, size + 2 * offset, size + 2 * offset, null);
        }
    }
}
