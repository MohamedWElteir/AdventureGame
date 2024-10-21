package main;


import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure");

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);

        window.pack(); //make the window size fit the preferred size and layouts of its subcomponents
    
        window.setLocationRelativeTo(null); // at the center of the screen
        window.setVisible(true);

        gamepanel.startGameThread();
    }
}