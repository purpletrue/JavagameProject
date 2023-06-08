

// EnemyAkaza, EnemyKoku, EnemyMuzan 의 부모클래스로, 적의 공통적인 특성을 정의하는 클래스입니다.


package entity;

import game.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class Enemy extends JLabel {

    protected int hp;
    private boolean isDefeated = false;
    int x, y;
    int width, height;
    int speed;
    String name;
    GameFrame gameFrame;
    ImageIcon imageicon;
    // HP related fields
    public int maxHpEnemy = 100;
    public int currentHpEnemy = maxHpEnemy;
    public int hpBarWidthEnemy = 20;
    public int hpBarHeightEnemy;
    private GamePanel gamePanel;
    private Player player;

    public void setHpBarDefaultValues() {
        maxHpEnemy = 100;
        hpBarWidthEnemy = 150;
        hpBarHeightEnemy = 20;
    }

    public void decreaseEnemyHp(int amount, Player player) {
        int distance = Math.abs(this.x - player.getX());

        if (distance <= 16) {
            System.out.println("Enemy HP " + hp);
            this.hp -= amount;
            if (this.hp <= 0 && !isDefeated) {
                isDefeated = true;
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Enemy is defeated!");
                    gamePanel.returnToBeginningPanel();
                });
            }
        }
    }


    public Enemy() {
        setHpBarDefaultValues();
    }
    public Enemy(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setHpBarDefaultValues();
    }
    public Enemy(Player player, GamePanel gamePanel) {
        this.player = player;
        this.gamePanel = gamePanel;
        setHpBarDefaultValues();
    }

    public void update() {
        int distance = Math.abs(this.x - player.getX());

        if (distance <= 16) {
            player.decreasePlayerHp(1);
        }
    }

}