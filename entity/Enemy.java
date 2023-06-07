package entity;

import game.*;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;

import javax.swing.*;

public class Enemy extends JLabel {

    protected int hp;
    private boolean isDefeated = false;
    int x, y;
    int width, height;
    int speed;
    String name;
    String direction;
    GameFrame gameFrame;
    boolean movingForward = true;
    int distanceMoved = 0;
    ImageIcon imageicon;
    final static String TAG = "Enemy : ";
    Random random = new Random();
    Timer timer = new Timer();
    public int spriteNum = 1;

    // HP related fields
    public int maxHpEnemy = 100;
    public int currentHpEnemy = maxHpEnemy;
    public int playerAttackHp = 1;     // The health decrease when the player attacks
    public int hpBarWidthEnemy = 20;
    public int hpBarHeightEnemy;
    private GamePanel gamePanel;

    public void takeDamage(int damage) {
        currentHpEnemy -= damage;
        if (currentHpEnemy < 0) {
            currentHpEnemy = 0;
        }
    }

    public void setHpBarDefaultValues() {
        maxHpEnemy = 100;
        hpBarWidthEnemy = 150;
        hpBarHeightEnemy = 20;
    }

    protected void returnToBeginningPanel() {
        if (gameFrame != null) {
            gameFrame.returnToBeginningPanel();
        }
    }
    public void decreaseHp(int amount, Player player) {

        int distance = Math.abs(this.x - player.getX());

        if (distance <= 16) {
            System.out.println("현재 hp" + hp);
            this.hp -= amount;
            if (this.hp <= 0 && !isDefeated) {
                isDefeated = true;
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "적이 쓰러졌습니다!");
                    gamePanel.returnToBeginningPanel();
                });
            }
        }
    }



    public void update() {
        // Prevent enemy from moving outside the screen
        if (x < 0) {
            x = 0;
        } else if (x > 1152 - width) {
            x = 1152- width;
        }

        if (y < 0) {
            y = 0;
        } else if (y > 864 - height) {
            y = 864 - height;
        }
    }

    public Enemy() {
        setHpBarDefaultValues();
    }

    public Enemy(String string, int x, int y) {
        this.x = x;
        this.y = y;
        this.name = string;
    }

    public void moveChange() {
        // Implement change in movement
    }

    public void moveDirection() {
        // Implement direction of movement
    }

}
