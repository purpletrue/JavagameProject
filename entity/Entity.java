package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x;
    public static int y;
    public int hp;
    public int mp;
    public int width;
    public int height;
    public int speed, floor;
    public boolean isRight, isLeft, isMove, seeWhere, isAttack, jumpState;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1, right2,jump1,jump2;
    public String direction;

//    캐릭터 움직임 주기 위해서. player클래스의 sprite~~~()에서 사용
    public int spriteCounter = 0;
    public int spriteNum = 1;

}
