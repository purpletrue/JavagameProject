package entity;

import game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class EnemyMuzan extends Enemy {
    private static BufferedImage muzan1; // Muzan의 왼쪽 이미지
    private static BufferedImage muzan2; // Muzan의 오른쪽 이미지
    private static BufferedImage attack;
    private String direction; // 이동 방향
    private boolean movingForward = true; // 스프라이트 애니메이션 전환을 위한 플래그
    private Player playerToFollow; // 따라다니는 대상 Player
    private int hpBarWidthEnemy = 100; // 적 체력바 너비
    private int maxDistance = 100;
    private long lastAttackTime = 0;     // 스킬 샷
    private long attackCooldown = 3000; // 3초의 쿨다운 시간
    private GamePanel gamePanel;
    private boolean isDead = false;
    private boolean attacks = false; // 무잔이 날아다니는지 여부를 나타내는 플래그
    private double attackX; // 무잔이 X 좌표
    private double attackY; // 무잔이 Y 좌표
    private boolean attackLaunched = false;


    public EnemyMuzan(GamePanel gamePanel) {
        super(gamePanel);
        setDefaultValues();
        getEnemyImage();
    }

    public void setDefaultValues() {
        x = 550;
        y = 550;
        speed = 1;
        direction = "up";
        hp = 10;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getHp() {
        return hp;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void getEnemyImage() {
        try {
            muzan1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/muzan1.png")));
            muzan2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/muzan2.png")));
            attack = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/muzan2.png")));
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
            if (distanceX > maxDistance) {
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

    public void attackDefault() {
        double distanceX = Math.abs(playerToFollow.getX() - x);
        double distanceY = Math.abs(playerToFollow.getY() - y);
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        // 플레이어와 Muzan 객체 사이의 거리가 500 이하이고, 공격이 아직 실행 중이 아닌 경우
        if (distance <= 200 && !attacks) {
            attacks = true;
            attackX = x;
            attackY = y;

            // 이동 방향에 따라 공격의 초기 X 위치를 설정합니다.
            if (direction.equals("right")) {
                attackX = x + 50; // Muzan이 오른쪽으로 이동 중인 경우, 오른쪽으로 100만큼 이동한 공격 위치 설정
            } else if (direction.equals("left")) {
                attackX = x; // Muzan이 왼쪽으로 이동 중인 경우, 왼쪽으로 100만큼 이동한 공격 위치 설정
            }
        }

        if (attacks) {
            double directionX = (direction.equals("right")) ? 1 : -1; // Muzan의 X축 이동 방향 설정
            double directionY = 0; // Muzan의 Y축 이동 방향은 고정입니다.

            double distanceMoved = Math.sqrt((attackX - x) * (attackX - x) + (attackY - y) * (attackY - y));

            // 공격이 목표 위치까지 이동한 거리가 100 이상인 경우 공격을 종료합니다.
            if (direction.equals("right")) {
                // End the attack when Muzan moves more than 100 distance to the right of the target location.
                if (distanceMoved >= 200) {
                    attacks = false;
                }
            } else if (direction.equals("left")) {
                // End the attack when Muzan moves more than 100 distance to the left of the target location.
                if (distanceMoved >= 100) {
                    attacks = false;
                }
            }

            if (attacks) {
                // Muzan을 2의 속도로 앞으로 이동합니다. (속도는 필요에 따라 조정 가능합니다.)
                attackX += directionX * 2;
                attackY += directionY * 2;
            }
        }
    }

    public void attackSkill() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAttackTime < attackCooldown) {
            return; // 스킬이 쿨다운 상태일 경우 더 이상 진행하지 않습니다.
        }

        int damage = 20; // 스킬이 입히는 데미지
        int skillRange = 100; // 스킬의 범위

        if (playerToFollow != null) {
            int distanceX = this.x - playerToFollow.getX();
            int distanceY = this.y - playerToFollow.getY();
            double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

            if (distance <= skillRange) {
                playerToFollow.decreasePlayerHp(damage); // 플레이어의 체력을 감소시킵니다.
            }
        }
        lastAttackTime = currentTime; // 마지막 공격 시간을 현재 시간으로 업데이트 합니다.
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if (playerToFollow != null) {
            int playerX = playerToFollow.getX(); // Player의 X 좌표

            if (playerX < x) {
                image = muzan1; // Player가 Muzan의 왼쪽에 있는 경우, Muzan의 왼쪽 이미지 선택
            } else if (playerX > x) {
                image = muzan2;  // Player가 Muzan의 오른쪽에 있는 경우, Muzan의 오른쪽 이미지 선택
            }
        }
        if (image != null) {
            g2.drawImage(image, x, y, null);
        }
        if (attacks && attack != null) {
            int drawX = (int) attackX - attack.getWidth() / 2; // 위치 보정
            int drawY = (int) attackY - attack.getHeight() / 2; // 위치 보정
            g2.drawImage(attack, drawX, drawY + 40 , null);
        }
        g2.setColor(Color.RED);
        g2.fillRect(x, y - 10, hpBarWidthEnemy, hpBarHeightEnemy); // HP 바 배경색으로 채우기
        int hpBarWidthEnemy = (int) ((double) currentHpEnemy / maxHpEnemy * this.hpBarWidthEnemy); // 현재 체력에 따라 바의 길이 계산
        g2.fillRect(x, y - 10, this.hpBarWidthEnemy, hpBarHeightEnemy); // 현재 체력에 맞게 HP 바 그리기
    }

    public void update() {
        followCoordinates(); // player를 따라다니게 설정
        attackSkill(); // 공격 스킬을 호출
        attackDefault();
        int distanceX = this.x - playerToFollow.getX();
        int distanceY = this.y - playerToFollow.getY();
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance <= 50) {
            playerToFollow.decreasePlayerHp(10);
        }
    }
}