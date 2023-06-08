package entity;

import game.*;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PlayerM extends Player {

    public PlayerM(GamePanel gp, KeyHandler keyH, int x, int y, int width, int height, Enemy enemy) {
        super(gp, x, y, width, height, enemy);
        this.keyH = keyH;
        getPlayerImage();
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 150;
        speed = 4;
        direction = "right";
        currentHp = maxHp - decreaseHp;
        mp = 120;
        width = 150;
        height = 150;
        hp = 200;
    }

    public void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResource("/res/rengoku_right1.png"));
            right2 = ImageIO.read(getClass().getResource("/res/rengoku_right2.png"));
            left1 = ImageIO.read(getClass().getResource("/res/rengoku_left1.png"));
            left2 = ImageIO.read(getClass().getResource("/res/rengoku_left2.png"));
            jump1 = ImageIO.read(getClass().getResource("/res/rengoku_jumpR.png"));
            jump2 = ImageIO.read(getClass().getResource("/res/rengoku_jumpL.png"));
            attack1 = ImageIO.read(getClass().getResource("/res/rengoku_attackR1.png"));
            attack2 = ImageIO.read(getClass().getResource("/res/rengoku_attackR2.png"));
            die = ImageIO.read(getClass().getResource("/res/rengoku_jumpR.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
