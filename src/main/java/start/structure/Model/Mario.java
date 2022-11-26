package start.structure.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Mario extends Group {
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 5;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private final Rectangle corps;
    private String direction;
    private double ySave;
    private boolean estEnSaut = false;
    private int change = 0;
    private IntegerProperty score = new SimpleIntegerProperty(0);
    private boolean aEuSonScore = false;
    private boolean estSurEchelle = false;

    /**
     * Constructeur de la classe Mario
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Mario(int x, int y, int width, int height) {
        corps = new Rectangle(x, y, width, height);
        corps.setFill(new ImagePattern(new Image("mario-idle.png")));

        this.getChildren().add(corps);
        direction = "droite";
    }

    public IntegerProperty getScore() {
        return score;
    }

    public void setaEuSonScore(boolean aEuSonScore) {
        this.aEuSonScore = aEuSonScore;
    }

    public void setEstSurEchelle(boolean estSurEchelle) {
        this.estSurEchelle = estSurEchelle;
    }

    /**
     * Méthode qui permet de faire bouger le personnage vers la Gauche
     */
    public void directionGauche() {
        // ****
        // * *
        // *--- *
        // * *
        // ****

        // déplacement <----
        if (getLayoutX() > -20) {
            setLayoutX(getLayoutX() - LARGEUR_MOITIE_PERSONNAGE);
        }
        if (!direction.equals("gauche")) {
            direction = "gauche";
        }
        corps.setFill(new ImagePattern(new Image("mario-idle.png")));
        corps.setScaleX(1);
    }

    /**
     * Méthode qui permet de faire bouger le personnage vers la Droite
     *
     * @param largeurJeu
     */
    public void directionDroite(double largeurJeu) {
        // ****
        // * *
        // * ---*
        // * *
        // ****
        // déplacement ---->
        if (getLayoutX() < 550.0) {
            setLayoutX(getLayoutX() + LARGEUR_MOITIE_PERSONNAGE);
        }
        if (!direction.equals("droite")) {
            direction = "droite";
        }
        corps.setFill(new ImagePattern(new Image("mario-idle.png")));
        corps.setScaleX(-1);
    }

    /**
     * Méthode qui permet de faire bouger le personnage vers le Bas
     *
     * @param hauteurJeu
     */
    public void directionBas(double hauteurJeu) {
        // *****
        // * *
        // * | *
        // * | *
        // *****
        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE + 1);
        }
        if (!direction.equals("bas")) {
            direction = "bas";
        }
        corps.setFill(new ImagePattern(new Image("mario-climb.png")));
        if (change % 2 == 0) {
            corps.setScaleX(1);
            change++;
        } else {
            corps.setScaleX(-1);
            change++;
        }
    }

    /**
     * Méthode qui permet de faire bouger le personnage vers le Haut
     */
    public void directionHaut() {
        // *****
        // * | *
        // * | *
        // * *
        // *****
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE - 1);
        }
        if (!direction.equals("haut")) {
            direction = "haut";
        }
        corps.setFill(new ImagePattern(new Image("mario-climb.png")));
        if (change % 2 == 0) {
            corps.setScaleX(1);
            change++;
        } else {
            corps.setScaleX(-1);
            change++;
        }

    }

    /**
     * Méthode qui permet de faire sauter le personnage
     */
    public void jump() {
        // *****
        // * | *
        // * | *
        // * *
        // *****
        ySave = getLayoutY();
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            // making jump
            double y = getLayoutY();
            //System.out.println("y = " + y);
            for (int i = 0; i < 3; i++) {
                setLayoutY(getLayoutY() - (0.7 * LARGEUR_PERSONNAGE));
                //System.out.println("en train de jump");
            }
            //System.out.println(getLayoutY());
        }
        this.estEnSaut = true;
        corps.setFill(new ImagePattern(new Image("mario-walk1.png")));
    }

    /**
     * Méthode animation de faire atterir le personnage
     */
    public void atterir() {
        while (getLayoutY() < ySave) {
            setLayoutY(getLayoutY() + (0.7 * LARGEUR_PERSONNAGE));
        }
        this.estEnSaut = false;
        corps.setFill(new ImagePattern(new Image("mario-idle.png")));
    }

    public double getYSave() {
        return ySave;
    }

    public void setYSave(double y) {
        ySave = y;
    }

    /**
     * Méthode qui permet de savoir si le personnage est en collision avec une
     * échelle
     *
     * @param echelles
     * @return
     */
    public boolean collisionEchelle(ArrayList<Echelle> echelles) {
        boolean v = false;
        for (Echelle e : echelles) {
            v = this.getBoundsInParent().contains(e.getBoundsInParent()) || e.getBoundsInParent().contains(this.getBoundsInParent());
            if (v) {
                break;
            }
        }
        return v;
    }

    /**
     * Méthode qui permet de savoir ou se trouve le personnage par rapport à une
     * échelle
     *
     * @param tab
     * @return
     */
    public boolean estEn(ArrayList<ArrayList<Double>> tab) {
        for (ArrayList<Double> d : tab) {
            System.out.println(d.toString());
            if ((Double.compare(getLayoutX(), d.get(0)) == 0 || Double.compare(getLayoutX(), d.get(0) + 10) == 0 || Double.compare(getLayoutX(), d.get(0) + 5) == 0 || Double.compare(getLayoutX(), d.get(0) - 5) == 0 || Double.compare(getLayoutX(), d.get(0) - 10) == 0) && Double.compare(getLayoutY(), d.get(1)) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode qui permet de savoir si le personnage est sur une échelle
     *
     * @param tab
     * @return
     */
    public boolean estDansEchelle(ArrayList<ArrayList<Double>> tab) {
        for (ArrayList<Double> d : tab) {
            if ((Double.compare(getLayoutX(), d.get(0)) == 0 || Double.compare(getLayoutX(), d.get(0) + 5) == 0 || Double.compare(getLayoutX(), d.get(0) + 10) == 0 || Double.compare(getLayoutX(), d.get(0) - 5) == 0 || Double.compare(getLayoutX(), d.get(0) - 10) == 0)// reste sur une lignée, ne sort pas sur le téco
                    && Double.compare(getLayoutY(), d.get(1) + 77) < 0 // permet de passer par le bas même en étant sur
                    // la hitbox de l'échelle
                    && Double.compare(getLayoutY(), d.get(1)) > 0) { // pareil mais pour en haut (pour qu'il puisse
                // sortir en gros)
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode qui permet de savoir si le personnage est en bas de l'échelle
     *
     * @param tab
     * @return
     */
    public boolean estDansBasEchelle(ArrayList<ArrayList<Double>> tab) {
        for (ArrayList<Double> d : tab) {
            if ((Double.compare(getLayoutY(), d.get(1) + 77) == 0) && // 77 = getLayout(Y) - Y de coordonnéesEchelles
                    (((Double.compare(getLayoutX(), d.get(0)) == 0)) || (Double.compare(getLayoutX(), d.get(0) + 10) == 0) || (Double.compare(getLayoutX(), d.get(0) + 5) == 0) || (Double.compare(getLayoutX(), d.get(0) - 5) == 0) || (Double.compare(getLayoutX(), d.get(0) - 10) == 0))) { // permet de pas aller + bas que
                // l'échelle
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode qui permet de savoir si le personnage est en collision avec une
     * échelle cassée
     *
     * @param echelles
     * @return
     */
    public boolean collisionEchelleBroken(ArrayList<EchelleBroken> echelles) {
        boolean v = false;
        for (EchelleBroken eb : echelles) {
            v = this.getBoundsInParent().contains(eb.getBoundsInParent()) || eb.getBoundsInParent().contains(this.getBoundsInParent());
            if (v) {
                break;
            }
        }
        return v;
    }

    public boolean estEnBroken(ArrayList<ArrayList<Double>> tab) {
        for (ArrayList<Double> d : tab) {
            System.out.println(d.toString());
            if ((Double.compare(getLayoutX(), d.get(0)) == 0 || Double.compare(getLayoutX(), d.get(0) + 10) == 0 || Double.compare(getLayoutX(), d.get(0) + 5) == 0 || Double.compare(getLayoutX(), d.get(0) - 5) == 0 || Double.compare(getLayoutX(), d.get(0) - 10) == 0) && Double.compare(getLayoutY(), d.get(1) + 60) <= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode qui permet de savoir si le personnage est en saut
     *
     * @return
     */
    public boolean isEstEnSaut() {
        return estEnSaut;
    }

    public void tomberEtage() {
        double[] étagesDroiteVide = {468.0, 314.0, 160.0};
        double[] étagesGaucheVide = {391.0, 237.0};

        if (this.getLayoutX() >= 525.0) { // étage 1 & 3 & 5
            for (double d : étagesDroiteVide) {
                if (this.getLayoutY() == d) {
                    for (int i = 0; i < 77; i++) {
                        this.setLayoutY(this.getLayoutY() + 1);
                    }
                }
            }
        } else if (this.getLayoutX() == 0.0) { // étage 2 & 4
            for (double d : étagesGaucheVide) {
                if (this.getLayoutY() == d) {
                    for (int i = 0; i < 77; i++) {
                        this.setLayoutY(this.getLayoutY() + 1);
                    }
                }
            }
        }
    }

    public int collisionTonneaux(ArrayList<Tonneaux> tonneaux) {
        int res = 0;
        for (Tonneaux t : tonneaux) {
            if (this.getBoundsInParent().intersects(t.getBoundsInParent())) {
                if (this.isEstEnSaut() && !aEuSonScore) {
                    ajouterScore(1);
                    res = 1;
                    this.aEuSonScore = true;
                    //aEuSonScore, s'il est true, permet d'avoir que +1 quand il touche la collision du haut.
                    //Il se remet à false à chaque fois qu'on saute.
                } else if ((!this.isEstEnSaut() && !estSurEchelle) || (estSurEchelle && t.getDescendUneEchelle())) {
                    //System.out.println("Touche !");
                    res = -1;
                }
            }
        }
        return res;
    }

    private void ajouterScore(int nb) {
        this.score.set(this.score.getValue() + nb);
    }


}