package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// works as a game screen
public class GamePanel extends JPanel implements Runnable{
    // screen settings
        final int originalTileSize = 16; // 16 * 16 tile (size of characters npcs and map in this game)
        final int scale = 3; //scale the tile size

        public final int tileSize = originalTileSize * scale; // 48 * 48
        final int maxScreenCol = 16;
        final int maxScreenRow = 12;
        final int screenWidth = tileSize * maxScreenCol; // 768 px
        final int screenHeight = tileSize * maxScreenRow; // 576 px
        int FPS = 60;

        KeyHandler KeyH = new KeyHandler();

        Thread gameThread;
        Player player = new Player(this, KeyH);


        public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.BLACK);
            this.setDoubleBuffered(true); // improve game's rendering performance
            this.addKeyListener(KeyH);
            this.setFocusable(true); // focus to receive key inputs
        }


        public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();
        }


    @Override
    public void run(){

            double drawInterval = (double) 1_000_000_000 / FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;
            long drawCounter = 0;
            long diff;

            while(gameThread != null){

                currentTime = System.nanoTime();
                diff = currentTime - lastTime;
                delta += diff / drawInterval;
                timer += diff;
                lastTime = currentTime;


                if(delta >= 1){
                    update();
                    repaint();
                    delta--;
                    drawCounter++;
                }

                if(timer >= 1_000_000_000){
                    System.out.println("FPS: "+ drawCounter);
                    drawCounter = 0;
                    timer = 0;
                }
            }
    }

    public void update(){
            player.update();
    }

    public void paintComponent(Graphics g){
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;

            player.draw(g2);

            g2.dispose(); //save some resources


    }
}
