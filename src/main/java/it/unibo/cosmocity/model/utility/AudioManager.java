package it.unibo.cosmocity.model.utility;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.net.URI;
import java.nio.file.Paths;
import java.io.File;

public class AudioManager {

    private MediaPlayer mediaPlayer;

    public void play(String path) {
         try {
            String absolutePath = Paths.get("").toAbsolutePath().toString();
            URI uri = Paths.get(absolutePath,"/src/main/java/it/unibo/resources/" ,path).toUri();
            Media sound = new Media(uri.toString());
            this.mediaPlayer = new MediaPlayer(sound);
            this.mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Errore durante la riproduzione audio: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stop() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }
    }

}
