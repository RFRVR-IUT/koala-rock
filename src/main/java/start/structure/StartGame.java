package start.structure;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class StartGame extends Application {
    private Scene Deplacement, s;

    private Stage primaryStage;

    private TimerTask timerTask;

    /**
     * Starter du jeu
     * @param stage
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        //TimerT.tempsRestant();

        BorderPane root = new BorderPane();
        Mario mario = new Mario(20, -10, 30, 30);
        Echelle echelle1 = new Echelle(400, 485, 25, 80, 0);
        Echelle echelle2 = new Echelle(200, 408, 25, 80, 0);
        Echelle echelle3 = new Echelle(500, 331, 25, 80, 0);
        Echelle echelle4 = new Echelle(100, 255, 25, 80, 0);
        Echelle echelle5 = new Echelle(500, 177, 25, 80, 0);

        EchelleBroken eb1 = new EchelleBroken(300, 331, 25, 80);
        EchelleBroken eb2 = new EchelleBroken(240, 177, 25, 80);

        DonkeyKong dk = new DonkeyKong(60, 80, 100, 100);
        Fond fond = new Fond(0, 0, 600, 600);
        Tonneaux tonneau1 = new Tonneaux(20, -10, 20, 20);
        tonneau1.setLayoutY(160);
        // décompte du temps
        // Tonneaux tonneau2 = new Tonneaux(20, 160, 20, 20);

        mario.setLayoutX(20 * 10);
        mario.setLayoutY(545);

        // panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(600, 600);
        jeu.getChildren().add(fond);
        jeu.getChildren().add(echelle1);
        jeu.getChildren().add(echelle2);
        jeu.getChildren().add(echelle3);
        jeu.getChildren().add(echelle4);
        jeu.getChildren().add(echelle5);
        jeu.getChildren().add(eb1);
        jeu.getChildren().add(eb2);
        jeu.getChildren().add(mario);
        jeu.getChildren().add(dk);
        jeu.getChildren().add(tonneau1);

        System.out.println(echelle1.getLayoutX());

        // Attribution des coordonnées etc des échelles
        ArrayList<Echelle> echelles = new ArrayList<>();
        echelles.add(echelle1);
        echelles.add(echelle2);
        echelles.add(echelle3);
        echelles.add(echelle4);
        echelles.add(echelle5);
        ArrayList<ArrayList<Double>> coordonneesEchelles = new ArrayList<>();
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

        coordonneesEchelles.add(coordonneesEchelle1);
        coordonneesEchelles.add(coordonneesEchelle2);
        coordonneesEchelles.add(coordonneesEchelle3);
        coordonneesEchelles.add(coordonneesEchelle4);
        coordonneesEchelles.add(coordonneesEchelle5);

        // Tonneaux (faudra penser à essayer de le foutre dans la classe Tonneaux nan ?)
        moveTonneaux(tonneau1, coordonneesEchelles, dk);

        //lance(tonneau1, dk);

        root.setCenter(jeu);
        s = new Scene(root);
        move(mario, echelles, coordonneesEchelles);
        stage.setScene(s);
        stage.show();
    }

    /**
     * Méthode qui permet le mouvement de Mario
     * @param mario
     * @param echelles
     * @param coordonneesEchelles
     */
    private void move(Mario mario, ArrayList<Echelle> echelles, ArrayList<ArrayList<Double>> coordonneesEchelles) {
        s.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    System.out.println("X : " + mario.getLayoutX());
                    System.out.println("Y : " + mario.getLayoutY() + "\n");
                    if (mario.collisionEchelle(echelles) && !(mario.estEn(coordonneesEchelles))) {
                        mario.directionHaut();
                    }
                    break;
                case LEFT:
                    if (!mario.estDansEchelle(coordonneesEchelles)) {
                        mario.directionGauche();
                    }
                    break;
                case RIGHT:
                    if (!mario.estDansEchelle(coordonneesEchelles)) {
                        mario.directionDroite(s.getWidth());
                    }
                    break;
                case DOWN:
                    if (mario.collisionEchelle(echelles) && !(mario.estDansBasEchelle(coordonneesEchelles))) {
                        mario.directionBas(s.getHeight());
                    }
                    break;
                case SPACE:
                    if (!mario.isEstEnSaut()) {
                        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(0.5));
                        mario.jump();
                        pause.play();
                        pause.setOnFinished(event1 -> mario.atterir());
                        break;
                    }
            }

        });
    }

    /**
     * Méthode qui permet le mouvement des tonneaux
     * @param tonneaux
     * @param coordonneesEchelles
     * @param dk
     */
    private void moveTonneaux(Tonneaux tonneaux, ArrayList<ArrayList<Double>> coordonneesEchelles, DonkeyKong dk) {
        ArrayList<Double> coordonneesEchelle1 = new ArrayList<>();
        // coordonneesEchelle1.add(76.0);
        // coordonneesEchelle1.add(246.0);
        PauseTransition pause = new PauseTransition();
        pause.setDuration(javafx.util.Duration.seconds(0.005));
        pause.setOnFinished(event -> {
            double chanceTonneauxEchelle = Math.random();
            if ((tonneaux.collisionEchelleTonneau(coordonneesEchelles)) && (chanceTonneauxEchelle < 0.5)) {
                tonneaux.directionBas();
                pause.play();

            }
            // quand le tonneau est en bas de l'échelle -> On relance le tonneau.
            if (tonneaux.getLayoutX() == 70.0 && tonneaux.getLayoutY() == 556.0) {
                dk.lance(tonneaux);
            }

            if (tonneaux.getLayoutX() < -30.0 && tonneaux.getLayoutY() == 556.0) {
                tonneaux.setLayoutY(170.0);
                tonneaux.setLayoutX(120.0);
                moveTonneaux(tonneaux, coordonneesEchelles, dk);
            } else if ((tonneaux.getLayoutX() < 530.0 && tonneaux.getLayoutY() == 170.0) // étage 1 (haut -> bas)
                    || (tonneaux.getLayoutX() < 530.0 && tonneaux.getLayoutY() == 324.0) // étage 3
                    || (tonneaux.getLayoutX() < 530.0 && tonneaux.getLayoutY() == 478.0)) { // étage 5
                if (tonneaux.getLayoutX() < 640) {
                    tonneaux.directionDroite(640);
                    pause.play();
                }
            } else if ((tonneaux.getLayoutX() > 0.0 && tonneaux.getLayoutY() == 248.0) // étage 2
                    || (tonneaux.getLayoutX() > 0.0 && tonneaux.getLayoutY() == 402.0) // étage 4
                    || (tonneaux.getLayoutX() > -40.0 && tonneaux.getLayoutY() == 556.0)) { // étage 6
                tonneaux.directionGauche();
                pause.play();
            } else {
                //System.out.println(tonneaux.getLayoutY());
                tonneaux.directionBas();
                pause.play();
            }
        });
        pause.play();
    }


    public static void main(String[] args) {
        launch();
    }
}