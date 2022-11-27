package start.structure.Model;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Tonneaux extends Group {
    protected final static double LARGEUR_MOITIE_TONNEAUX = 5;
    protected final static double LARGEUR_TONNEAUX = LARGEUR_MOITIE_TONNEAUX * 2;
    private final Rectangle corps;
    private final Rectangle collision;
    private final Rectangle collisionHaut;
    private boolean isRotationGauche = false;
    private boolean isRotationDroite = false;
    private boolean descendUneEchelle = false;

    /**
     * Constructeur de la classe Tonneaux
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Tonneaux(int x, int y, int width, int height) {
        corps = new Rectangle(x, y, width, height);
        collision = new Rectangle(x, y, width, height);
        collisionHaut = new Rectangle(x, y - 20, width, height-10);
        corps.setFill(Paint.valueOf("brown"));
        corps.setFill(new ImagePattern(new Image("Rocher.png")));
        collision.setFill(Paint.valueOf("red"));
        collisionHaut.setFill(Paint.valueOf("blue"));
        collisionHaut.setOpacity(0.5);
        collision.setOpacity(0.5);

        this.getChildren().add(corps);
        this.getChildren().add(collision);
        this.getChildren().add(collisionHaut);
    }

    public Rectangle getCollision() {
        return collision;
    }

    public Rectangle getCollisionHaut() {
        return collisionHaut;
    }

    public boolean getDescendUneEchelle() {
        return descendUneEchelle;
    }

    public void setDescendUneEchelle(boolean descendUneEchelle) {
        this.descendUneEchelle = descendUneEchelle;
    }

    /**
     * Méthode qui permet tonneaux direction Droite
     *
     * @param largeurJeu
     */
    public void directionDroite(double largeurJeu) {
        setLayoutX(getLayoutX() + LARGEUR_TONNEAUX / 5);
        if(!isRotationDroite){
            this.getChildren().add(collisionHaut);
            rotation(360);
            isRotationDroite = true;
            isRotationGauche = false;
        }

    }

    /**
     * Méthode qui permet tonneaux direction Gauche
     */
    public void directionGauche() {
        setLayoutX(getLayoutX() - LARGEUR_TONNEAUX / 5);
        if(!isRotationGauche){
            this.getChildren().add(collisionHaut);
            rotation(-360);
            isRotationGauche = true;
            isRotationDroite = false;
        }

    }

    /**
     * Méthode qui permet de faire bouger le tonneaux vers le bas
     */
    public void directionBas() {
        setLayoutY(getLayoutY() + LARGEUR_TONNEAUX / 5);
        this.getChildren().remove(collisionHaut);
    }

    public void rotation(int d){
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(d);
        rotate.setCycleCount(500);
        rotate.setDuration(Duration.millis(1000));
        rotate.setNode(this.corps);
        rotate.play();
    }

    /**
     * Méthode qui permet la collision echelle avec tonneaux
     *
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
     *
     * @param coordonneesEchelles
     * @param dk
     */
    public void moveTonneaux(ArrayList<ArrayList<Double>> coordonneesEchelles, DonkeyKong dk) {
        PauseTransition pause = new PauseTransition();
        pause.setDuration(javafx.util.Duration.seconds(0.01));
        pause.play();
        pause.setOnFinished(event -> {
            double chanceTonneauxEchelle = Math.random();
            if ((this.collisionEchelleTonneau(coordonneesEchelles)) && (chanceTonneauxEchelle < 0.5)) {
                this.directionBas();
                this.descendUneEchelle = true;
                pause.play();

            }
            if (this.getLayoutX() == 70.0 && this.getLayoutY() == 556.0) {
                this.getChildren().remove(collisionHaut);
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
                    this.descendUneEchelle = false;
                    pause.play();
                }
            } else if ((this.getLayoutX() > 0.0 && this.getLayoutY() == 248.0) // étage 2
                    || (this.getLayoutX() > 0.0 && this.getLayoutY() == 402.0) // étage 4
                    || (this.getLayoutX() > -40.0 && this.getLayoutY() == 556.0)) { // étage 6
                this.directionGauche();
                this.descendUneEchelle = false;
                pause.play();
            } else {
                this.directionBas();
                pause.play();
            }
        });
    }
}