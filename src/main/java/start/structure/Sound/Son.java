package start.structure.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Son {

    public static void jump() {
        File src = new File("src\\main\\java\\start\\structure\\Sound\\jump.wav");
        try {
            AudioInputStream jump = AudioSystem.getAudioInputStream(src);
            Clip clip = AudioSystem.getClip();
            clip.open(jump);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void point() {
        File src = new File("src\\main\\java\\start\\structure\\Sound\\point.wav");
        try {
            AudioInputStream jump = AudioSystem.getAudioInputStream(src);
            Clip clip = AudioSystem.getClip();
            clip.open(jump);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
