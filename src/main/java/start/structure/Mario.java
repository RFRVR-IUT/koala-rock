package start.structure;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Mario extends Group {
    private final Rectangle corps;
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 5;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private String direction;
    private double ySave;
    private boolean estEnSaut = false;
    private int change = 0;

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
        //corps.setFill(Paint.valueOf("red"));
        corps.setFill(new ImagePattern(new Image("mario-idle.png")));

        this.getChildren().add(corps);
        direction = "droite";
    }

    /**
     * Méthode qui permet de faire bouger le personnage vers la Gauche
     */
    public void directionGauche() {
        //    ****
        //   *    *
        //  *---   *
        //   *    *
        //    ****

        //déplacement <----
        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
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
        //    ****
        //   *    *
        //  *   ---*
        //   *    *
        //    ****
        //déplacement ---->
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
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
        //    *****
        //   *     *
        //  *   |   *
        //   *  |  *
        //    *****
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
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
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
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
        ySave = getLayoutY();
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            //making jump
            double y = getLayoutY();
            System.out.println("y = " + y);
            for (int i = 0; i < 3; i++) {
                setLayoutY(getLayoutY() - (0.7 * LARGEUR_PERSONNAGE));
                System.out.println("en train de jump");
            }
            System.out.println(getLayoutY());
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

    public void setYSave(double y) {
        ySave = y;
    }

    public double getYSave() {
        return ySave;
    }

    /**
     * Méthode qui permet de savoir si le personnage est en collision avec une échelle
     *
     * @param echelles
     * @return
     */
    boolean collisionEchelle(ArrayList<Echelle> echelles) {
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
     * Méthode qui permet de savoir ou se trouve le personnage par rapport à une échelle
     *
     * @param tab
     * @param val -> sert à définir la valeur que ne doit pas dépasser Mario. Si val = 0, alors c'est une échelle normale. Sinon, c'est une échelle cassée (il ne peut pas monter à partir du milieu de celle-ci)
     * @return
     */
    public boolean estEn(ArrayList<ArrayList<Double>> tab, int val) {
        for (ArrayList<Double> d : tab) {
            if ((Double.compare(getLayoutX(), d.get(0)) == 0 || Double.compare(getLayoutX(), d.get(0) + 10) == 0 || Double.compare(getLayoutX(), d.get(0) + 5) == 0 || Double.compare(getLayoutX(), d.get(0) - 5) == 0 || Double.compare(getLayoutX(), d.get(0) - 10) == 0) && Double.compare(getLayoutY(), d.get(1) + val) <= 0) {
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
            if ((Double.compare(getLayoutX(), d.get(0)) == 0 || Double.compare(getLayoutX(), d.get(0) + 5) == 0 || Double.compare(getLayoutX(), d.get(0) + 10) == 0 || Double.compare(getLayoutX(), d.get(0) - 5) == 0 || Double.compare(getLayoutX(), d.get(0) - 10) == 0)//reste sur une lignée, ne sort pas sur le téco
                    && Double.compare(getLayoutY(), d.get(1) + 77) < 0           //permet de passer par le bas même en étant sur la hitbox de l'échelle
                    && Double.compare(getLayoutY(), d.get(1)) > 0) {            //pareil mais pour en haut (pour qu'il puisse sortir en gros)
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
            if ((Double.compare(getLayoutY(), d.get(1) + 77) == 0) &&                      //77 = getLayout(Y) - Y de coordonnéesEchelles
                    (((Double.compare(getLayoutX(), d.get(0)) == 0)) || (Double.compare(getLayoutX(), d.get(0) + 10) == 0) || (Double.compare(getLayoutX(), d.get(0) + 5) == 0) || (Double.compare(getLayoutX(), d.get(0) - 5) == 0) || (Double.compare(getLayoutX(), d.get(0) - 10) == 0))) {       //permet de pas aller + bas que l'échelle
                return true;
            }
        }
        return false;
    }


    /**
     * Méthode qui permet de savoir si le personnage est en collision avec une échelle cassée
     *
     * @param echelles
     * @return
     */
    boolean collisionEchelleBroken(ArrayList<EchelleBroken> echelles) {
        boolean v = false;
        for (EchelleBroken eb : echelles) {
            v = this.getBoundsInParent().contains(eb.getBoundsInParent()) || eb.getBoundsInParent().contains(this.getBoundsInParent());
            if (v) {
                break;
            }
        }
        return v;
    }

    /**
     * Méthode qui permet de savoir si le personnage est en saut
     *
     * @return
     */
    public boolean isEstEnSaut() {
        return estEnSaut;
    }
}
