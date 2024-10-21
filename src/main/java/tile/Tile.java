package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;

    public enum TileType{
        GRASS,
        WATER,
        WALL
    };
}
