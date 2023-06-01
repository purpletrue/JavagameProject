package entity;

import game.KeyHandler;
import game.Map1Panel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PlayerU extends Player {
    public PlayerU(Map1Panel gp, KeyHandler keyH) {
        this.mp1 = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        updateAttack();
    }
    public void setDefaultValues() {
        x = 150;
        y = 550;
        speed = 4;
        direction = "right";
        hp = 100;
        mp = 100;
        width = 96;
        height = 96;
    }
    public void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_left2.png"));
            jump1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_jump2.png"));
            jump2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_jump2.png"));
            attack1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_jump2.png"));
            attack2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_jump2.png"));
            die = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_jump2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
