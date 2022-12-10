package start.structure.Model;

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PersonneEnnemie extends Group {

    private final Rectangle donkey;

    /////////////////////////// Choix personnage ///////////////////////////
    private final String choixPersonnage = "NINJA";
    /**
     * Permet de changer l'image du personnage en fonction du choix du joueur
     * @param choixPersonnage
     * @return
     */
    public Paint setChoixPersonnage_IDLE(String choixPersonnage) {
        if (choixPersonnage.equals("KOALA")) {
            return new ImagePattern(new Image("koala.png"));
        } else if (choixPersonnage.equals("NINJA")) {
            return new ImagePattern(new Image("Ninja.png"));
        }
        return null;
    }

    public Paint setChoixPersonnage_LANCE(String choixPersonnage) {
        if (choixPersonnage.equals("KOALA")) {
            return new ImagePattern(new Image("koala-2.png"));
        } else if (choixPersonnage.equals("NINJA")) {
            return new ImagePattern(new Image("Ninja-2.png"));
        }
        return null;
    }
/////////////////////////////////////////////////////////////////////////



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
        donkey.setFill(setChoixPersonnage_IDLE(choixPersonnage));
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
        this.donkey.setFill(setChoixPersonnage_LANCE(choixPersonnage));
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
        this.donkey.setFill(setChoixPersonnage_IDLE(choixPersonnage));
    }

}
