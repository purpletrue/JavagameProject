package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

//    downPressed 뺌
    public boolean upPressed, leftPressed, rightPressed;
//    x:기본공격,  스페이스:점프   1,2,3:스킬
    public boolean xPressed, spaceBarPressed, onePressed, twoPressed,threePressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
//        if (code == KeyEvent.VK_UP) {
//            upPressed = true;
//        }
//        if (code == KeyEvent.VK_DOWN) {
//            downPressed = true;
//        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_X) {
            xPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spaceBarPressed = true;
        }
        if (code == KeyEvent.VK_1) {
            onePressed = true;
        }
        if (code == KeyEvent.VK_2) {
            twoPressed = true;
        }
        if (code == KeyEvent.VK_3) {
            threePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
//        if (code == KeyEvent.VK_UP) {
//            upPressed = false;
//        }
//        if (code == KeyEvent.VK_DOWN) {
//            downPressed = false;
//        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_X) {
            xPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spaceBarPressed = false;
        }
        if (code == KeyEvent.VK_1) {
            onePressed = false;
        }
        if (code == KeyEvent.VK_2) {
            twoPressed = false;
        }
        if (code == KeyEvent.VK_3) {
            threePressed = false;
        }
    }
}
