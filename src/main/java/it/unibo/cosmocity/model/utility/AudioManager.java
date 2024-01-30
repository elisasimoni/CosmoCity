package it.unibo.cosmocity.model.utility;

import java.io.FileNotFoundException;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

/*
 * This class is used to play audio files
 *
 */
public class AudioManager {

  private MediaPlayer mediaPlayer;

  /**
   * Play audio file from path
   *
   * @param path
   * @throws FileNotFoundException
   */
  public void play(final String path) {

    try {
      InputStream inputStream = AudioManager.class
          .getResourceAsStream("/it/unibo/asset/audio/" + path);
        System.out.println("Input stream: " + inputStream);
      if (inputStream == null) {
        throw new FileNotFoundException("Can't find audio file");
      }
      final Media sound = new Media(inputStream.toString());
      mediaPlayer = new MediaPlayer(sound);
      mediaPlayer.setVolume(25);
      mediaPlayer.play();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Stop audio file
   */
  public void stop() {

    mediaPlayer.stop();
  }
}
