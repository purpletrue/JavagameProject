package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class AutoAttack {
    private EnemyMuzan enemyMuzan;
    private Player playerToFollow;
    private int radius;
    private double velocityX;
    private double velocityY;
    private double x;
    private double y;

    public AutoAttack(EnemyMuzan enemyMuzan, int radius, double velocityX, double velocityY) {
        this.enemyMuzan = enemyMuzan;
        this.radius = radius;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.x = enemyMuzan.getX();
        this.y = enemyMuzan.getY();
    }

    public void update() {
        if (playerToFollow != null) {
            double playerX = playerToFollow.getX(); // Get the player's X coordinate
            double muzanX = enemyMuzan.getX(); // Get the muzan's X coordinate

            double distance = Math.abs(playerX - muzanX); // Calculate the distance between player and muzan

            if (distance <= 500) {
                // Move forward with a speed of 2
                x += velocityX * 2;
            } else {
                // Move forward with a speed of 1
                x += velocityX;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        Ellipse2D.Double circle = new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2);
        g2.fill(circle); // Draw the sphere as a filled circle
    }
}

