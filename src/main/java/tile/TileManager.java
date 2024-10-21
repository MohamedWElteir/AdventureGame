package tile;

import main.GamePanel;
import utils.ImageLoader;

import java.awt.*;
import java.util.EnumMap;


public class TileManager {
    private GamePanel gp;
    private EnumMap<Tile.TileType, Tile> tiles;

    public TileManager(GamePanel gp){
        this.gp = gp;
        int numberOfTiles = 10;
        tiles = new EnumMap<>(Tile.TileType.class);
        loadTileImages();
    }
    private void loadTileImages(){
        tiles.put(Tile.TileType.GRASS, createTile("/tiles/grass.png", false));
        tiles.put(Tile.TileType.WATER, createTile("/tiles/water.png", true));
        tiles.put(Tile.TileType.WALL, createTile("/tiles/wall.png", true));
    }

    private Tile createTile(String imagePath, boolean collision){
        Tile tile = new Tile();
        tile.image = ImageLoader.loadImage(imagePath);
        tile.collision = collision;
        return tile;
    }


    public void draw(Graphics2D g2){
        int tileSize = gp.tileSize;
        int [][] mapLayout = {
                {0, 0, 0},
                {1, 2, 1},
                {0, 0, 0}
        };

        for(int row = 0; row < mapLayout.length; row++){
            for(int col = 0; col < mapLayout[row].length; col++){
                Tile.TileType tileType = getTileType(mapLayout[row][col]);
                Tile tile = tiles.get(tileType);
                g2.drawImage(tile.image, col*tileSize, row*tileSize, tileSize, tileSize, null);
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
