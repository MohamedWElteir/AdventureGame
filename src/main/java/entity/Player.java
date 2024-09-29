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
    private final int STEPS = 12;
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
        direction = "down";
    }

    public void update(){
        if(keyH.up){
            direction = "up";
            y -= speed;
        }else if(keyH.down){
            direction = "down";
            y += speed;
        }else if(keyH.left){
            direction = "left";
            x -= speed;
        }else if(keyH.right){
            direction = "right";
            x += speed;
        }else{
            return;
        }

        spriteCounter++;
        if(spriteCounter == STEPS){
            if(spriteNum == 1){
                spriteNum = 2;
            }else{
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        if(Objects.equals(direction, "up")){
            if(spriteNum == 1){
                image = up1;
            }else{
                image = up2;
            }
        }else if(Objects.equals(direction, "down")){
            if(spriteNum == 1){
                image = down1;
            }else{
                image = down2;
            }
        }else if(Objects.equals(direction, "left")){
            if(spriteNum == 1){
                image = left1;
            }else{
                image = left2;
            }
        }else if(Objects.equals(direction, "right")){
            if(spriteNum == 1){
                image = right1;
            }else{
                image = right2;
            }
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }


}
