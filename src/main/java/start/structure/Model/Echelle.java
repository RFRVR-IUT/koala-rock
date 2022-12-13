package start.structure.Model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Echelle extends Group {

    private static String choixEchelle = "KOALA";

    /**
     * Constructeur de la classe Echelle
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Echelle(int x, int y, int width, int height) {
        Rectangle echelle = new Rectangle(x, y, width, height);
        Rectangle collision = new Rectangle(x - 10, y - 35, (width + 20), (height + 35));
        echelle.setFill(setChoixEchelle_Img(choixEchelle));
        collision.setOpacity(0.5);
        this.getChildren().add(echelle);
        this.getChildren().add(collision);
    }

    public static void setChoixEchelle(String choixEchelle) {
        Echelle.choixEchelle = choixEchelle;
    }

    /**
     * Methode qui permet de choisir l'image de l'echelle
     *
     * @param choixEchelle
     * @return
     */
    public Paint setChoixEchelle_Img(String choixEchelle) {
        if (choixEchelle.equals("KOALA")) {
            return new ImagePattern(new Image("echelle_koala.png"));
        } else if (choixEchelle.equals("NINJA")) {
            return new ImagePattern(new Image("echelle_ninja.png"));
        }
        return null;
    }
}