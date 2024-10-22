package utils;

import main.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextLoader {

    private static final Logger LOGGER = Logger.getLogger(TextLoader.class.getName());


    public static int[][] loadMap(int worldMaxRow, int worldMaxCol, String path){
        int[][] map = new int[worldMaxCol][worldMaxRow];

        try{
            InputStream inputStream = TextLoader.class.getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for(int row = 0; row < worldMaxRow; row++){
                String[] tileNumbers = bufferedReader.readLine().split(" ");
                for(int col = 0; col < worldMaxCol; col++){
                    map[col][row] = Integer.parseInt(tileNumbers[col]);
                }
            }
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, "Error loading world map: "+ path, e);
        }

        return map;
    }
}
