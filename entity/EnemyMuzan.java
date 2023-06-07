package entity;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class EnemyMuzan extends Enemy {
    private static BufferedImage muzan1; // Muzan의 왼쪽 이미지
    private static BufferedImage muzan2; // Muzan의 오른쪽 이미지
    private String direction; // 이동 방향
    private boolean movingForward = true; // 스프라이트 애니메이션 전환을 위한 플래그
    private Player playerToFollow; // 따라다니는 대상 Player
    private int spriteNum = 1; // 스프라이트 번호
    private int hpBarWidthEnemy = 100; // 적 체력바 너비
    private int maxDistance = 100;

    public EnemyMuzan() {
        setDefaultValues();
        getEnemyImage();
    }

    public EnemyMuzan(String string, int x, int y, int hp, String name) {
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
        speed = 1;
        direction = "up";
        hp = 1000;
    }

    public void getEnemyImage() {
        try {
            muzan1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/muzan1.png")));
            muzan2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/muzan2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayer(Player player) {
        this.playerToFollow = player;
    }

    public void followCoordinates() {
        if (playerToFollow != null) {
            int targetX = playerToFollow.getX(); // 따라다닐 대상의 X 좌표
            int targetY = playerToFollow.getY(); // 따라다닐 대상의 Y 좌표
            int distanceX = Math.abs(targetX - x);

            // player와 muzan 사이의 거리가 maxDistance 이상일 때만 이동
            if (Math.abs(distanceX) > maxDistance) {
                if (x < targetX) {
                    x += speed; // 타겟의 X 좌표를 따라 오른쪽으로 이동
                    direction = "right"; // 이동 방향을 오른쪽으로 설정
                } else if (x > targetX) {
                    x -= speed; // 타겟의 X 좌표를 따라 왼쪽으로 이동
                    direction = "left"; // 이동 방향을 왼쪽으로 설정
                }

                if (y < targetY) {
                    y += speed; // 타겟의 Y 좌표를 따라 아래로 이동
                    direction = "down"; // 이동 방향을 아래로 설정
                } else if (y > targetY) {
                    y -= speed; // 타겟의 Y 좌표를 따라 위로 이동
                    direction = "up"; // 이동 방향을 위로 설정
                }
                movingForward = !movingForward; // 이동 방향이 변경되었으므로 스프라이트 애니메이션 전환을 위한 플래그 업데이트
            }
        }
    }

    public void draw(Graphics2D g2) {
        followCoordinates(); // 플레이어를 따라가도록 위치 업데이트
        BufferedImage image = null;
        if (playerToFollow != null) {
            int playerX = playerToFollow.getX(); // Player의 X 좌표

            if (playerX < x) {
                image = (movingForward && spriteNum == 1) ? muzan1 : muzan2; // Player가 Muzan의 왼쪽에 있는 경우, Muzan의 왼쪽 이미지 선택
            } else if (playerX > x) {
                image = muzan2;  // Player가 Muzan의 오른쪽에 있는 경우, Muzan의 오른쪽 이미지 선택
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