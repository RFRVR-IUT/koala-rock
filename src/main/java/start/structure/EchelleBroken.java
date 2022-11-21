package start.structure;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class EchelleBroken extends Group {

    private final Rectangle echelle;
    private final Rectangle collision;

    /**
     * Constructeur de la classe EchelleBroken
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public EchelleBroken(int x, int y, int width, int height) {
        this.echelle = new Rectangle(x, y, width, height);
        this.collision = new Rectangle(x - 10, y, (width + 20), (height));
        collision.setFill(Paint.valueOf("red"));
        //echelle.setFill(new ImagePattern(new Image("echelle-broken.png"))); //OLD
        echelle.setFill(new ImagePattern(new Image("echelle-broken-NEW.png"))); //NEW
        collision.setOpacity(0);
        this.getChildren().add(echelle);
        this.getChildren().add(collision);
    }
}