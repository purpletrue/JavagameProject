// 게임 화면 전체의 부모 클래스
// 게임 화면 Map1,Map2,Map3으로 나뉠 것.

package game;

import javax.swing.*;
import entity.Muzan;
import entity.PlayerU;
import entity.PlayerY;
import entity.PlayerM;
import entity.Platform;
import java.awt.*;

        
public class GamePanel extends JPanel implements Runnable {
    Map1Panel parent;
    int characterType;

    GameFrame gameFrame;
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
   PlayerU playerU;
    PlayerY playerY;
   PlayerM playerM;
    Muzan muzan;
    Platform platform1;

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