package entity;

import game.Map1Panel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerU extends Entity {

    Map1Panel gp;
    KeyHandler keyH;
    public static boolean isJumping = false;

    public PlayerU(Map1Panel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

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

    public void update() {
        handleKeyEvents();
        animateSprite();
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
        // Handle other key events
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            keyH.spaceBarPressed = false;
        }
        // Handle other key events
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
}
