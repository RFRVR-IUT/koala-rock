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
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.play();
        pause.setOnFinished(event -> {
            this.donkey.setFill(new ImagePattern(new Image("dk-lance.png")));
            this.donkey.setScaleX(-1);

        });
        System.out.println("ATTEND");

        PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
        pause2.play();
        pause.setOnFinished(event -> {
            this.donkey.setFill(new ImagePattern(new Image("dk-lance.png"))); //oui je le met 2 fois pour les tests, c'est pas parce que je suis con
            this.donkey.setScaleX(1);

        });
        System.out.println("ATTEND");
        this.donkey.setFill(new ImagePattern(new Image("dk-idle.png")));


    }
}
