package entity;

import game.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class PlayerU extends Player {
    public PlayerU(GamePanel gp, KeyHandler keyH, int x, int y, int width, int height, Enemy enemy) {
        super(gp, x, y, width, height,enemy); // 부모 클래스인 Player의 생성자 호출
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        x = 150;
        y = 650;
        speed = 4;
        direction = "right";
        mp = 100;
        width = 96;
        height = 96;
        jumpSpeed=2;
        hp = 200;
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
            fire1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right1.png"));
            fire2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
