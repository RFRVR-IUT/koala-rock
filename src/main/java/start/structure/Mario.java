package start.structure;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Mario extends Group {
    private Rectangle corps;
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 5;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private String direction;
    private double ySave;
    private boolean estEnSaut=false;

    public Mario(int x, int y, int width, int height) {
        corps = new Rectangle(x, y, width, height);
        //corps.setFill(Paint.valueOf("red"));
        corps.setFill(new ImagePattern(new Image("mario-idle.png")));

        this.getChildren().add(corps);
        direction = "droite";
    }

    public void directionGauche() {
        //    ****
        //   *    *
        //  *---   *
        //   *    *
        //    ****

        //déplacement <----
        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("gauche")) {
            direction = "gauche";
        }
        corps.setFill(new ImagePattern(new Image("mario-idle.png")));
        corps.setScaleX(1);
    }

    public void directionDroite(double largeurJeu) {
        //    ****
        //   *    *
        //  *   ---*
        //   *    *
        //    ****
        //déplacement ---->
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("droite")) {
            direction = "droite";
        }
        corps.setFill(new ImagePattern(new Image("mario-idle.png")));
        corps.setScaleX(-1);
    }

    public void directionBas(double hauteurJeu) {
        //    *****
        //   *     *
        //  *   |   *
        //   *  |  *
        //    *****
        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("bas")) {
            direction = "bas";
        }
        corps.setFill(new ImagePattern(new Image("mario-climb.png")));
    }

    public void directionHaut() {
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("haut")) {
            direction = "haut";
        }
        corps.setFill(new ImagePattern(new Image("mario-climb.png")));
    }

    public void jump() {
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
        ySave = getLayoutY();
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            //making jump
            double y = getLayoutY();
            System.out.println("y = " + y);
            for (int i = 0; i < 3; i++) {
                setLayoutY(getLayoutY() - (0.2*LARGEUR_PERSONNAGE));
                System.out.println("en train de jump");
            }
            System.out.println(getLayoutY());
        }
        this.estEnSaut = true;
        corps.setFill(new ImagePattern(new Image("mario-walk1.png")));
    }

    public void atterir(){
        while(getLayoutY() < ySave){
            setLayoutY(getLayoutY() + (0.2*LARGEUR_PERSONNAGE));
        }
        this.estEnSaut = false;
        corps.setFill(new ImagePattern(new Image("mario-idle.png")));
    }

    public void setYSave(double y){
        ySave = y;
    }

    public double getYSave(){
        return ySave;
    }

    boolean collisionEchelle(ArrayList<Echelle> echelles) {
        boolean v = false;
        for(Echelle e : echelles){
            v = this.getBoundsInParent().contains(e.getBoundsInParent())
                    || e.getBoundsInParent().contains(this.getBoundsInParent());
            if(v){break;}
        }
        return v;
    }

    public boolean estEn(ArrayList<ArrayList<Double>> tab){
        for(ArrayList<Double> d : tab){
            if((Double.compare(getLayoutX(), d.get(0)) == 0
                    || Double.compare(getLayoutX(), d.get(0)+10) == 0
                    || Double.compare(getLayoutX(), d.get(0)-10) == 0)
                    && Double.compare(getLayoutY(), d.get(1)) == 0){
                return true;
            }
        }
        return false;
    }

    public boolean estDansEchelle(ArrayList<ArrayList<Double>> tab){
        for(ArrayList<Double> d : tab){
            if((Double.compare(getLayoutX(), d.get(0)) == 0
                    || Double.compare(getLayoutX(), d.get(0)+10) == 0
                    || Double.compare(getLayoutX(), d.get(0)-10) == 0) //reste sur une lignée, ne sort pas sur le téco
            && Double.compare(getLayoutY(), d.get(1)+50) < 0           //permet de passer par le bas même en étant sur la hitbox de l'échelle
            && Double.compare(getLayoutY(), d.get(1)) > 0){            //pareil mais pour en haut (pour qu'il puisse sortir en gros)
                return true;
            }
        }
        return false;
    }

    public boolean estDansBasEchelle(ArrayList<ArrayList<Double>> tab){
        for(ArrayList<Double> d : tab){
            if(Double.compare(getLayoutY(), d.get(1)+50) == 0){       //permet de pas aller + bas que l'échelle
                return true;
            }
        }
        return false;
    }

    public boolean isEstEnSaut() {
        return estEnSaut;
    }
}
