package start.structure.Sound;

import start.structure.RessourcesAccess;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class Son {
    private static Media gameMusic;

    public static void playMusic() {
        try {
            File musicPath;
            if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS || OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
                musicPath = new File(Objects.requireNonNull(RessourcesAccess.class.getResource("son/fond.wav")).toURI());
                // gameMusic = new Media(Objects.requireNonNull(String.valueOf(RessourcesAccess.class.getResource("son/fond.wav"))));

            } else {
                musicPath = new File(Objects.requireNonNull(RessourcesAccess.class.getResource("son\\fond.wav")).toURI());

            }
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jump() throws URISyntaxException {
        File soundFile;
        if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS || OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
            soundFile = new File(Objects.requireNonNull(RessourcesAccess.class.getResource("son/jump.wav")).toURI());
        } else {
            soundFile = new File(Objects.requireNonNull(RessourcesAccess.class.getResource("son\\jump.wav")).toURI());
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

    public static void point() throws URISyntaxException {
        File soundFile;
        if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS || OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
            soundFile = new File(Objects.requireNonNull(RessourcesAccess.class.getResource("son/point.wav")).toURI());
        } else {
            soundFile = new File(Objects.requireNonNull(RessourcesAccess.class.getResource("son\\point.wav")).toURI());
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
