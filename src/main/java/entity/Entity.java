package entity;

import java.awt.image.BufferedImage;

public class Entity {
    protected   int entityX, entityY;
    protected   int speed;

    protected   BufferedImage currentImage;
    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    protected  Direction currentDirection;



    protected  int stepCount;
    protected  int stepLimit;
    protected  boolean spriteMoved;

}
