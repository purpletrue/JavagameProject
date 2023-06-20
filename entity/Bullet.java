package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends JLabel {
    private int x, y; // 총알 위치
    private int targetX, targetY; // 플레이어 위치
    private int speed; // 총알 속도
    private int damage; // 총알 데미지
    private Player muzanTarget;
    private double totalTravelDistance = 0; // 추가된 총 이동 거리 변수
    private double maxTravelDistance = 1000; // 총알이 이동할 수 있는 최대 거리

    public Bullet(int x, int y, int targetX, int targetY, int damage) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.speed = 1; // 총알 속도 설정
        this.damage = 15;

        setIcon(new ImageIcon(getClass().getResource("/res/nezuko_left1.png"))); // 이미지 아이콘 설정
        setBounds(x, y, 50, 50); // 총알 크기 설정
    }

    public void move() {
        // 플레이어 위치를 향해 총알을 이동시키는 로직을 구현합니다.
        double angle = Math.atan2(targetY - y, targetX - x);
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        x += dx;
        y += dy;

        // 총알이 이동한 거리 갱신
        totalTravelDistance += Math.sqrt(dx * dx + dy * dy);

        setLocation((int) x, (int) y);
    }

    public boolean collidesWith(Player player) {
        // 플레이어와 총알의 충돌 여부를 판단하는 로직을 구현
        Rectangle bulletBounds = new Rectangle(x, y, getWidth(), getHeight());
        Rectangle playerBounds = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        // 충돌 발생 시 플레이어에게 데미지 적용
        if (bulletBounds.intersects(playerBounds)) {
            player.decreasePlayerHp(this.damage);
            return true;
        }
        return false;
    }


    public int getSpeed() {
        return speed;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 총알의 디자인을 그리는 로직을 구현합니다.
        ImageIcon imageIcon = (ImageIcon) getIcon();
        Image image = imageIcon.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    public void setMuzanTarget(Player player) {
        this.muzanTarget = player;
    }
    public boolean hasTravelledMaxDistance() {
        return totalTravelDistance >= maxTravelDistance;
    }

}
