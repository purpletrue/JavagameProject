package utilz;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "res/tanjiro.png";
    public static final String LEVEL_ATLAS = "res/block.png";
//    public static final String LEVEL_ONE_DATA = "res/.png";



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
//    public static int[][] GetLevelData() {
//        int[][] lvData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
//        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
//
//        for (int j = 0; j < img.getHeight(); j++)
//            for (int i = 0; i < img.getWidth(); i++) {
//                Color color = new Color(img.getRGB(i, j));
//                int
//
//                lvData[j][i] = color.getRGB();
//
//            }
//        return lvData;

        }



