package tile;

import main.GamePanel;
import utils.ImageLoader;
import utils.TextLoader;

import java.awt.*;
import java.util.EnumMap;


public class TileManager {
    private final GamePanel GP;
    private final EnumMap<Tile.TileType, Tile> TILES;
    private int[][] tileMap;

    public static final int WORLD_MAX_ROW = 50;
    public static final int WORLD_MAX_COL = 50;

    public TileManager(GamePanel gp){
        this.GP = gp;
        TILES = new EnumMap<>(Tile.TileType.class);
        tileMap = new int[GP.MAX_SCREEN_COL][GP.MAX_SCREEN_ROW];
        loadTileImages();
        loadTilesMap();
    }
    private void loadTileImages(){

        for(Tile.TileType type: Tile.TileType.values()){
            TILES.put(type, createTile("/tiles/" + type.name().toLowerCase() + ".png", type != Tile.TileType.GRASS));
        }
//        tiles.put(Tile.TileType.GRASS, createTile("/tiles/grass.png", false));
//        tiles.put(Tile.TileType.EARTH, createTile("/tiles/earth.png", false));
//        tiles.put(Tile.TileType.SAND, createTile("/tiles/sand.png", false));
//        tiles.put(Tile.TileType.WATER, createTile("/tiles/water.png", true));
//        tiles.put(Tile.TileType.WALL, createTile("/tiles/wall.png", true));
//        tiles.put(Tile.TileType.TREE, createTile("/tiles/tree.png", true));
    }

    private void loadTilesMap(){
        tileMap = TextLoader.loadMap(GP.MAX_SCREEN_ROW, GP.MAX_SCREEN_COL, "/maps/mapLayout.txt");
    }

    private Tile createTile(String imagePath, boolean collision){
        Tile tile = new Tile();
        tile.image = ImageLoader.loadImage(imagePath);
        tile.collision = collision;
        return tile;
    }


    public void draw(Graphics2D g2){


       for(int row = 0; row < GP.MAX_SCREEN_ROW; row++){
           for(int col = 0; col < GP.MAX_SCREEN_COL; col++){
               Tile tile = TILES.get(Tile.TileType.fromId(tileMap[col][row]));
               g2.drawImage(tile.image, col * GP.TILE_SIZE, row * GP.TILE_SIZE, GP.TILE_SIZE, GP.TILE_SIZE, null);
           }
       }

    }

}
