package start.structure;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EchelleBroken extends Group {

    private final Rectangle echelle;

    /**
     * Constructeur de la classe EchelleBroken
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public EchelleBroken(int x, int y, int width, int height) {
        this.echelle = new Rectangle(x, y, width, height);
        echelle.setFill(new ImagePattern(new Image("echelle-broken.png")));
        this.getChildren().add(echelle);
    }
}