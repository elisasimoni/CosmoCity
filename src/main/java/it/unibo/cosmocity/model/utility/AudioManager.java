package it.unibo.cosmocity.model.utility;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

/*
 * 
 * AudioManager class
 * Inspiated by https://www.baeldung.com/java-play-sound
 */

public class AudioManager implements LineListener {

  /**
   * Play audio file from path
   *
   * @param path
   */

  /*
   * public void play(String path) {
   * try {
   * System.out.println("File Passato : " + path);
   * final InputStream inputStream =
   * AudioManager.class.getResourceAsStream("/it/unibo/asset/audio/" + path);
   * AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
   * if (inputStream == null) {
   * throw new RuntimeException("Can't find audio file");
   * }
   * AudioFormat audioFormat = audioStream.getFormat();
   * DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
   * Clip audioClip = (Clip) AudioSystem.getLine(info);
   * audioClip.addLineListener(this);
   * audioClip.open(audioStream);
   * audioClip.start();
   * audioClip.close();
   * audioStream.close();
   * } catch (final Exception e) {
   * e.printStackTrace();
   * }
   * }
   */

  public void play(String path) {
    try {
      System.out.println("File Passato: " + path);
      final InputStream inputStream = AudioManager.class.getResourceAsStream("/it/unibo/asset/audio/" + path);
      if (inputStream == null) {
        throw new RuntimeException("Can't find audio file");
      }

      AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
      AudioFormat audioFormat = audioStream.getFormat();
      DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

      try (SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info)) {
        audioLine.open(audioFormat);
        audioLine.start();

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1) {
          audioLine.write(buffer, 0, bytesRead);
        }

        audioLine.drain();
      }

      audioStream.close();
    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(LineEvent event) {
    if (event.getType() == LineEvent.Type.STOP) {
      event.getLine().close();
    }
  }
}
