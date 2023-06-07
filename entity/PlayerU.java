package entity;

//import game.Background;
import game.KeyHandler;
import game.Map1Panel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PlayerU extends Player {
    public PlayerU(Map1Panel gp, KeyHandler keyH, int x, int y, int width, int height, Enemy enemy) {
        super(x, y, width, height,enemy); // 부모 클래스인 Player의 생성자 호출
        this.mp1 = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        updateAttack();
    }


    public void setDefaultValues() {
        x = 150;
        y = 650;
        speed = 4;
        direction = "right";
        currentHp = maxHp - decreaseHp; // 현재 체력
        mp = 100;
        width = 96;
        height = 96;
        jumpSpeed=2;
    }
    public void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_left2.png"));
            jump1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_jumpR.png"));
            jump2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_jumpL.png"));
            attack1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_attackR1.png"));
            attack2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_attackL1.png"));
            die = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_attackL1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
