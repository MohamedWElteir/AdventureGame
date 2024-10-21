package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player extends Entity{

    private final GamePanel gp;
    private final KeyHandler keyH;
    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());
    private final EnumMap<Direction, BufferedImage[]> directionImages = new EnumMap<>(Direction.class);

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefault();
        loadPlayerImages();
    }

    private BufferedImage loadImage(String path) throws IOException{
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
    }

    public void loadPlayerImages(){
        try{

            directionImages.put(Direction.UP, new BufferedImage[]{
                    loadImage("/player/boy_up_1.png"),
                    loadImage("/player/boy_up_2.png")
            });

            directionImages.put(Direction.DOWN, new BufferedImage[]{
                    loadImage("/player/boy_down_1.png"),
                    loadImage("/player/boy_down_2.png")
            });

            directionImages.put(Direction.LEFT, new BufferedImage[]{
                    loadImage("/player/boy_left_1.png"),
                    loadImage("/player/boy_left_2.png")
            });

            directionImages.put(Direction.RIGHT, new BufferedImage[]{
                    loadImage("/player/boy_right_1.png"),
                    loadImage("/player/boy_right_2.png")
            });

        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "Error loading player images", e);
        }

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
