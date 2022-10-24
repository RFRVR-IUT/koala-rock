package start.structure;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tonneaux extends Group {
    private Rectangle corps;
    protected final static double LARGEUR_MOITIE_TONNEAUX = 5;
    protected final static double LARGEUR_TONNEAUX = LARGEUR_MOITIE_TONNEAUX * 2;

    public Tonneaux(int x, int y, int width, int height) {
        corps = new Rectangle(x, y, width, height);
        corps.setFill(Paint.valueOf("brown"));

        this.getChildren().add(corps);
    }

    public void directionDroite(double largeurJeu) {
        setLayoutX(getLayoutX() + LARGEUR_TONNEAUX / 5);
    }

    public void directionGauche() {
        setLayoutX(getLayoutX() - LARGEUR_TONNEAUX / 5);
    }

    public void directionBas() {
        setLayoutY(getLayoutY() + LARGEUR_TONNEAUX / 5);
    }

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

    public void tombe() {

    }
}
