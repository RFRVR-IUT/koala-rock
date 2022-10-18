package start.structure;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Mario extends Group {
    private Rectangle corps;
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 5;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private String direction;

    public Mario(int x, int y, int width, int height) {
        corps = new Rectangle(x, y, width, height);
        corps.setFill(Paint.valueOf("red"));
        this.getChildren().add(corps);
        direction = "droite";
    }

    public void directionGauche() {
        //    ****
        //   *    *
        //  *---   *
        //   *    *
        //    ****

        //déplacement <----
        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("gauche")) {
            direction = "gauche";
        }
    }

    public void directionDroite(double largeurJeu) {
        //    ****
        //   *    *
        //  *   ---*
        //   *    *
        //    ****
        //déplacement ---->
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("droite")) {
            direction = "droite";
        }
    }

    public void directionBas(double hauteurJeu) {
        //    *****
        //   *     *
        //  *   |   *
        //   *  |  *
        //    *****
        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("bas")) {
            direction = "bas";
        }
    }

    public void directionHaut() {
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("haut")) {
            direction = "haut";
        }
    }

    public void jump() {
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            //making jump
            double y = getLayoutY();
            for (int i = 0; i < 3; i++) {
                setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("en train de jump");
            }
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setLayoutY(y);
        }
    }

    boolean collisionEchelle(ArrayList<Echelle> echelles) {
        boolean v = false;
        for(Echelle e : echelles){
            v = this.getBoundsInParent().contains(e.getBoundsInParent())
                    || e.getBoundsInParent().contains(this.getBoundsInParent());
            if(v){break;}
        }
        return v;
    }
}
