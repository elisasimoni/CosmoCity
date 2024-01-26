package it.unibo.cosmocity.model.utility;

import java.net.URI;
import java.nio.file.Paths;
import javafx.scene.image.Image;

public class ImageManagerImpl implements ImageManager{
    

    public Image loadImage(String path) {
        try {
            String absolutePath = Paths.get("").toAbsolutePath().toString();
            URI uri = Paths.get(absolutePath,"/src/main/java/it/unibo/resources/", path).toUri();
            return new Image(uri.toString());

        } catch (Exception e) {
            System.err.println("Errore durante la riproduzione audio: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
