package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private  Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
//        levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
//        levelOne = new Level(LoadSave.GetLevelData());

    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + 1;
                levelSprite[index] = img.getSubimage(i * 20, j * 20, 20, 20);
                
            }
            
        }


    public void draw(Graphics g) {
//        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
//            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
//                int index = levelOne.getSpriteIndex(i,j);
                g.drawImage(levelSprite[1], 0, 0, null);
            }





    public void update() {

    }
}
