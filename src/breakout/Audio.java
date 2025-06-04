package breakout;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Audio {
   private Clip clip;

   public Audio(String fileName) {
       try {
           URL url = getClass().getClassLoader().getResource(fileName);
           if (url == null) {
               System.err.println("Audio file not found: " + fileName);
               return;
           }

           AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
           clip = AudioSystem.getClip();
           clip.open(audioIn);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public void play() {
       if (clip == null) return;
       if (clip.isRunning()) clip.stop();
       clip.setFramePosition(0); // rewind
       clip.start();
   }

   public void stop() {
       if (clip != null && clip.isRunning()) {
           clip.stop();
       }
   }

   public boolean isPlaying() {
       return clip != null && clip.isRunning();
   }

   // Preloaded audio instances
   public static final Audio themeGameMusic = new Audio("AGST - Force.wav");
   public static final Audio destroyEffect = new Audio("DestroyFX.wav");
   public static final Audio endGameAudio = new Audio("gameover.wav");
}
