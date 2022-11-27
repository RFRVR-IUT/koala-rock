package start.structure.Sound;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Son {

    public static void jump() {
        File src = new File("src\\main\\java\\start\\structure\\Sound\\jump.wav");
        try{
            AudioInputStream jump = AudioSystem.getAudioInputStream(src);
            Clip clip = AudioSystem.getClip();
            clip.open(jump);
            clip.start();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public static void point(){
        File src = new File("src\\main\\java\\start\\structure\\Sound\\point.wav");
        try{
            AudioInputStream jump = AudioSystem.getAudioInputStream(src);
            Clip clip = AudioSystem.getClip();
            clip.open(jump);
            clip.start();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }



}
