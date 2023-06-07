package entity;

import game.KeyHandler;
import game.Map1Panel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PlayerY extends Player {
    public PlayerY(Map1Panel gp, KeyHandler keyH, int x, int y, int width, int height, Enemy enemy) {
        super(x, y, width, height, enemy); // 부모 클래스인 Player의 생성자 호출
        this.mp1 = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        updateAttack();
        setHpBar();
    }
    public void setDefaultValues() {
        x = 150;
        y = 700;
        speed = 4;
        direction = "right";
        hp = 80;
        mp = 120;
        width = 96;
        height = 96;
    }
    public void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_left2.png"));
            jump1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_jumpR.png"));
            jump2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_jumpL.png"));
            attack1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_attackR1.png"));
            attack2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_attackR2.png"));
            die = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_jumpR.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


