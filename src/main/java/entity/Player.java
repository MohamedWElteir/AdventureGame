package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefault();
        getPlayerImage();
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));

            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setDefault(){
        x = 100;
        y = 100;
        speed = 4;
        currentDirection = Direction.DOWN;
        limit = 12;
        currentImage = null;
    }
    public boolean updatePlayerDirection(){
        if(keyH.up){
            y -= speed;
            currentDirection = Direction.UP;
            return true;
        }
        if(keyH.down){
           y += speed;
            currentDirection = Direction.DOWN;
            return true;
        }
        if(keyH.left){
            x -= speed;
            currentDirection = Direction.LEFT;
            return true;
        }
        if(keyH.right){
            x += speed;
            currentDirection = Direction.RIGHT;
            return true;
        }
//        return Direction.DOWN;
        return false;
    }

    public void update(){
        boolean moved = updatePlayerDirection();
        if(!moved) return;

        spriteSteps++;

        if(spriteSteps == limit){
            spriteMoved = !spriteMoved;
            spriteSteps = 0;
        }
    }

    public void moveUp(){
        if(spriteMoved){
            currentImage = up1;
        }else{
            currentImage = up2;
        }
    }

    public void moveDown(){
        if(spriteMoved){
            currentImage = down1;
        }else{
            currentImage = down2;
        }
    }

    public void moveLeft(){
        if(spriteMoved){
            currentImage = left1;
        }else{
            currentImage = left2;
        }
    }

    public void moveRight(){
        if(spriteMoved){
            currentImage = right1;
        }else{
            currentImage = right2;
        }
    }

    public void draw(Graphics2D g2){

        if(currentDirection.equals(Direction.UP)){
            moveUp();
        }else if(currentDirection.equals(Direction.DOWN)){
            moveDown();
        }else if(currentDirection.equals(Direction.LEFT)){
            moveLeft();
        }else if(currentDirection.equals(Direction.RIGHT)){
            moveRight();
        }
        g2.drawImage(currentImage, x, y, gp.tileSize, gp.tileSize, null);
    }


}
