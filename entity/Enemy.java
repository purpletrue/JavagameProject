package entity;

import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel {

    Enemy enemy = this;
    ImageIcon enemyMove;
    final static String TAG = "Enemy : ";
    //
    int x;
    int y;
    int speed;
    int moveState;
    int hp;
    int width;
    int height;
    String name;
    Random random = new Random();
    Timer timer = new Timer();
    public int spriteNum = 1;

    // TODO: 2023-06-02 유진
    // HP 바 관련 필드
    public int maxHpEnemy=100;
    public int currentHpEnemy;
    public int playerattackHp;     // 플레이어에게 공격당했을 때 줄어들 hp
    public int hpBarWidthEnemy=50;
    public int hpBarHeightEnemy;
    public Player player;

    public void setHpBarDefaultValues() {
        maxHpEnemy = 100;
        currentHpEnemy = maxHpEnemy - playerattackHp;
        hpBarWidthEnemy = 150;
        hpBarHeightEnemy = 5;
    }

    public void decreaseHp(int amount) {
        currentHpEnemy -= amount;
        if (currentHpEnemy < 0) {
            currentHpEnemy = 0;
        }
    }




    public Enemy() {
        setHpBarDefaultValues();
    }
    public Enemy(String string, int x, int y) {

    }
    public void moveChange(){

    }
    public void moveDirection() {

    }

}