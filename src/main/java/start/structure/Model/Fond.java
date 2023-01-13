/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package start.structure.Model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import start.structure.RessourcesAccess;

import java.util.Objects;

public class Fond extends Group {
    private static String choixFond = "CLASSIC";

    public Fond(int width, int height) {
        Rectangle fond = new Rectangle(width, height);
        fond.setFill(setChoixFond_Img(choixFond));
        System.out.println(choixFond);
        this.getChildren().add(fond);
    }

    public static void setChoixFond(String choixFond) {
        Fond.choixFond = choixFond;
    }

    public Node getChoixFond() {
        return this.getChildren().get(0);
    }

    /**
     * Methode qui permet de choisir l'image de l'echelle
     *
     * @param choixFond
     * @return
     */
    public Paint setChoixFond_Img(String choixFond) {
        if (choixFond.equals("CLASSIC")) {
            return new ImagePattern(new Image(Objects.requireNonNull(RessourcesAccess.class.getResourceAsStream("fond/classic.png"))));
        } else if (choixFond.equals("NUIT")) {
            return new ImagePattern(new Image(Objects.requireNonNull(RessourcesAccess.class.getResourceAsStream("fond/night.jpeg"))));
        }
        return null;
    }
}
