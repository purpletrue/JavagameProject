

// 게임 시작 후 첫번째 맵의 코드입니다.


package game;

import entity.*;

import javax.swing.*;
import java.awt.*;

public class Map1Panel extends GamePanel implements Runnable {

    public Map1Panel(GameFrame gameFrame, int characterType) {
        super(gameFrame);
        this.characterType = characterType;
        this.mapNumber = 1; // Map1Panel은 mapNumber를 1로 설정

        keyH = new KeyHandler();  // keyH 객체 초기화
        gameFrame.addKeyListener(keyH);  // gameFrame에 KeyListener 등록
        addKeyListener(keyH);    // keyH 객체를 리스너로 추가

        muzan = new EnemyMuzan(this);

        // Player 객체 생성
        switch (characterType) {
            case 0:
                playerU = new PlayerU(this, keyH, x, y, width, height, muzan);
                setMuzanTarget(playerU);
                break;
            case 1:
                playerY = new PlayerY(this, keyH, x, y, width, height, muzan);
                break;
            case 2:
                playerM = new PlayerM(this, keyH, x, y, width, height, muzan);
                break;
        }
//        setMuzanTarget(playerY);
//        setMuzanTarget(playerM);

    }


    private void setMuzanTarget(Player player) {
        muzan.setPlayer(player);
    }
}