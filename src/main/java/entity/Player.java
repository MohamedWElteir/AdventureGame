package entity;
import tile.TileManager;
import utils.ImageLoader;

import main.GamePanel;
import Handlers.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

public class Player extends Entity{

    private final GamePanel gamePanel;
    private final KeyHandler keyHandlerObject;
    private final int SCREEN_X, SCREEN_Y;
    private final EnumMap<Direction, BufferedImage[]> DIRECTION_IMAGES = new EnumMap<>(Direction.class);



    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandlerObject = keyHandler;

        this.SCREEN_X = this.gamePanel.SCREEN_WIDTH / 2 - (this.gamePanel.TILE_SIZE / 2); // center of the screen - half player size
        this.SCREEN_Y = this.gamePanel.SCREEN_HEIGHT / 2 - (this.gamePanel.TILE_SIZE / 2);

        setDefault();
        loadPlayerImages();
    }



    public void loadPlayerImages(){
        DIRECTION_IMAGES.put(Direction.UP, ImageLoader.loadImages(new String[]{
                "/player/boy_up_1.png",
                "/player/boy_up_2.png"
        }));

        DIRECTION_IMAGES.put(Direction.DOWN, ImageLoader.loadImages(new String[]{
                "/player/boy_down_1.png",
                "/player/boy_down_2.png"
        }));

        DIRECTION_IMAGES.put(Direction.LEFT, ImageLoader.loadImages(new String[]{
                "/player/boy_left_1.png",
                "/player/boy_left_2.png"
        }));

        DIRECTION_IMAGES.put(Direction.RIGHT, ImageLoader.loadImages(new String[]{
                "/player/boy_right_1.png",
                "/player/boy_right_2.png"
        }));

    }

    private void setDefault(){
        entityX = gamePanel.TILE_SIZE * (TileManager.WORLD_MAX_ROW / 2);
        entityY = gamePanel.TILE_SIZE * (TileManager.WORLD_MAX_COL / 2);

        speed = 3;

        currentDirection = Direction.DOWN;

        stepCount = 0;
        stepLimit = 12;

        currentImage = null;
    }
    private boolean updatePlayerDirection(){
        Direction newDirection = null;
        int currentSpeed = speed + (keyHandlerObject.speedUp ? 1 : 0);

        if (keyHandlerObject.up) {
            entityY -= currentSpeed;
            newDirection = Direction.UP;
        } else if (keyHandlerObject.down) {
            entityY += currentSpeed;
            newDirection = Direction.DOWN;
        } else if (keyHandlerObject.left) {
            entityX -= currentSpeed;
            newDirection = Direction.LEFT;
        } else if (keyHandlerObject.right) {
            entityX += currentSpeed;
            newDirection = Direction.RIGHT;
        }

        if (newDirection != null) {
            currentDirection = newDirection;
            return true;
        }

        return false;
    }

    public void update(){
        if(!updatePlayerDirection()) return;

        stepCount++;

        if(stepCount == stepLimit){
            spriteMoved = !spriteMoved;
            stepCount = 0;
        }
    }


    private void getCurrentImage(){
        BufferedImage[] images = DIRECTION_IMAGES.get(currentDirection);
        if(images != null){
            currentImage = spriteMoved ? images[0] : images[1];
        }
    }
    public void draw(Graphics2D g2){
        getCurrentImage();

        g2.drawImage(currentImage, SCREEN_X, SCREEN_Y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }


}
