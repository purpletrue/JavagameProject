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
    private String direction;
    private boolean movingForward = true;

    public Muzan() {
        setDefaultValues();
        getEnemyImage();
    }

    public Muzan(String string, int x, int y, int hp, String name) {
        enemyMove = new ImageIcon(string);
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = enemyMove.getIconWidth();
        this.height = enemyMove.getIconHeight();
        this.hp = hp;

        setIcon(enemyMove);
        setSize(850, 700);
        setLocation(x, y);

        setDefaultValues();
        getEnemyImage();
    }

    public void setDefaultValues() {
        x = 550;
        y = 550;
        speed = 20; // Set the speed to 20
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

    public void moveForwardAndBackward() {
        if (movingForward) {
            x += speed; // Move the image forward
        } else {
            x -= speed; // Move the image backward
        }

        // Check if the image has reached the boundaries
        if (x <= 0) {
            movingForward = true; // Change direction to forward
        } else if (x >= getWidth() - width) {
            movingForward = false; // Change direction to backward
        }
    }

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
    }
}