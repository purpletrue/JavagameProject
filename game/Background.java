

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

    public BufferedImage image;
    private final Player player;
    private final EnemyMuzan muzan;
    private boolean running;
    private EnemyMuzan playerToFollow;

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
            int x = player.getX();
            int y = player.getY();
            Color leftColor = new Color(image.getRGB(x + 10, y + 25));
            Color rightColor = new Color(image.getRGB(x + 90, y + 25));
            int bottomColor = image.getRGB(x+72,y + 132);

//            바닥 충돌 확인
            if (bottomColor != -1) {
                if(player.isDown()) {
                    player.setDown(false);
                }
            } else {
                if (!player.isUp() && !player.isDown()) {
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
                running = false;
                break;
            }
            int muzanX = muzan.getX();
            int muzanY = muzan.getY();
            Color muzanLeftColor = new Color(image.getRGB(muzanX + 10, muzanY + 25));
            Color muzanRightColor = new Color(image.getRGB(muzanX + 90, muzanY + 25));
            int btColor = image.getRGB(muzanX + 72, muzanY + 132);

            //muzan 바닥과의 충돌 감지
            if (btColor != -1) {
                if (muzan.isDown()) {
                    muzan.setDown(false);
                }
            } else {
                if (!muzan.isUp() && !muzan.isDown()) {
                    muzan.down();
                }
            }

            // muzan 벽과의 충돌 감지
            muzan.setLeftWallCrash(muzanLeftColor.getRed() == 255 && muzanLeftColor.getGreen() == 0 && muzanLeftColor.getBlue() == 0);

            muzan.setRightWallCrash(muzanRightColor.getRed() == 255 && muzanRightColor.getGreen() == 0 && muzanRightColor.getBlue() == 0);


            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}