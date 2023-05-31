//package entity;
//
//import game.GamePanel;
//import game.KeyHandler;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
//public class Character01 extends Entity {
//
//    GamePanel gp;
//    KeyHandler keyH;
//
//    public Character01(GamePanel gp, KeyHandler keyH) {
//        this.gp = gp;
//        this.keyH = keyH;
//        setDefaultValues();
//        getPlayerImage();
//    }
//    public void setDefaultValues() {
//        x = 100;
//        y = 100;
//        speed = 4;
//        direction = "down";
//    }
//    public void getPlayerImage(){
//        try {
//            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player.png"));
//            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player.png"));
//            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player.png"));
//            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player.png"));
//            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player.png"));
//            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player.png"));
//            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player.png"));
//            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player.png"));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public void update(){
//        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
//            if (keyH.upPressed) {
//                direction = "up";
//                y -= speed;
//            } else if (keyH.downPressed) {
//                direction = "down";
//                y += speed;
//            } else if (keyH.leftPressed) {
//                direction = "left";
//                x -= speed;
//            } else {
//                direction = "right";
//                x += speed;
//            }
//            spriteCounter++;
//            if (spriteCounter > 10) {    // 10은 속도 조절하는 것... 숫자 바꿔서 조절
//                if (spriteNum == 1) {
//                    spriteNum = 2;
//                } else if (spriteNum == 2) {
//                    spriteNum = 1;
//                }
//                spriteCounter = 0;
//            }
//        }
//    }
//    public void draw(Graphics2D g2) {
//        BufferedImage image = null;
//        switch (direction) {
//            case "up":
//                if (spriteNum == 1) {
//                    image = up1;
//                }
//                if (spriteNum == 2) {
//                    image = up2;
//                }
//                break;
//            case "down":
//                if (spriteNum == 1) {
//                    image = down1;
//                }
//                if (spriteNum == 2) {
//                    image = down2;
//                }
//                break;
//            case "left":
//                if (spriteNum == 1) {
//                    image = left1;
//                }
//                if (spriteNum == 2) {
//                    image = left2;
//                }
//                break;
//            case "right":
//                if (spriteNum == 1) {
//                    image = right1;
//                }
//                if (spriteNum == 2) {
//                    image = right2;
//                }
//                break;
//        }
//        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize,null);
//
//    }
//
//    public void healing(){
//
//    }
//    public void moveRangeR(){
//
//    }
//    public void moveRangeL(){
//
//    }
//    public void moveDown(height){
//
//    }
//    public void moveJump(){
//
//    }
//    public void moveWaiting(){
//
//    }
//    public void moveWatingLeft(){
//
//    }
//    public void attack(){
//
//    }
//    public void dieDown(){
//
//    }
//    public void skill1(){
//
//    }
//    public void skill2(){
//
//    }
//    public void skill3(){
//
//    }
//
//
//
//
//
//}
