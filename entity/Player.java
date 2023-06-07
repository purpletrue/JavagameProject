package entity;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
    int attackCoolDown, attackCoolDownMax;        // 공격 관련. 공격 연속으로 못 하게 변수 설정
    boolean seeWhere;       //보는 방향?
    BufferedImage right1, right2, left1, left2, jump1, jump2, attack1, attack2, die;       // 캐릭터 이미지
    String direction;       // 캐릭터 상태를 알려주는 문자열 변수
    int spriteCounter = 0;      //캐릭터 이미지를 변경하기 위한 변수
    public int spriteNum = 1;       // 위와 마찬가지

    // TODO: 2023-06-02 유진
    // HP 바 변수
    public int maxHp = 100; // 최대 체력
    public int currentHp; // 현재 체력
    public int decreaseHp;  // 적에게 공격당했을때 줄어들 hp
    public int hpBarWidth = 50;
    public int hpBarHeight;
    public Enemy enemy;
    public int hpwidth;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mp1 = mp1;  // mp1 변수에 Map1Panel 인스턴스 할당
        initBackgroundPlayerService();
    }


    public void setHpBar() {
        maxHp = 100;
        currentHp = maxHp - decreaseHp;
        hpBarWidth = 100;
        hpBarHeight = 5;
    }

    public void update() {
        handleKeyEvents();
        animateSprite();
        updateAttack();
        setHpBar();
    }

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
            attack();
        }
    }

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

    public void up() {
        if (!up) {
            up = true;
            new Thread(() -> {
                int initialY = y;
                for (int i = 0; i < 100 && up; i++) {
                    y -= jumpSpeed;
                    setLocation(x, y);
                    if (y <= initialY - 150) { // 100은 점프 높이
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

    public void moveRight() {
        x += speed; // 플레이어의 x 좌표를 오른쪽으로 이동
        direction = "right"; // 이동 방향을 오른쪽으로 설정
    }

    public void moveLeft() {
        x -= speed; // 플레이어의 x 좌표를 왼쪽으로 이동
        direction = "left"; // 이동 방향을 왼쪽으로 설정

    }

    public void attack() {
        if (!attack) {
            attack = true;
            attackCoolDown = attackCoolDownMax;
            if (spriteNum == 1) {
                spriteNum = 1;
            } else if (spriteNum == 2) {
                spriteNum = 2;
            }

//            // 플레이어와 보스의 거리 계산
//            int distance = calculateDistanceToBoss();
//
//            // 보스의 공격 범위
//            int attackRange = 100; // 적절한 공격 범위 값으로 수정해야 합니다.
//
//            // 플레이어가 보스의 공격 범위에 들어왔을 때 공격 성공
//            if (distance <= attackRange) {
//                // 보스의 체력 감소
//                EnemyHp -= 50; // 보스의 체력 감소량을 적절한 값으로 수정해야 합니다.
//            }
        }
    }
//    private int calculateDistanceToBoss() {
//        int playerX = x; // 플레이어의 X 좌표
//        int playerY = y; // 플레이어의 Y 좌표
//        int bossX = Enemy.getX(); // 보스의 X 좌표
//        int bossY = Enemy.getY(); // 보스의 Y 좌표
//
//        int distanceX = Math.abs(playerX - bossX); // X 좌표 간 거리 계산
//        int distanceY = Math.abs(playerY - bossY); // Y 좌표 간 거리 계산
//
//        // 두 좌표 사이의 거리를 피타고라스의 정리를 이용하여 계산
//        int distance = (int) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
//
//        return distance;
//    }


    public void updateAttack() {
        if (attack) {
            attackCoolDown--;

            if (attackCoolDown <= 0) {
                attack = false;
                attackCoolDown = 0;
            }
        }
    }


    private void initBackgroundPlayerService() {
        new Thread(new Background(this)).start();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
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

    public boolean isLeftWallCrash() {
        return leftWallCrash;
    }


    public void setDown(boolean down) {
        this.down = down;
    }
}





