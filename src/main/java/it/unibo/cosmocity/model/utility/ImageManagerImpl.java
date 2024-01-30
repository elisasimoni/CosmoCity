package it.unibo.cosmocity.model.utility;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;
import javafx.scene.image.Image;

public class ImageManagerImpl implements ImageManager{
    

    public Image loadImage(String path) {
        try {
            
            InputStream inputStream = ImageManagerImpl.class.getResourceAsStream("/it/unibo/asset/img/"+path);
            
            if (inputStream != null) {
                return new Image(inputStream);
            } else {
                System.err.println("File non trovato: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
