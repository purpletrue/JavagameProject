package game;

import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    protected int mapNumber;
    protected int characterType;
    int x, y, width, height;

    protected GameFrame gameFrame;
    protected final int originalTileSize = 48;
    protected final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    protected final int maxScreenCol = 12;
    protected final int maxScreenRow = 9;
    protected final int screenWidth = tileSize * maxScreenCol;
    protected final int screenHeight = tileSize * maxScreenRow;
    protected int FPS = 60;
    protected KeyHandler keyH = new KeyHandler();
    protected Thread gameThread;
    protected PlayerU playerU;
    protected PlayerY playerY;
    protected PlayerM playerM;
    protected EnemyMuzan muzan;

    public GamePanel() {
        // 발판 생성
        int tileSize = 32; // 발판의 크기
        int totalTiles = 1152 / tileSize; // 패널 가로 크기를 발판의 크기로 나눈 값

        int yPosition = 700; // y 좌표

        BufferedImage platformImage = new BufferedImage(tileSize * 2, tileSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = platformImage.createGraphics();
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, tileSize * 2, tileSize);
        g2.dispose();
    }


    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public void setCharacterType(int characterType) {
        this.characterType = characterType;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        switch (characterType) {
            case 0 -> playerU.update();
            case 1 -> playerY.update();
            case 2 -> playerM.update();
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        switch (characterType) {
            case 0 -> playerU.draw(g2);
            case 1 -> playerY.draw(g2);
            case 2 -> playerM.draw(g2);
        }
        muzan.draw(g2);
        g2.dispose();
    }

    private void drawBackground(Graphics2D g2) {
        ImageIcon backgroundIcon;
        switch (mapNumber) {
            case 1 -> backgroundIcon = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\test.png");
            case 2 -> backgroundIcon = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\backgroundG2.png");
            case 3 -> backgroundIcon = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\backgroundG3.png");
            default -> backgroundIcon = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\background1.png");
        }
        Image backgroundImage = backgroundIcon.getImage();
        g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

