package start.structure;

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class DonkeyKong extends Group{

    private Rectangle donkey;

    public DonkeyKong(int x, int y, int width, int height){
        this.donkey = new Rectangle(x, y, width, height);
        donkey.setFill(new ImagePattern(new Image("dk-idle.png")));
        this.getChildren().add(donkey);

    }

    public Rectangle getDonkey() {
        return donkey;
    }

    public void lance(Tonneaux t) {
        this.donkey.setFill(new ImagePattern(new Image("dk-lance.png")));
        this.donkey.setScaleX(-1);
        gauche();

    }

    public void gauche(){
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            droite();

        });
        pause.play();
    }

    public void droite(){
        this.donkey.setScaleX(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            idle();

        });
        pause.play();

    }

    public void idle(){
        this.donkey.setFill(new ImagePattern(new Image("dk-idle.png")));
    }
}
