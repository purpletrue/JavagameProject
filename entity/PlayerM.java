package entity;

import game.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerM extends Player {
    // 이미지 변수 추가

    public PlayerM(Map1Panel gp, KeyHandler keyH, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.mp1 = gp;
        this.keyH = keyH;
        getPlayerImage();
        setDefaultValues();
        updateAttack();
        setHpBar();
    }

    public void setDefaultValues() {
        x = 150;
        speed = 4;
        direction = "right";
        currentHp = maxHp - decreaseHp; // 현재 체력
        mp = 120;
        width = 150;
        height = 150;
    }

    public void getPlayerImage() {
        try {
            // 이미지 로딩 코드 수정
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

    // Graphics2D 객체를 인자로 받아와서 사용

}
