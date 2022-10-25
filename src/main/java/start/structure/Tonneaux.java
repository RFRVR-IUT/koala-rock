package start.structure;

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Tonneaux extends Group {
    private final Rectangle corps;
    protected final static double LARGEUR_MOITIE_TONNEAUX = 5;
    protected final static double LARGEUR_TONNEAUX = LARGEUR_MOITIE_TONNEAUX * 2;

    /**
     * Constructeur de la classe Tonneaux
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Tonneaux(int x, int y, int width, int height) {
        corps = new Rectangle(x, y, width, height);
        corps.setFill(Paint.valueOf("brown"));
        corps.setFill(new ImagePattern(new Image("tonneau1.png")));

        this.getChildren().add(corps);
    }

    /**
     * Méthode qui permet tonneaux direction Droite
     * @param largeurJeu
     */
    public void directionDroite(double largeurJeu) {
        setLayoutX(getLayoutX() + LARGEUR_TONNEAUX / 5);
    }

    /**
     * Méthode qui permet tonneaux direction Gauche
     */
    public void directionGauche() {
        setLayoutX(getLayoutX() - LARGEUR_TONNEAUX / 5);
    }

    /**
     * Méthode qui permet de faire bouger le tonneaux vers le bas
     */
    public void directionBas() {
        setLayoutY(getLayoutY() + LARGEUR_TONNEAUX / 5);
    }

    /**
     * Méthode qui permet la collision echelle avec tonneaux
     * @param coordonneesEchelles
     * @return
     */
    public boolean collisionEchelleTonneau(ArrayList<ArrayList<Double>> coordonneesEchelles) {
        for (int i = 0; i < coordonneesEchelles.size(); i++) {
            Double coordoneesX = coordonneesEchelles.get(i).get(0);
            Double coordonneesY = (coordonneesEchelles.get(i).get(1));
            if (coordoneesX % 2 == 1) {
                coordoneesX++;
            }
            if (coordonneesY % 2 == 1) {
                coordonneesY++;
            }
            if (this.getLayoutX() == coordoneesX && this.getLayoutY() == coordonneesY + 10) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode qui permet le mouvement des tonneaux
     * @param coordonneesEchelles
     * @param dk
     */
    public void moveTonneaux(ArrayList<ArrayList<Double>> coordonneesEchelles, DonkeyKong dk) {
        ArrayList<Double> coordonneesEchelle1 = new ArrayList<>();
        // coordonneesEchelle1.add(76.0);
        // coordonneesEchelle1.add(246.0);
        PauseTransition pause = new PauseTransition();
        pause.setDuration(javafx.util.Duration.seconds(0.005));
        pause.setOnFinished(event -> {
            double chanceTonneauxEchelle = Math.random();
            if ((this.collisionEchelleTonneau(coordonneesEchelles)) && (chanceTonneauxEchelle < 0.5)) {
                this.directionBas();
                pause.play();

            }
            // quand le tonneau est en bas de l'échelle -> On relance le tonneau.
            if (this.getLayoutX() == 70.0 && this.getLayoutY() == 556.0) {
                dk.lance(this);
            }

            if (this.getLayoutX() < -30.0 && this.getLayoutY() == 556.0) {
                this.setLayoutY(170.0);
                this.setLayoutX(120.0);
                moveTonneaux(coordonneesEchelles, dk);
            } else if ((this.getLayoutX() < 530.0 && this.getLayoutY() == 170.0) // étage 1 (haut -> bas)
                    || (this.getLayoutX() < 530.0 && this.getLayoutY() == 324.0) // étage 3
                    || (this.getLayoutX() < 530.0 && this.getLayoutY() == 478.0)) { // étage 5
                if (this.getLayoutX() < 640) {
                    this.directionDroite(640);
                    pause.play();
                }
            } else if ((this.getLayoutX() > 0.0 && this.getLayoutY() == 248.0) // étage 2
                    || (this.getLayoutX() > 0.0 && this.getLayoutY() == 402.0) // étage 4
                    || (this.getLayoutX() > -40.0 && this.getLayoutY() == 556.0)) { // étage 6
                this.directionGauche();
                pause.play();
            } else {
                //System.out.println(tonneaux.getLayoutY());
                this.directionBas();
                pause.play();
            }
        });
        pause.play();
    }


    public void tombe() {

    }
}