package start.structure;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Echelle extends Group {

    private Rectangle echelle;
    private final Rectangle collision;
    private final int num;

    public Echelle(int x, int y, int width, int height, int num) {
        this.echelle = new Rectangle(x, y, width, height);
        this.collision = new Rectangle(x - 10, y - 35, (width + 20), (height + 35));
        collision.setFill(Paint.valueOf("red"));
        echelle.setFill(new ImagePattern(new Image("echelle.png")));
        collision.setOpacity(0);
        this.num = num;
        this.getChildren().add(echelle);
        this.getChildren().add(collision);
    }

    public Rectangle getEchelle() {
        return echelle;
    }

    public void setEchelle(Rectangle echelle) {
        this.echelle = echelle;
    }

    public int getNum() {
        return num;
    }

    public static int getLargeurCollision() {
        return 15;
    }
}
