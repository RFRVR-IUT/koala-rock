package start.structure;

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
        setLayoutX(getLayoutX() + LARGEUR_TONNEAUX);
    }

    public void directionGauche() {
        setLayoutX(getLayoutX() - LARGEUR_TONNEAUX);
    }

    public void directionBas(){
        setLayoutY(getLayoutY() + 1);
    }

    public void tombe(){


    }
}
