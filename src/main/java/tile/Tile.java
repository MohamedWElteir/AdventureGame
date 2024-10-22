package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;

    public enum TileType{
        GRASS(0),
        WATER(1),
        WALL(2),
        EARTH(3),
        SAND(4),
        TREE(5);

        private final int id;

        TileType(int id){
            this.id = id;
        }

        public int getId(){
            return id;
        }

        public static TileType fromId(int id){
            for(TileType type: values()) {
                if (type.getId() == id) return type;
            }
            return GRASS;
        }
    }
}
