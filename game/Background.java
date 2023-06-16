

// 캐릭터의 움직임 범위를 지정하고, 발판을 확인하는 코드입니다.


package game;

import entity.Player;
import entity.EnemyMuzan;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Background implements Runnable {

    private BufferedImage image;
    private Player player;
    private EnemyMuzan muzan;
    private boolean running;

    public Background(Player player, EnemyMuzan muzan) {
        this.player = player;
        this.muzan = muzan;
        this.running = true;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/test.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (running) {
            int playerX = player.getX();
            int playerY = player.getY();
            Color leftColor = new Color(image.getRGB(playerX + 10, playerY + 25));
            Color rightColor = new Color(image.getRGB(playerX + 90, playerY + 25));
            int bottomColor = image.getRGB(playerX + 72, playerY + 132);

            // 바닥과의 충돌 감지
            if (bottomColor != -1) {
                if (player.isDown()) {
                    player.setDown(false);
                }
            } else {
                if (!player.isUp() && !player.isDown()) {
                    player.setDown();
                }
            }

            // 벽과의 충돌 감지
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
                player.setLeftWallCrash(true);
            } else {
                player.setLeftWallCrash(false);
            }

            if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
                player.setRightWallCrash(true);
            } else {
                player.setRightWallCrash(false);
            }

            // 무장과 벽과의 충돌 감지
            int muzanX = muzan.getX();
            int muzanY = muzan.getY();
            Color muzanLeftColor = new Color(image.getRGB(muzanX + 10, muzanY + 25));
            Color muzanRightColor = new Color(image.getRGB(muzanX + 90, muzanY + 25));

            if (muzanLeftColor.getRed() == 255 && muzanLeftColor.getGreen() == 0 && muzanLeftColor.getBlue() == 0) {
                muzan.setLeftWallCrash(true);
            } else {
                muzan.setLeftWallCrash(false);
            }

            if (muzanRightColor.getRed() == 255 && muzanRightColor.getGreen() == 0 && muzanRightColor.getBlue() == 0) {
                muzan.setRightWallCrash(true);
            } else {
                muzan.setRightWallCrash(false);
            }

            // 비계와의 충돌 감지
            Color playerTopColor = new Color(image.getRGB(playerX + 50, playerY + 10));
            Color muzanTopColor = new Color(image.getRGB(muzanX + 50, muzanY + 10));

            if (playerTopColor.getRed() == 255 && playerTopColor.getGreen() == 255 && playerTopColor.getBlue() == 0) {
                player.setScaffoldCrash(true);
            } else {
                player.setScaffoldCrash(false);
            }

            if (muzanTopColor.getRed() == 255 && muzanTopColor.getGreen() == 255 && muzanTopColor.getBlue() == 0) {
                muzan.setScaffoldCrash(true);
            } else {
                muzan.setScaffoldCrash(false);
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

