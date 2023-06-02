package entity;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Muzan extends Enemy {
    private BufferedImage muzan1;
    private BufferedImage muzan2;
    private int maxDistance = 200;

    public Muzan() {
        setDefaultValues();
        getEnemyImage();
    }

    public Muzan(String string, int x, int y, int hp, String name) {
        imageicon = new ImageIcon(string);
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = imageicon.getIconWidth();
        this.height = imageicon.getIconHeight();
        this.hp = hp;

        setIcon(imageicon);
        setSize(850, 700);
        setLocation(x, y);

        setDefaultValues();
        getEnemyImage();
    }

    public void setDefaultValues() {
        x = 550;
        y = 550;
        speed = 5;
        direction = "up";
    }

    public void getEnemyImage() {
        try {
            muzan1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/muzan1.png")));
            muzan2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/muzan2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void moveForwardAndBackward() {
//        if (movingForward) {
//            x += speed; // Move the image forward
//            distanceMoved += speed;
//        } else {
//            x -= speed; // Move the image backward
//            distanceMoved -= speed;
//        }

        // Check if the image has reached the boundaries
//        if (distanceMoved >= maxDistance) {
//            movingForward = false; // Change direction to backward
//        } else if (distanceMoved <= -maxDistance) {
//            movingForward = true; // Change direction to forward
//        }
//    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if (direction.equals("up")) {
            if (spriteNum == 1) {
                image = muzan1;
            } else if (spriteNum == 2) {
                image = muzan2;
            }
        }
        if (image != null) {
            g2.drawImage(image, x, y, null);
        }

            g2.setColor(Color.RED);
            g2.fillRect(x, y - 10, hpBarWidthEnemy, hpBarHeightEnemy); // HP 바 배경색으로 채우기
            g2.setColor(Color.RED);
            int hpBarWidthEnemy = (int) ((double) currentHpEnemy / maxHpEnemy * this.hpBarWidthEnemy); // 현재 체력에 따라 바의 길이 계산
            g2.fillRect(x, y - 10, this.hpBarWidthEnemy, hpBarHeightEnemy); // 현재 체력에 맞게 HP 바 그리기
    }
}
