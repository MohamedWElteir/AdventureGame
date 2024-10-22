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

    private final int worldMaxRow = 60;
    private final int worldMaxCol = 60;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new EnumMap<>(Tile.TileType.class);
        tileMap = new int[gp.maxScreenCol][gp.maxScreenRow];
        loadTileImages();
        loadTilesMap();
    }
    private void loadTileImages(){

        for(Tile.TileType type: Tile.TileType.values()){
            tiles.put(type, createTile("/tiles/" + type.name().toLowerCase() + ".png", type != Tile.TileType.GRASS));
        }
//        tiles.put(Tile.TileType.GRASS, createTile("/tiles/grass.png", false));
//        tiles.put(Tile.TileType.EARTH, createTile("/tiles/earth.png", false));
//        tiles.put(Tile.TileType.SAND, createTile("/tiles/sand.png", false));
//        tiles.put(Tile.TileType.WATER, createTile("/tiles/water.png", true));
//        tiles.put(Tile.TileType.WALL, createTile("/tiles/wall.png", true));
//        tiles.put(Tile.TileType.TREE, createTile("/tiles/tree.png", true));
    }

    private void loadTilesMap(){
        tileMap = TextLoader.loadMap(gp.maxScreenRow, gp.maxScreenCol, "/maps/mapLayout.txt");
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
               Tile tile = tiles.get(Tile.TileType.fromId(tileMap[col][row]));
               g2.drawImage(tile.image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
           }
       }

    }

}
