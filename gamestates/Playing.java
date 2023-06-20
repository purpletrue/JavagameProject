package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Playing extends State implements Statemethods {
    private Player player;
    private LevelManager levelManager;
    private int xLvlOffset;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);

    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;

    private BufferedImage backgroundImg;

    public Playing(Game game) {
        super(game);
        initClasses();

        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
    }
    private void initClasses() {
        levelManager= new LevelManager(game);
        player = new Player(200, 200, (int)(32 * Game.SCALE),(int)(32 * Game.SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }


    @Override
    public void update() {
        levelManager.update();
        player.update();
        checkCloseToBorder();
        checkEndOfLevel();
    }

    private void checkEndOfLevel() {
        if (xLvlOffset >= maxLvlOffsetX) {
            JOptionPane.showMessageDialog(null, "네즈코와 탄지로는 행복하게 잘 살았습니다.", "Alert", JOptionPane.INFORMATION_MESSAGE);
            Gamestate.state = Gamestate.MENU;
        }
    }



    private void checkCloseToBorder() {
        int playerX = (int) player.getHitbox().x;
        int diff = playerX - xLvlOffset;

        if (diff > rightBorder)
            xLvlOffset += diff - rightBorder;
        else if (diff < leftBorder)
        xLvlOffset += diff - leftBorder;

        if (xLvlOffset > maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0)
            xLvlOffset = 0;




    }
//    private void checkCloseToBorder() {
//        int playerx = (int) player.getHitbox().x;
//        int diff = playerx - xLvlOffset;
//
//        if (diff > rightBorder)
//            xLvlOffset += diff - rightBorder;
//        else if (diff < leftBorder)
//            xLvlOffset += diff = leftBorder;
//
//        if (xLvlOffset > maxLvlOffsetX)
//            xLvlOffset > maxLvlOffsetX;
//        else if (xLvlOffset < 0)
//        xLvlOffset = 0;

//    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);



    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            player.setAttacking(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_A:
               player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state = Gamestate.MENU;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_A:
                player.setLeft(false);
                break;

            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }

    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
    public Player getPlayer() {
        return player;
    }
}
