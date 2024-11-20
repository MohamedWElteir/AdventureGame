package entity;

import java.awt.image.BufferedImage;

abstract class Entity {
    protected   int entityX;
    protected int entityY;
    protected   int speed;
    protected  int stepCount;
    protected  int stepLimit;
    protected  boolean spriteMoved;
    protected   BufferedImage currentImage;
    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    protected  Direction currentDirection;


}
