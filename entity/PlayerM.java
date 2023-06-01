package entity;

import game.KeyHandler;
import game.Map1Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerM extends Player {
    public PlayerM(Map1Panel gp, KeyHandler keyH) {
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
        hp = 80;
        mp = 120;

        width = 150;
        height = 150;
    }
    public void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_left2.png"));
            jump1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_jump2.png"));
            jump2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_jump2.png"));
            attack1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_attack1.png"));
            attack2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_attack2.png"));
            die = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_jump2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    오버라이딩 한 이유는 캐릭터를 크게 키우면 히트박스도 커져야 하니까 g2.drawImage(image, x, y, width * 2, height * 2, null); 를 오버라이딩 한 것임..
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            // TODO: 2023-06-01 sj. 왼쪽방향 점프도 설정해야 함..
            case "jump":
                if (spriteNum == 1 || (keyH.rightPressed || keyH.leftPressed) && spriteNum == 1) {
                    image = jump1;
                } else if (spriteNum == 2 || (keyH.rightPressed || keyH.leftPressed) && spriteNum == 2) {
                    image = jump2;
                }
                break;
            case "attack":
                if (spriteNum == 1) {
                    image = attack1;
                } else if (spriteNum == 2) {
                    image = attack2;
                }
                break;
//                스킬샷에 관한 이미지도 여기서 넣어야 함
//            case "skill1":
//                if (spriteNum == 1) {
//                    image = attack1;
//                } else if (spriteNum == 2) {
//                    image = attack2;
//                }
//                break;

        }
        g2.drawImage(image, x, y, width * 2, height * 2, null);
    }

}



