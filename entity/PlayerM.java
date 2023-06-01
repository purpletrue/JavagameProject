package entity;

import game.Map1Panel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerM extends Entity {

    Map1Panel gp;
    KeyHandler keyH;
    public static boolean isJumping = false;

    // TODO: 2023-06-01 유진 hp 공격 수정중
    private boolean isAttacking = false;
    private int attackCooldown = 0;
    private int attackCooldownMax = 30;
    private BufferedImage attack1;
    private BufferedImage attack2;
    private BufferedImage lastDirectionImage;

    public PlayerM(Map1Panel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        updateAttack();
    }

    public void setDefaultValues() {
        x = 150;
        y = 450;
        speed = 8;
        direction = "up";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_right2.png"));
            jump1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_jump2.png"));
            jump2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_jump2.png"));
            // 유진 어택 추가
            attack1 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_attack1.png"));
            attack2 = ImageIO.read(getClass().getResourceAsStream("/res/rengoku_attack2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        handleKeyEvents();
        animateSprite();
        checkBounds();
    }

    private void handleKeyEvents() {
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
        // 유진- 어택 추가
        if (keyH.xPressed && !isAttacking) {
            direction = "attack";
            attack();
        }

    }

    private void animateSprite() {
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
        int newWidth = 200;  // 변경할 가로 크기
        int newHeight = 200; // 변경할 세로 크기
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "jump":
                if (spriteNum == 1 || (keyH.rightPressed || keyH.leftPressed) && spriteNum == 1) {
                    image = jump1;
                } else if (spriteNum == 2 || (keyH.rightPressed || keyH.leftPressed) && spriteNum == 2) {
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
            case "attack":
                if (spriteNum == 1) {
                    image = attack1;
                } else if (spriteNum == 2) {
                    image = attack2;
                } else {
                    // 공격 후 원래 이미지로 돌아오도록 설정
                    if (direction.equals("left")) {
                        image = left1;
                    } else if (direction.equals("right")) {
                        image = right1;
                    }
                }
                break;

        }
        if (image != null) {
            Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
            g2.drawImage(resizedImage, x, y, null);
        }

    }

    public void checkBounds() {
        int maxX = gp.getWidth() - gp.tileSize; // 오른쪽 경계에 타일의 크기(gp.tileSize)를 고려하여 계산
        int maxY = gp.getHeight() - gp.tileSize; // 아래쪽 경계에 타일의 크기(gp.tileSize)를 고려하여 계산

        if (x < 0) {
            x = 0;
        } else if (x > maxX) {
            x = maxX;
        }

        if (y < 0) {
            y = 0;
        } else if (y > maxY) {
            y = maxY;
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            keyH.spaceBarPressed = true;
            direction = "jump";
            jump(true);
        }
        // Handle other key events
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            keyH.spaceBarPressed = false;
        }
        // Handle other key events
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
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
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    deltaY = 10;
                    while (y < initialY) {
                        y += deltaY;
                        try {
                            Thread.sleep(20);
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

    // TODO: 2023-06-01 유진 어택 추가
    private void attack() {
        if (!isAttacking && attackCooldown <= 0) {
            isAttacking = true;
            attackCooldown = attackCooldownMax;

            Thread attackThread = new Thread(new Runnable() {
                public void run() {
                    // 공격 애니메이션 처리 코드 작성
                    BufferedImage currentImage = lastDirectionImage;
                    BufferedImage attackImage;

                    if (spriteNum == 1) {
                        attackImage = attack1;
                    } else {
                        attackImage = attack2;
                    }

                    // 몬스터와의 충돌 체크 및 HP 감소 처리
//                    Rectangle attackBounds = new Rectangle(x, y, width, height);  // 공격 범위 바운딩 박스
//                    Monster monster = gp.getMonster();  // 몬스터 객체 가져오기
//
//                    if (monster != null && attackBounds.intersects(monster.getBounds())) {
//                        monster.decreaseHP(attackDamage);
//                    }

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (currentImage != lastDirectionImage) {
                        currentImage = lastDirectionImage;
                    }


                    isAttacking = false;
                }
            });

            attackThread.start();
        }
    }


    private void updateAttack() {
        if (attackCooldown > 0) {
            attackCooldown--;
        }
    }
}

