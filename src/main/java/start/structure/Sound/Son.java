package start.structure.Sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import start.structure.RessourcesAccess;

import java.util.Objects;

public class Son {
    private static Media gameMusic;
    private static Media jumpMusic;
    private static Media pointMusic;
    private static MediaPlayer mediaPlayer;

    public static void playMusic() {
        try {
            if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS || OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
                gameMusic = new Media(Objects.requireNonNull(String.valueOf(RessourcesAccess.class.getResource("son/fond.wav"))));
                mediaPlayer = new MediaPlayer(gameMusic);
            } else {
                gameMusic = new Media(Objects.requireNonNull(String.valueOf(RessourcesAccess.class.getResource("son\\fond.wav"))));
                mediaPlayer = new MediaPlayer(gameMusic);
            }
            if (gameMusic != null) {
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setVolume(0.1);
                mediaPlayer.play();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jump() {
        try {
            if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS || OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
                jumpMusic = new Media(Objects.requireNonNull(String.valueOf(RessourcesAccess.class.getResource("son/jump.wav"))));
                mediaPlayer = new MediaPlayer(jumpMusic);
            } else {
                jumpMusic = new Media(Objects.requireNonNull(String.valueOf(RessourcesAccess.class.getResource("son\\jump.wav"))));
                mediaPlayer = new MediaPlayer(jumpMusic);
            }
            if (jumpMusic != null) {
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setCycleCount(1);
                mediaPlayer.setVolume(0.1);
                mediaPlayer.play();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void point() {
        try {
            if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS || OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
                pointMusic = new Media(Objects.requireNonNull(String.valueOf(RessourcesAccess.class.getResource("son/point.wav"))));
                mediaPlayer = new MediaPlayer(pointMusic);
            } else {
                pointMusic = new Media(Objects.requireNonNull(String.valueOf(RessourcesAccess.class.getResource("son\\jump.wav"))));
                mediaPlayer = new MediaPlayer(pointMusic);
            }
            if (pointMusic != null) {
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setCycleCount(1);
                mediaPlayer.setVolume(0.1);
                mediaPlayer.play();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
