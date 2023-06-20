package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 50;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 1.0f * Game.SCALE;
    private int[][] lvlData;
    //히트박스
    private float xDrawOffset = 5 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    //점핑,중력
    private float airSpeed = 0f;
    private float gravity =0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    private boolean lastDirectionRight = true;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y,(int) (20 * Game.SCALE), (int) (27 * Game.SCALE));
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();

    }
    public void render(Graphics g, int lvlOffset) {
        Graphics2D g2d = (Graphics2D) g;

        BufferedImage currentFrame = animations[playerAction][aniIndex];
        int drawX = (int) (hitbox.x - xDrawOffset) - lvlOffset;
        int drawY = (int) (hitbox.y - yDrawOffset);

        if (!lastDirectionRight) {
            // AffineTransform for flipping the image horizontally
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-currentFrame.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            currentFrame = op.filter(currentFrame, null);

            // adjust drawX because the image is flipped
            drawX = drawX - currentFrame.getWidth();
        }

        g2d.drawImage(currentFrame, drawX, drawY, width, height, null);
    }



    private void updateAnimationTick() {

        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }
    private void setAnimation() {
        int startAni = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (inAir) {
            if (airSpeed <0 )
                playerAction = JUMP;
            else
                playerAction = FALLING;
        }

        if (attacking)
            playerAction = ATTACK_1;

        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;

        if (jump)
            jump();

        if (!inAir)
            if ((!left && !right) || (right && left))
                return;
        if (inAir) {
            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;
        }

        float xSpeed = 0;

        if (left) {
            xSpeed -= playerSpeed;
            lastDirectionRight = false;
        }
        if (right) {
            xSpeed += playerSpeed;
            lastDirectionRight = true;
        }
        if (inAir)
            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;

        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }
        } else {
            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;
            else
                updateXPos(xSpeed);
        }
        moving = true;

    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;

        } else {
            hitbox.x = GetEntityPosNextToWall(hitbox, xSpeed);
        }

    }

    private void loadAnimations() {

        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 32, j*32, 32, 32);

    }
    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
    }
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}