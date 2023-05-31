// 게임 패널

package game;

import entity.Muzan;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
    // TODO: 2023-05-31 사이즈를 이렇게 설정했는데 캐릭터가 픽셀을 넘어간다면 타격 범위는 그대로인가?
    final int originalTileSize = 48; //한 칸 사이즈
    final int scale = 2; //16x3=48 픽셀
    public final int tileSize = originalTileSize * scale;  //최종 결정 타일사이즈
    final int maxScreenCol = 12;  //화면에 표시되는 최대 열,행의 수
    final int maxScreenRow = 9;  //4x3비율
    final int screenWidth = tileSize * maxScreenCol;  //1152: 48x2x12
    final int screenHeight = tileSize * maxScreenRow;  //864
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    Muzan muzan = new Muzan();


//      게임 패널의 초기 설정 수행.
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

//      게임 루프를 실행하는 스레드 시작
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
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
//      플레이어 상태 업데이트
    public void update() {
        player.update();
    }

//    플레이어 그리는 작업 수행.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        muzan.draw(g2);
        g2.dispose();
    }
}
