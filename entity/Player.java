package entity;

import game.Map1Panel;
import game.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player {

    protected Map1Panel gp;
    protected KeyHandler keyH;
    protected boolean isJumping = false;

    protected int x;
    protected int y;
    protected int speed;
    protected String direction;

    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;
    protected BufferedImage jump1;
    protected BufferedImage jump2;
    protected BufferedImage skill1;
    protected BufferedImage skill2;
    protected BufferedImage skill3;

    protected int spriteNum = 1;
    protected int spriteCounter = 0;

    public Player(Map1Panel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    // 초기 설정
    protected void setDefaultValues() {
        x = 150;
        y = 550;
        speed = 4;
        direction = "up";
    }

    protected void getPlayerImage() {
    }

    // 플레이어 상태 업데이트
    public void update() {
        handleKeyEvents();  // 키 입력 처리
        animateSprite();    // 스프라이트 애니메이션 처리
    }

    protected void handleKeyEvents() {
        // 키 입력 처리 로직
    }

    protected void animateSprite() {
        // 스프라이트 애니메이션 처리 로직
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
//            case "up" -> {
//                if (spriteNum == 1) {
//                    image = up1;
//                } else if (spriteNum == 2) {
//                    image = up2;
//                }
//            }
//                왼, 오 방향키 누르면서 점프시에 점프 이미지가 먼저 나왔으면 좋겠는데..
            case "jump" -> {
                if (spriteNum == 1 || (keyH.rightPressed || keyH.leftPressed) && spriteNum == 1) {
                    image = jump1;
                } else if (spriteNum == 2 || (keyH.rightPressed || keyH.leftPressed) && spriteNum == 2) {
                    image = jump2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void keyPressed(KeyEvent e) {
        // 키 눌림 이벤트 처리 로직
    }

    public void keyReleased(KeyEvent e) {
        // 키 릴리스 이벤트 처리 로직
    }

    public void jump(boolean b) {
        // 점프 로직
    }
}
