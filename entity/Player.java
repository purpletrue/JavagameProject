

// PlayerU, PlayerY, PlayerM 의 부모클래스로, 플레이어의 공통적인 특성을 정의하는 클래스입니다.


package entity;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;


public class Player extends JLabel {
    Map1Panel mp1;
    //    Map2Panel mp2;
//    Map3Panel mp3;
    KeyHandler keyH;

    int x, y;       // 캐릭터 위치한 좌표
    int hp, mp;     // 캐릭터 체력, 마력
    int width, height;      // 캐릭터 크기
    int speed, jumpSpeed;      // 캐릭터 속도
    boolean right, left, move, attack;        //캐릭터 상태
    private boolean up;
    private volatile boolean down;
    private boolean leftWallCrash, rightWallCrash;
    BufferedImage right1, right2, left1, left2, jump1, jump2, attack1, attack2, die;       // 캐릭터 이미지
    String direction;       // 캐릭터 상태를 알려주는 문자열 변수
    int spriteCounter = 0;      //캐릭터 이미지를 변경하기 위한 변수
    public int spriteNum = 1;       // 위와 마찬가지
    public int maxHp = 100; // 최대 체력
    public int currentHp; // 현재 체력
    public int decreaseHp;  // 적에게 공격당했을때 줄어들 hp
    public int hpBarWidth = 50;
    public int hpBarHeight;
    public Enemy enemy;
    private GamePanel gamePanel;
    private long lastDecreaseTime = 0;
    private volatile boolean isDead = false;  //죽었을 때 변수를 추가해 체력이 0이 될 때 true로 설정함.

//    생성자
    public Player(GamePanel gp, int x, int y, int width, int height, Enemy enemy) {
        this.gamePanel = gp;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.enemy = enemy;
        initBackgroundPlayerService();
    }

    private void initBackgroundPlayerService() {
        new Thread(new Background(this)).start();
    }

//    플레이어 상태를 업데이트
    public void update() {
        handleKeyEvents();
        animateSprite();
        if (keyH.isKeyPressed(KeyEvent.VK_K)) {
            enemy.decreaseEnemyHp(1,this);
        }
    }

    public void decreasePlayerHp(int amount) {
        long currentTime = System.currentTimeMillis();
        // 체력 감소 후 3초가 지나지 않았다면 데미지를 받지 않음. (무적 시간)
        if (currentTime - lastDecreaseTime < 3000) {
            return;
        }
        if (isDead) {
            return;
        }
        System.out.println("Player HP " + hp);
        this.hp -= amount;
        lastDecreaseTime = currentTime; // 체력 감소 시간 업데이트

        if (this.hp <= 0) {
            isDead = true;
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "다시 도전하세요..");
                gamePanel.returnToBeginningPanel();
            });
        }
    }




    //    키보드 키 입력시 상태 변화
    private void handleKeyEvents() {
        if (keyH.leftPressed && !isLeftWallCrash()) {
            direction = "left";
            moveLeft();
        } else if (keyH.rightPressed && !isRightWallCrash()) {
            direction = "right";
            moveRight();
        }
        if (keyH.spaceBarPressed) {
            direction = "jump";
            up();
        }
        if (keyH.xPressed && !attack) {
            direction = "attack";
            if (this.enemy != null) {
                enemy.decreaseEnemyHp(1,this);
            }
        }
    }

//    캐릭터 이미지를 변경해 애니메이션 효과를 주는 메서드. animateSprite() 에서 변경된 spriteNum 으로 draw() 에서 다른 이미지를 보여줌.
    public void animateSprite() {
        if (keyH.leftPressed || keyH.rightPressed || (keyH.spaceBarPressed && direction.equals("jump"))) {
            spriteCounter++;
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
//    상태별 캐릭터 이미지 표시
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
        g2.drawImage(image, x, y, width, height, null);
    }

//    점프 관련 메서드
    public void up() {
        if (!up) {
            up = true;
            new Thread(() -> {
                int initialY = y;
                for (int i = 0; i < 100 && up; i++) {
                    y -= jumpSpeed;
                    setLocation(x, y);
                    if (y <= initialY - 150) { // 숫자는 점프 높이
                        up = false;
                        down();
                    }
                    try {
                        Thread.sleep(5);
                    } catch (Exception e) {
                        System.out.println("위쪽 이동중 인터럽트 발생 : " + e.getMessage());
                    }
                }
            }).start();
        }
    }

//    Background 클래스에서 가져온 스레드로 바닥을 확인해, 없으면 하강하도록 만듬.
    public void down() {
        down = true;
        new Thread(() -> {
            int initialY = y;
            while (down) {
                y += jumpSpeed;
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            down = false;
        }).start();
    }

    public void moveRight() {
        x += speed;
        direction = "right";
    }
    public void moveLeft() {
        x -= speed;
        direction = "left";
    }
    public boolean isDown() {
        return down;
    }
    public boolean isUp() {
        return up;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setDown(boolean down) {
        this.down = down;
    }

//    벽 충돌을 감지하기 위한 getter, setter
    public boolean isLeftWallCrash() {
    return leftWallCrash;
}
    public void setLeftWallCrash(boolean leftWallCrash) {
        this.leftWallCrash = leftWallCrash;
    }
    public boolean isRightWallCrash() {
        return rightWallCrash;
    }
    public void setRightWallCrash(boolean rightWallCrash) {
        this.rightWallCrash = rightWallCrash;
    }
    public int getHp() {
        return hp;
    }

}
