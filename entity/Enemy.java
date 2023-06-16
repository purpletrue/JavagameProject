

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
    private long lastDecreaseTime = 0;

    public void setHpBarDefaultValues() {
        maxHpEnemy = 100;
        hpBarWidthEnemy = 100;
        hpBarHeightEnemy = 10;
    }

    public void decreaseEnemyHp(int amount, Player player) {
        if (isDefeated) {
            return;
        }
        long currentTime = System.currentTimeMillis();

        // 체력 감소 딜레이 줌 0.1sec
        if (currentTime - lastDecreaseTime < 100) {
            return;
        }

        int distance = Math.abs(this.x - player.getX());

        if (distance <= 30) {
            System.out.println("Enemy HP " + hp);
            this.hp -= amount;
            lastDecreaseTime = currentTime; // 체력 감소 시간 업데이트

            if (this.hp <= 0) {
                isDefeated = true;
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "다음 단계로 넘어감");
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

}