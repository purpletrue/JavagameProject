package entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Muzan extends Enemy {
    private static BufferedImage muzan1; // Muzan의 왼쪽 이미지
    private static BufferedImage muzan2; // Muzan의 오른쪽 이미지
    private static BufferedImage muzan4;
    private String direction; // 이동 방향
    private boolean movingForward = true; // 스프라이트 애니메이션 전환을 위한 플래그
    private Player playerToFollow; // 따라다니는 대상 Player
    private int spriteNum = 1; // 스프라이트 번호
    private ImageIcon enemyMove;
    private int hpBarWidthEnemy = 100; // 적 체력바 너비

    static {
        getEnemyImage();
    }

    public Muzan() {
        setDefaultValues();
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
    }

    public void setDefaultValues() {
        x = 550; // 초기 X 좌표
        y = 550; // 초기 Y 좌표
        speed = 1; // 이동 속도
    }

    public static void getEnemyImage() {
        try {
            muzan1 = ImageIO.read(Objects.requireNonNull(Muzan.class.getResourceAsStream("/res/muzan1.png")));
            muzan2 = ImageIO.read(Objects.requireNonNull(Muzan.class.getResourceAsStream("/res/muzan2.png")));
            muzan4 = ImageIO.read(Objects.requireNonNull(Muzan.class.getResourceAsStream("/res/muzan4.png")));
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
            g2.drawImage(image, x, y, null); // 선택된 이미지를 그리기
        }

        g2.setColor(Color.RED);
        g2.fillRect(x, y - 10, hpBarWidthEnemy, hpBarHeightEnemy); // HP 바 배경색으로 채우기
        g2.setColor(Color.RED);
        int hpBarWidth = (int) ((double) currentHpEnemy / maxHpEnemy * this.hpBarWidthEnemy); // 현재 체력에 따라 바의 길이 계산
        g2.fillRect(x, y - 10, hpBarWidth, hpBarHeightEnemy); // 현재 체력에 맞게 HP 바 그리기

        // spriteNum 업데이트
        if (movingForward) {
            if (spriteNum == 1) {
                spriteNum = 2; // 스프라이트 번호를 1에서 2로 변경
                image = muzan1; // 여기에 원하는 이미지를 할당해주면 됩니다.
            } else if (spriteNum == 2) {
                spriteNum = 3; // 스프라이트 번호를 2에서 3로 변경
                image = muzan1; // 여기에 원하는 이미지를 할당해주면 됩니다.
            } else if (spriteNum == 3) {
                spriteNum = 4; // 스프라이트 번호를 3에서 4로 변경
                image = muzan1; // 여기에 원하는 이미지를 할당해주면 됩니다.
            } else if (spriteNum == 4) {
                spriteNum = 5; // 스프라이트 번호를 4에서 5로 변경
                image = muzan1; // 여기에 원하는 이미지를 할당해주면 됩니다.
            } else {
                spriteNum = 1; // 스프라이트 번호를 5에서 1로 변경
                image = muzan1; // 여기에 원하는 이미지를 할당해주면 됩니다.
            }
        }
    }
}