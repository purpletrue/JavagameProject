package entity;

import game.Map1Panel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    //    GamePanel gp;
    Map1Panel gp;
    KeyHandler keyH;
    public static boolean isJumping = false;

    public Player(Map1Panel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

//    초기 설정
    public void setDefaultValues() {
        x = 150;
        y = 550;
        speed = 4;
        direction = "up";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_right2.png"));
            jump1 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_jump2.png"));
            jump2 = ImageIO.read(getClass().getResourceAsStream("/res/tanjiro_jump2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//      플레이어 상태 업데이트.
    public void update() {
        handleKeyEvents();  //키 입력 처리
        animateSprite();    // 스프라이트 애니메이션 처리
    }

    private void handleKeyEvents() {
//        if (keyH.upPressed) {
//            direction = "up";
//            y -= speed;
//        }
//         else if (keyH.downPressed) {
//             direction = "down";
//             y += speed;
//         }
        if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        }

        if (keyH.spaceBarPressed) {
            jump(true);
        }
    }
//    캐릭터 이동중일 때 부드러운 애니메이션 효과 제공
//    spriteCounter는 매 프레임마다 1씩 증가. 10초과시 프레임 변경
    private void animateSprite() {
        if ( keyH.leftPressed || keyH.rightPressed) {
            spriteCounter++;  //spriteCounter는 애니메이션 프레임 간격을 제어하기 위한 변수
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
//                왼, 오 방향키 누르면서 점프시에 점프 이미지가 먼저 나왔으면 좋겠는데..
            case "jump":
                if (spriteNum == 1 || (keyH.rightPressed || keyH.leftPressed) && spriteNum == 1) {
                    image = jump1;
                } else if (spriteNum == 2||(keyH.rightPressed || keyH.leftPressed) && spriteNum == 2) {
                    image = jump2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            keyH.spaceBarPressed = true;
            direction = "jump";
            jump(true);
        }
        // 나머지 키 이벤트 처리 로직
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            keyH.spaceBarPressed = false;
        }
        // 나머지 키 이벤트 처리 로직
    }

    public void jump(boolean b) {
        if (!isJumping && b) {
            isJumping = true;
            Thread jumpThread = new Thread(new Runnable() {
                public void run() {
                    int initialY = y;
                    int targetY = y - 200;
                    int deltaY = -20;
                    while (y > targetY) {
                        y += deltaY;
                        try {
                            Thread.sleep(20); // 점프 속도 조절
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    deltaY = 10;
                    while (y < initialY) {
                        y += deltaY;
                        try {
                            Thread.sleep(20); // 점프 속도 조절
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        direction = "jump";
                        spriteNum = 1;
                    }
                    direction = "up";
                    spriteNum = 1;
                    isJumping = false;
                }
            });
            jumpThread.start();
        }
    }
}
