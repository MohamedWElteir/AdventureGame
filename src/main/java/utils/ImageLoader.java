package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageLoader {

    private static final Logger LOGGER = Logger.getLogger(ImageLoader.class.getName());

    public static BufferedImage loadImage(String path){
        try{
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResourceAsStream(path)));
        }catch(IOException | NullPointerException e){
            LOGGER.log(Level.SEVERE, "Error loading image: " + path, e);
            return null;
        }
    }

    public static BufferedImage[] loadImages(String[] paths){
        BufferedImage[] images = new BufferedImage[paths.length];
        for(int i = 0; i < paths.length; i++){
            images[i] = loadImage(paths[i]);
        }
        return images;
    }
}
