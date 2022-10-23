package start.structure;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Fond extends Group {
    private Rectangle fond;

    public Fond(int x, int y, int width, int height) {
        fond = new Rectangle(x, y, width, height);
        //corps.setFill(Paint.valueOf("red"));
        fond.setFill(new ImagePattern(new Image("test.png")));

        this.getChildren().add(fond);
    }
}
