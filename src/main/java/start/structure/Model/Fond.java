package start.structure.Model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import start.structure.RessourcesAccess;

import java.util.Objects;

public class Fond extends Group {
    private static String choixFond = "KOALA";

    public Fond(int x, int y, int width, int height) {
        Rectangle fond = new Rectangle(x, y, width, height);
        fond.setFill(setChoixFond_Img(choixFond));


        this.getChildren().add(fond);
    }

    public static void setChoixFond(String choixFond) {
        Fond.choixFond = choixFond;
    }

    /**
     * Methode qui permet de choisir l'image de l'echelle
     *
     * @param choixFond
     * @return
     */
    public Paint setChoixFond_Img(String choixFond) {
        if (choixFond.equals("KOALA")) {
            return new ImagePattern(new Image(Objects.requireNonNull(RessourcesAccess.class.getResourceAsStream("fond/fondKoala.png"))));
        } else if (choixFond.equals("NINJA")) {
            return new ImagePattern(new Image(Objects.requireNonNull(RessourcesAccess.class.getResourceAsStream("fond/fondNinja.png"))));
        }
        return null;
    }
}
