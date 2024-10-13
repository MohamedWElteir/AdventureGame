package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, currentImage;
    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    public Direction currentDirection;



    public int spriteSteps = 0;
    public int limit;
    public boolean spriteMoved;

}
