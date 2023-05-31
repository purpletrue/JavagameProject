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

    public Enemy() {

    }
    public Enemy(String string, int x, int y) {

    }
    public void moveChange(){

    }
    public void moveDirection() {

    }

}