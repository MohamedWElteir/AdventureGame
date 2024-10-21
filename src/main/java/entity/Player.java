package entity;
import utils.ImageLoader;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

public class Player extends Entity{

    private final GamePanel gp;
    private final KeyHandler keyH;
    private final EnumMap<Direction, BufferedImage[]> directionImages = new EnumMap<>(Direction.class);

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefault();
        loadPlayerImages();
    }



    public void loadPlayerImages(){
        directionImages.put(Direction.UP, ImageLoader.loadImages(new String[]{
                "/player/boy_up_1.png",
                "/player/boy_up_2.png"
        }));

        directionImages.put(Direction.DOWN, ImageLoader.loadImages(new String[]{
                "/player/boy_down_1.png",
                "/player/boy_down_2.png"
        }));

        directionImages.put(Direction.LEFT, ImageLoader.loadImages(new String[]{
                "/player/boy_left_1.png",
                "/player/boy_left_2.png"
        }));

        directionImages.put(Direction.RIGHT, ImageLoader.loadImages(new String[]{
                "/player/boy_right_1.png",
                "/player/boy_right_2.png"
        }));

    }

    private void setDefault(){
        x = 100;
        y = 100;
        speed = 3;
        currentDirection = Direction.DOWN;
        stepCount = 0;
        stepLimit = 12;
        currentImage = null;
    }
    private boolean updatePlayerDirection(){
        Direction newDirection = null;
        int currentSpeed = speed + (keyH.speedUp ? 1 : 0);

        if (keyH.up) {
            y -= currentSpeed;
            newDirection = Direction.UP;
        } else if (keyH.down) {
            y += currentSpeed;
            newDirection = Direction.DOWN;
        } else if (keyH.left) {
            x -= currentSpeed;
            newDirection = Direction.LEFT;
        } else if (keyH.right) {
            x += currentSpeed;
            newDirection = Direction.RIGHT;
        }

        if (newDirection != null) {
            currentDirection = newDirection;
            return true;
        }

        return false;
    }

    public void update(){
        boolean moved = updatePlayerDirection();
        if(!moved) return;

        stepCount++;

        if(stepCount == stepLimit){
            spriteMoved = !spriteMoved;
            stepCount = 0;
        }
    }


    private void getCurrentImage(){
        BufferedImage[] images = directionImages.get(currentDirection);
        if(images != null){
            currentImage = spriteMoved ? images[0] : images[1];
        }
    }
    public void draw(Graphics2D g2){
        getCurrentImage();

        g2.drawImage(currentImage, x, y, gp.tileSize, gp.tileSize, null);
    }


}
