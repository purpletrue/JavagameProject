package game;

import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background implements Runnable {

    private BufferedImage image;
    private Player player;

    public Background(Player player) {
        this.player = player;
        try {
            image = ImageIO.read(new File("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\test.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            int x = player.getX();
            int y = player.getY();
            Color leftColor = new Color(image.getRGB(x + 10, y + 25));
            Color rightColor = new Color(image.getRGB(x + 90, y + 25));
            int bottomColor = image.getRGB(x+90,y + 132);
//            System.out.println(bottomColor);

//            바닥 충돌 확인
            if (bottomColor != -1) {
//                System.out.println("바닥에 충돌");
                if(player.isDown()) {
                    player.setDown(false);
                }
            } else {
                if (!player.isUp()&&!player.isDown()) {
                    player.down();
                }
            }
//            외벽 충돌 확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
                System.out.println("왼쪽 벽 충돌");
                player.setLeftWallCrash(true);
            } else {
                player.setLeftWallCrash(false);
            }
            if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
                System.out.println("오른쪽 벽 충돌");
                player.setRightWallCrash(true);
            } else {
                player.setRightWallCrash(false);
            }
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
