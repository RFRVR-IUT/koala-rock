package start.structure.Model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Fond extends Group {
    private final Rectangle fond;

    public Fond(int x, int y, int width, int height) {
        fond = new Rectangle(x, y, width, height);
        fond.setFill(new ImagePattern(new Image("fondKoala.png")));


        this.getChildren().add(fond);
    }
}
