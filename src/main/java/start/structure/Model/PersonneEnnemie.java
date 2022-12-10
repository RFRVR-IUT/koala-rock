package start.structure.Model;

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PersonneEnnemie extends Group {

    private final Rectangle donkey;

    /**
     * Constructeur de la classe DonkeyKong
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public PersonneEnnemie(int x, int y, int width, int height) {
        this.donkey = new Rectangle(x, y, width, height);
        donkey.setFill(new ImagePattern(new Image("koala.png")));
        this.getChildren().add(donkey);

    }

    public Rectangle getDonkey() {
        return donkey;
    }

    /**
     * Lancement Tonneaux
     *
     * @param tonneaux
     */
    public void lance(Tonneaux tonneaux) {
        this.donkey.setFill(new ImagePattern(new Image("koala-2.png")));
        this.donkey.setScaleX(-1);
        gauche();

    }

    /**
     * Animation Donkey Kong Gauche
     */
    public void gauche() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            droite();

        });
        pause.play();
    }

    /**
     * Animation Donkey Kong Droite
     */
    public void droite() {
        this.donkey.setScaleX(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            idle();

        });
        pause.play();

    }

    /**
     * Animation Donkey Kong Idle
     */
    public void idle() {
        this.donkey.setFill(new ImagePattern(new Image("koala.png")));
    }

}
