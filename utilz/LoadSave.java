package utilz;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "res/player.png";
    public static final String LEVEL_ATLAS = "res/map.png";
    public static final String LEVEL_ONE_DATA = "res/level_one_data.png";
    public static final String MENU_BUTTONS = "res/버튼.png";
    public static final String MENU_BACKGROUND = "res/메뉴.png";
    public static final String MENU_BACKGROUND_IMG = "res/고양이.png";
    public static final String PLAYING_BG_IMG = "res/배경.png";



    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;

    }

   public static int[][] GetLevelData() {
       BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
       int[][] lvlData = new int[img.getHeight()][img.getWidth()];


       for (int j = 0; j < img.getHeight(); j++)
           for (int i = 0; i < img.getWidth(); i++) {
            Color color = new Color(img.getRGB(i, j));
               int value = color.getRed();
               if (value >= 48)
                    value = 0;
                lvlData[j][i] = value;
            }
       return lvlData;

    }
}



