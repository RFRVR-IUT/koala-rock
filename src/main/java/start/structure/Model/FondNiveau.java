package start.structure.Model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import start.structure.RessourcesAccess;

import java.util.Objects;

public class FondNiveau extends Group {
    private static String choixFondNiveau = "KOALA";

    public FondNiveau(int x, int y, int width, int height) {
        Rectangle fond = new Rectangle(x, y, width, height);
        fond.setFill(setChoixFond_Img(choixFondNiveau));


        this.getChildren().add(fond);
    }

    public static void setChoixFondNiveau(String choixFondNiveau) {
        FondNiveau.choixFondNiveau = choixFondNiveau;
    }

    /**
     * Methode qui permet de choisir l'image de l'echelle
     *
     * @param choixFond
     * @return
     */
    public Paint setChoixFond_Img(String choixFond) {
        if (choixFond.equals("KOALA")) {
            return new ImagePattern(new Image(Objects.requireNonNull(RessourcesAccess.class.getResourceAsStream("fondNiveau/fondKoala.png"))));
        } else if (choixFond.equals("NINJA")) {
            return new ImagePattern(new Image(Objects.requireNonNull(RessourcesAccess.class.getResourceAsStream("fondNiveau/fondNinja.png"))));
        }
        return null;
    }
}
