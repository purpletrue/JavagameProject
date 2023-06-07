

// EnemyAkaza, EnemyKoku, EnemyMuzan 의 부모클래스로, 적의 공통적인 특성을 정의하는 클래스입니다.


package entity;

import game.*;
import javax.swing.*;

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

    public void setHpBarDefaultValues() {
        maxHpEnemy = 100;
        hpBarWidthEnemy = 150;
        hpBarHeightEnemy = 20;
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

    public Enemy() {
        setHpBarDefaultValues();
    }
    public Enemy(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setHpBarDefaultValues();
    }

}