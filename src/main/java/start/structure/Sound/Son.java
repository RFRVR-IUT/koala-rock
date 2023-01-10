package start.structure.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Son {


    public static void playMusic() {
        try {
            File musicPath;
            if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS || OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
                musicPath = new File("src/main/java/start/structure/Sound/Fond.wav");
            } else {
                musicPath = new File("src\\main\\java\\start\\structure\\Sound\\Fond.wav");
            }
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jump() {
        File soundFile;
        if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS || OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
            soundFile = new File("src/main/java/start/structure/Sound/jump.wav");
        } else {
            soundFile = new File("src\\main\\java\\start\\structure\\Sound\\jump.wav");
        }
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void point() {
        File soundFile;
        if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS || OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
            soundFile = new File("src/main/java/start/structure/Sound/point.wav");
        } else {
            soundFile = new File("src\\main\\java\\start\\structure\\Sound\\point.wav");
        }
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
