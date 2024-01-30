package it.unibo.cosmocity.model.utility;

import java.io.FileNotFoundException;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/*
 * This class is used to play audio files
 *
 */
public class AudioManager {

  /**
   * Play audio file from path
   *
   * @param path
   * @throws FileNotFoundException
   */
  public void play(String path) {
    MediaPlayer mediaPlayer;
    try {
      URL resourceUrl = getClass()
        .getClassLoader()
        .getResource("it/unibo/resources/" + path);
      if (resourceUrl == null) {
        throw new FileNotFoundException("Can't find audio file");
      }
      Media sound = new Media(resourceUrl.toString());
      mediaPlayer = new MediaPlayer(sound);
      mediaPlayer.setVolume(25);
      mediaPlayer.play();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
