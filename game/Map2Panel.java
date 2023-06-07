

// Map1 클리어 후 이동하는 Map2의 코드입니다.


//package game;
//
//import entity.Muzan;
//import entity.PlayerU;
//import entity.PlayerY;
//import entity.PlayerM;
//import entity.Platform;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//
//public class Map2Panel extends Gamepanel implements Runnable {
//
//    public Map2Panel(GameFrame gameFrame, int characterType) {
//        this.gameFrame = gameFrame;
//        this.characterType = characterType;
//        setFocusable(true);
//        setVisible(true);
//
//        keyH = new KeyHandler();  // keyH 객체 초기화
//        gameFrame.addKeyListener(keyH);  // gameFrame에 KeyListener 등록
//        addKeyListener(keyH);    // keyH 객체를 리스너로 추가
//        requestFocusInWindow();  // 포커스 요청
//
//        // Player 객체 생성
//        switch (characterType) {
//            case 0:
//                playerU = new PlayerU(this, keyH);
//                break;
//            case 1:
//                playerY = new PlayerY(this, keyH);
//                break;
//            case 2:
//                playerM = new PlayerM(this, keyH);
//                break;
//        }
//        muzan = new Muzan();
//        setPreferredSize(new Dimension(screenWidth, screenHeight));
//
//        // 발판 생성
//        BufferedImage platformImage = new BufferedImage(tileSize * 2, tileSize, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2 = platformImage.createGraphics();
//        g2.setColor(Color.GRAY);
//        g2.fillRect(0, 0, tileSize * 2, tileSize);
//        g2.dispose();
//
//        platform1 = new Platform(200, 400, platformImage);
//    }
//
//
//}