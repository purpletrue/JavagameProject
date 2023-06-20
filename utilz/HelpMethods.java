package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x,y, lvlData))
            if (!IsSolid(x + width, y + height, lvlData))
                if (!IsSolid(x + width, y, lvlData))
                    if (!IsSolid(x,y+height, lvlData))
                        return true;
        return false;

    }
    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        if (x<0||x>= maxWidth)
            return true;
        if (y<0||y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value >= 48 || value<0 || value != 11)
            return true;
        return false;
    }

    public static float GetEntityPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            //오른쪽
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else
            //왼쪽
            return currentTile * Game.TILES_SIZE;
        }
        public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
            int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
            if (airSpeed > 0) {
                //Falling
                int tileYPos = currentTile * Game.TILES_SIZE;
                int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
                return tileYPos + yOffset -1;
            } else
                //Jumping
                return currentTile * Game.TILES_SIZE;


        }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        //체크 픽셀 아래 버튼왼쪽 그리고 버튼 오른쪽
        if(!IsSolid(hitbox.x, hitbox.y + hitbox.height+1, lvlData))
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y+hitbox.height+1,lvlData))
                return false;
        return true;
    }

    }

