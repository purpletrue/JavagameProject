package game;

import entity.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map1Panel extends GamePanel implements Runnable {

    public Map1Panel(GameFrame gameFrame, int characterType) {
        this.gameFrame = gameFrame;
        this.characterType = characterType;
        this.mapNumber = 1; // Map1Panel은 mapNumber를 1로 설정
        setFocusable(true);
        setVisible(true);

        keyH = new KeyHandler();  // keyH 객체 초기화
        gameFrame.addKeyListener(keyH);  // gameFrame에 KeyListener 등록
        addKeyListener(keyH);    // keyH 객체를 리스너로 추가
        requestFocusInWindow();  // 포커스 요청

        // Player 객체 생성
        switch (characterType) {
            case 0 -> playerU = new PlayerU(this, keyH,x, y, width, height);
            case 1 -> playerY = new PlayerY(this, keyH,x, y, width, height);
            case 2 -> playerM = new PlayerM(this, keyH,x, y, width, height);
        }
        muzan = new EnemyMuzan();
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }
    private void setMuzanTarget(Player player) {
        muzan.setPlayer(player);
    }
}
