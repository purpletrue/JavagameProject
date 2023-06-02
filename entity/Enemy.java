// TODO: 2023-06-02 체력 구현, 기본공격 구현, 움직임 제어, 자기 체력 닳는거 구현, 플레이어 따라가기





package entity;

import game.KeyHandler;
import game.Map1Panel;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel {

    int x, y;
    int hp;
    int width, height;
    int speed;
    String name;
    String direction;
    boolean movingForward = true;
    int distanceMoved = 0;
    ImageIcon imageicon;
    final static String TAG = "Enemy : ";
    Random random = new Random();
    Timer timer = new Timer();
    public int spriteNum = 1;

    // TODO: 2023-06-02 유진
    // HP 바 관련 필드
    public int maxHpEnemy=100;
    public int currentHpEnemy;
    public int playerAttackHp;     // 플레이어에게 공격당했을 때 줄어들 hp
    public int hpBarWidthEnemy=20;
    public int hpBarHeightEnemy;


    public void playerAttackHp(int amount) {
        currentHpEnemy -= amount;
        if (currentHpEnemy < 0) {
            currentHpEnemy = 0;
        }
    }
    public void setHpBarDefaultValues() {
        maxHpEnemy = 100;
        currentHpEnemy = maxHpEnemy - playerAttackHp;
        hpBarWidthEnemy = 150;
        hpBarHeightEnemy = 5;
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