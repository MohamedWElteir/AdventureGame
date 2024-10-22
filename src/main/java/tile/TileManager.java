package tile;

import main.GamePanel;
import utils.ImageLoader;
import utils.TextLoader;

import java.awt.*;
import java.util.EnumMap;


public class TileManager {
    private final GamePanel gp;
    private final EnumMap<Tile.TileType, Tile> tiles;
    private int[][] tileMap;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new EnumMap<>(Tile.TileType.class);
        tileMap = new int[gp.maxScreenRow][gp.maxScreenCol];
        loadTileImages();
        loadTilesMap();
    }
    private void loadTileImages(){
        tiles.put(Tile.TileType.GRASS, createTile("/tiles/grass.png", false));
        tiles.put(Tile.TileType.WATER, createTile("/tiles/water.png", true));
        tiles.put(Tile.TileType.WALL, createTile("/tiles/wall.png", true));
    }

    private void loadTilesMap(){
        tileMap = TextLoader.loadTilesMap(gp,"/maps/mapLayout.txt");
    }

    private Tile createTile(String imagePath, boolean collision){
        Tile tile = new Tile();
        tile.image = ImageLoader.loadImage(imagePath);
        tile.collision = collision;
        return tile;
    }


    public void draw(Graphics2D g2){


       for(int row = 0; row < gp.maxScreenRow; row++){
           for(int col = 0; col < gp.maxScreenCol; col++){
               Tile tile = tiles.get(getTileType(tileMap[col][row]));
               g2.drawImage(tile.image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
           }
       }

    }

    private Tile.TileType getTileType(int index){
        return switch (index) {
            case 1 -> Tile.TileType.WALL;
            case 2 -> Tile.TileType.WATER;
            default -> Tile.TileType.GRASS;
        };
    }

}
