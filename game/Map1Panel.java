package game;

import entity.Muzan;
import entity.PlayerU;
import entity.PlayerY;
import entity.PlayerM;
import entity.Platform;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Map1Panel extends JPanel implements Runnable {
    private Map1Panel parent;
    private int characterType;

    private GameFrame gameFrame;
    final int originalTileSize = 48;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 12;
    final int maxScreenRow = 9;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    private PlayerU playerU;
    private PlayerY playerY;
    private PlayerM playerM;
    Muzan muzan;
    Platform platform1;

    public Map1Panel(GameFrame gameFrame, int characterType) {
        this.gameFrame = gameFrame;
        this.characterType = characterType;
        setFocusable(true);
        setVisible(true);

        keyH = new KeyHandler();  // keyH 객체 초기화
        gameFrame.addKeyListener(keyH);  // gameFrame에 KeyListener 등록
        addKeyListener(keyH);    // keyH 객체를 리스너로 추가
        requestFocusInWindow();  // 포커스 요청

        // Player 객체 생성
        switch (characterType) {
            case 0:
                playerU = new PlayerU(this, keyH);
                break;
            case 1:
                playerY = new PlayerY(this, keyH);
                break;
            case 2:
                playerM = new PlayerM(this, keyH);
                break;
        }
        muzan = new Muzan();
        setPreferredSize(new Dimension(screenWidth, screenHeight));

        // 발판 생성
        BufferedImage platformImage = new BufferedImage(tileSize * 2, tileSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = platformImage.createGraphics();
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, tileSize * 2, tileSize);
        g2.dispose();

        platform1 = new Platform(200, 400, platformImage);
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
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        switch (characterType) {
            case 0:
                playerU.update();
                muzan.moveForwardAndBackward();
                break;
            case 1:
                playerY.update();
                muzan.moveForwardAndBackward();
                break;
            case 2:
                playerM.update();
                muzan.moveForwardAndBackward();
                break;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        switch (characterType) {
            case 0:
                playerU.draw(g2);
                break;
            case 1:
                playerY.draw(g2);
                break;
            case 2:
                playerM.draw(g2);
                break;
        }
        muzan.draw(g2);
        platform1.draw(g2);
        g2.dispose();
    }
}