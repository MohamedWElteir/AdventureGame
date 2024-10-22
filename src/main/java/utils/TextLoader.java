package utils;

import main.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextLoader {

    private static final Logger LOGGER = Logger.getLogger(TextLoader.class.getName());


    public static int[][] loadTilesMap(GamePanel gp, String path){
        int[][] tilesMap = new int[gp.maxScreenCol][gp.maxScreenRow];

        try{
            InputStream inputStream = TextLoader.class.getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for(int row = 0; row < gp.maxScreenRow; row++){

                String mapLine = bufferedReader.readLine();
                String[] tileNumbers = mapLine.split(" ");

                for(int col = 0; col < gp.maxScreenCol; col++){

                    int tileNumber = Integer.parseInt(tileNumbers[col]);
                    tilesMap[col][row] = tileNumber;
                }
            }
            bufferedReader.close();
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, "Error loading map tiles: "+ path, e);
        }

        return tilesMap;
    }
}
