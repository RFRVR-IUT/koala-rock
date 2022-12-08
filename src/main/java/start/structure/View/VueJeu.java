package start.structure.View;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import start.structure.Model.*;
import start.structure.Sound.Son;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class VueJeu {
    ArrayList<Echelle> echelles;
    ArrayList<EchelleBroken> echellesBrokens;
    ArrayList<Tonneaux> tonneaux;
    Mario mario = new Mario();
    private boolean isPause = false;
    private Scene s;
    private Stage primaryStage;
    private DonkeyKong dk;
    private Pane jeu;
    private VueGagne vueGagne = new VueGagne();
    private VuePerdre vuesPerdu = new VuePerdre();
    private String mode = "Normal";


    public IntegerProperty getScore() {
        return mario.getScore();
    }
    public IntegerProperty getVie() {
        return mario.getVie();
    }

    public void demarrerJeu(Stage stage, String mode) throws IOException, InterruptedException {

        primaryStage = stage;
        jeu = new Pane();
        BorderPane root = new BorderPane();
        mario = new Mario(20, -10, 30, 30);
        Echelle echelle1 = new Echelle(400, 485, 25, 80);
        Echelle echelle2 = new Echelle(200, 408, 25, 80);
        Echelle echelle3 = new Echelle(500, 331, 25, 80);
        Echelle echelle4 = new Echelle(100, 255, 25, 80);
        Echelle echelle5 = new Echelle(500, 177, 25, 80);
        Echelle echelle6 = new Echelle(320, 100, 25, 80);

        EchelleBroken echelleBroken1 = new EchelleBroken(300, 331, 25, 80);
        EchelleBroken echelleBroken2 = new EchelleBroken(240, 177, 25, 80);

        DonkeyKong dk = new DonkeyKong(60, 80, 100, 100);
        Fond fond = new Fond(0, 0, 600, 600);
        Tonneaux tonneau1 = new Tonneaux(20, -10, 20, 20);
        Tonneaux tonneau2 = new Tonneaux(20, -10, 20, 20);
        Tonneaux tonneau3 = new Tonneaux(20, -10, 20, 20);
        Tonneaux tonneau4 = new Tonneaux(20, -10, 20, 20);
        Tonneaux tonneau5 = new Tonneaux(20, -10, 20, 20);
        tonneau1.setLayoutY(-30);
        tonneau2.setLayoutY(-30);
        tonneau3.setLayoutY(-30);
        tonneau4.setLayoutY(-30);
        tonneau5.setLayoutY(-30);


        mario.setLayoutX(20 * 10);
        mario.setLayoutY(545);

        Label score = new Label("Score : 0");
        score.setFont(new Font("Arial", 20));
        score.setTextFill(Color.WHITE);
        score.setLayoutX(500);
        score.setLayoutY(30);

        Label vie = new Label("Vie : 3");
        vie.setTextFill(Color.WHITE);
        vie.setLayoutX(500);
        vie.setLayoutY(50);
        mario.getVie().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                vie.setText("Vie : " + mario.getVie().getValue());
            }
        });

        mario.getScore().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                score.setText("Score : " + mario.getScore().getValue().toString());
            }
        });

        // panneau du jeu
        jeu.setPrefSize(600, 600);
        // Fond
        jeu.getChildren().add(fond);
        // Echelle
        jeu.getChildren().addAll(echelle1, echelle2, echelle3, echelle4, echelle5, echelle6);
        // EchelleBroken
        jeu.getChildren().addAll(echelleBroken1, echelleBroken2);
        // Perso
        jeu.getChildren().addAll(mario, dk);
        // Tonneaux
        jeu.getChildren().addAll(tonneau1, tonneau2, tonneau3, tonneau4, tonneau5);
        // Score
        jeu.getChildren().add(score);
        // Vie
        jeu.getChildren().add(vie);

        System.out.println(echelle1.getLayoutX());

        //////////////////////// Echelle
        // Attribution des coordonnées etc des échelles
        echelles = new ArrayList<>();
        echelles.addAll(Arrays.asList(echelle1, echelle2, echelle3, echelle4, echelle5, echelle6));

        // Attribution des coordonnées des echelles
        ArrayList<Double> coordonneesEchelle1 = new ArrayList<>();
        coordonneesEchelle1.add(380.0); // x
        coordonneesEchelle1.add(468.0); // y
        ArrayList<Double> coordonneesEchelle2 = new ArrayList<>();
        coordonneesEchelle2.add(180.0);
        coordonneesEchelle2.add(391.0);
        ArrayList<Double> coordonneesEchelle3 = new ArrayList<>();
        coordonneesEchelle3.add(475.0);
        coordonneesEchelle3.add(314.0);
        ArrayList<Double> coordonneesEchelle4 = new ArrayList<>();
        coordonneesEchelle4.add(75.0);
        coordonneesEchelle4.add(237.0);
        ArrayList<Double> coordonneesEchelle5 = new ArrayList<>();
        coordonneesEchelle5.add(475.0);
        coordonneesEchelle5.add(160.0);
        ArrayList<Double> coordonneesEchelle6 = new ArrayList<>();
        coordonneesEchelle6.add(300.0);
        coordonneesEchelle6.add(83.0);

        ArrayList<ArrayList<Double>> coordonneesEchelles = new ArrayList<>(Arrays.asList(coordonneesEchelle1, coordonneesEchelle2, coordonneesEchelle3, coordonneesEchelle4, coordonneesEchelle5, coordonneesEchelle6));

        //////////////////////// EchelleBroken
        echellesBrokens = new ArrayList<>(Arrays.asList(echelleBroken1, echelleBroken2));

        ArrayList<Double> coordonneesEchelleBroken1 = new ArrayList<>();
        coordonneesEchelleBroken1.add(275.0);
        coordonneesEchelleBroken1.add(314.0);
        ArrayList<Double> coordonneesEchelleBroken2 = new ArrayList<>();
        coordonneesEchelleBroken2.add(220.0);
        coordonneesEchelleBroken2.add(160.0);

        coordonneesEchelles.addAll(Arrays.asList(coordonneesEchelleBroken1, coordonneesEchelleBroken2));

        ////////////////////////////////////////////////

        // Tonneaux
        tonneaux = new ArrayList<>(Arrays.asList(tonneau1, tonneau2, tonneau3, tonneau4, tonneau5));
        if (!isPause) {
            System.out.println("je lance le premier tonneau !");
            tonneau1.moveTonneaux(coordonneesEchelles, dk);
            tonneau1.setLayoutY(160);
            dk.lance(tonneau1);
        }
        final IntegerProperty i = new SimpleIntegerProperty(0);
        final int[] x = {1};
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6), event -> {
            i.set(i.get() + 1);
            if (x[0] < 5) {
                tonneaux.get(x[0]).moveTonneaux(coordonneesEchelles, dk);
                tonneaux.get(x[0]).setLayoutY(160);
                dk.lance(tonneaux.get(x[0]));
                //System.out.println("dedans");
                x[0]++;
            } else {
                //System.out.println("plus dedans");
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //replacer les tonneaux
        //empecher le jeu de continuer
        AnimationTimer collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (mario.collisionTonneaux(tonneaux) == -1 && !isPause) {
                    if(mario.getVie().getValue() > 1){
                        isPause = true;
                        System.out.println(isPause);
                        mario.setLayoutX(20 * 10);
                        mario.setLayoutY(545);
                        isPause = false;
                        getVie().setValue(getVie().getValue() - 1);
                    }else{
                        for (Tonneaux tonneau : tonneaux) {
                            tonneau.setLayoutX(0);
                            tonneau.setLayoutY(-30);
                            getScore().setValue(0);
                        }
                        isPause = true;
                        System.out.println(isPause);
                        mario.setLayoutX(20 * 10);
                        mario.setLayoutY(545);
                        supprimerElements(jeu, tonneaux, echelles, echellesBrokens, mario, dk);
                        //empecher le jeu de continuer
                        timeline.stop();
                        primaryStage.close();
                        vuesPerdu.screenLose();
                    }
                } else if (mario.collisionTonneaux(tonneaux) == 1) {
                    System.out.println("+1");

                }

                //mario.getLayoutX()==235 && mario.getLayoutY()==545
                //mario.getLayoutX() == 305 && mario.getLayoutY() == 94|| mario.getLayoutX() == 300 && mario.getLayoutY() == 94|| mario.getLayoutX() == 295 && mario.getLayoutY() == 94|| mario.getLayoutX() == 290 && mario.getLayoutY() == 94
                if (mario.getLayoutX() == 305 && mario.getLayoutY() == 94 || mario.getLayoutX() == 300 && mario.getLayoutY() == 94 || mario.getLayoutX() == 295 && mario.getLayoutY() == 94 || mario.getLayoutX() == 290 && mario.getLayoutY() == 94) {
                    if (mario.getVie().getValue() > 1){
                        mario.setLayoutX(20 * 10);
                        mario.setLayoutY(545);
                        getScore().setValue(getScore().getValue() + 1000);

                    }else{
                        isPause = true;
                        mario.setLayoutX(20 * 10);
                        mario.setLayoutY(545);
                        supprimerElements(jeu, tonneaux, echelles, echellesBrokens, mario, dk);
                        primaryStage.close();
                        vueGagne.screenWin(mario.getScore());
                    }
                }
            }
        };
        // Fin Start Game //
        collisionTimer.start();
        root.setCenter(jeu);
        s = new Scene(root);
        move(mario, echelles, echellesBrokens, coordonneesEchelles);
        stage.setScene(s);
        stage.show();
    }

    public void restartGame(Stage stage) throws IOException, InterruptedException {
        System.out.println("restart");
        stage.close();
        supprimerElements(jeu, tonneaux, echelles, echellesBrokens, mario, dk);
        mario.setLayoutX(20 * 10);
        mario.setLayoutY(545);
        sleep(1000);
        //start(new Stage());
    }


    public void supprimerElements(Pane jeu, ArrayList<Tonneaux> tonneaux, ArrayList<Echelle> echelles, ArrayList<EchelleBroken> echellesBrokens, Mario mario, DonkeyKong dk) {
        tonneaux = null;
        echelles = null;
        echellesBrokens = null;
        mario = null;
        dk = null;
        jeu = null;
    }

    /**
     * Méthode qui permet le mouvement de Mario
     *
     * @param mario
     * @param echelles
     * @param echellesBrokens
     * @param coordonneesEchelles
     */
    private void move(Mario mario, ArrayList<Echelle> echelles, ArrayList<EchelleBroken> echellesBrokens, ArrayList<ArrayList<Double>> coordonneesEchelles) {
        s.setOnKeyPressed((KeyEvent event) -> {
            mario.tomberEtage();
            switch (event.getCode()) {
                case UP:
                    //mario.directionHaut();
                    //System.out.println("X : " + mario.getLayoutX());
                    //System.out.println("Y : " + mario.getLayoutY() + "\n");
                    //System.out.println(mario.collisionEchelle(echelles));
                    //System.out.println(echelles);
                    if (mario.collisionEchelle(echelles) && !(mario.estEn(coordonneesEchelles))) {
                        mario.directionHaut();
                        mario.setEstSurEchelle(true);
                    }
                    if (mario.collisionEchelleBroken(echellesBrokens) && !(mario.estEnBroken(coordonneesEchelles))) {
                        mario.directionHaut();
                        mario.setEstSurEchelle(true);
                    }
                    break;
                case LEFT:
                    if (!mario.estDansEchelle(coordonneesEchelles)) {
                        mario.directionGauche();
                        mario.setEstSurEchelle(false);
                    }
                    break;
                case RIGHT:
                    if (!mario.estDansEchelle(coordonneesEchelles)) {
                        mario.directionDroite(s.getWidth());
                        mario.setEstSurEchelle(false);
                    }
                    break;
                case DOWN:
                    if (mario.collisionEchelle(echelles) && !(mario.estDansBasEchelle(coordonneesEchelles))) {
                        mario.directionBas(s.getHeight());
                    }
                    if (mario.collisionEchelleBroken(echellesBrokens) && !(mario.estDansBasEchelle(coordonneesEchelles))) {
                        mario.directionBas(s.getHeight());
                    }
                    mario.setEstSurEchelle(false);
                    break;
                case SPACE:
                    if (!mario.isEstEnSaut()) {
                        Son.jump();
                        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(0.8));
                        mario.jump();
                        mario.setaEuSonScore(false);
                        pause.play();
                        pause.setOnFinished(event1 -> mario.atterir());
                        break;
                    }

                case A:
                    System.out.println(mario.getLayoutX());
                    System.out.println(mario.getLayoutY());
                    System.out.println(mario.getScore());
            }
        });
    }

    public String getMode() {
        return mode;
    }
}
