package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

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

        int playerX = 100;
        int playerY = 100;
        int playerSpeed = 4;

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
//    @Override
//    public void run() {
//           double drawInterval = (double) 1_000_000_000 / FPS;
//           double nextDraw = System.nanoTime() + drawInterval;
//           long start = System.nanoTime();
//           long current;
//           long timer = 0;
//           long counter = 0;
//            while(gameThread != null){
//                current = System.nanoTime();
//                update();
//
//                repaint();
//                counter++;
//                timer += (current - start);
//                start = current;
//
//                try {
//                    double remainingTime = nextDraw - System.nanoTime();
//                    remainingTime /= 1_000_000; // convert to milli
//                    if(remainingTime < 0){
//                        remainingTime = 0;
//                    }
//                    Thread.sleep((long)remainingTime);
//                    nextDraw += drawInterval;
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                if(timer >= 1_000_000_000){
//                    timer = 0;
//                    System.out.println("FPS: " + counter);
//                    counter = 0;
//
//                }
//
//            }
//    }


    @Override
    public void run(){

            double drawInterval = (double) 1_000_000_000 / FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;
            long drawCounter = 0;

            while(gameThread != null){

                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
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
