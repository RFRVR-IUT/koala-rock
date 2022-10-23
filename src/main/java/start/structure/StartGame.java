package start.structure;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class StartGame extends Application {
    private Scene Deplacement;
    private Stage primaryStage;
    private Scene s;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Mario mario = new Mario(20, -10, 30, 30);
        Echelle echelle1 = new Echelle(400, 485, 25, 80, 0);
        Echelle echelle2 = new Echelle(200, 408, 25, 80, 0);
        Fond fond = new Fond(0,0, 600, 600);
        Tonneaux tonneau1 = new Tonneaux(20, 130, 20, 20);

        mario.setLayoutX(20 * 10);
        mario.setLayoutY(545);

        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(600, 600);
        jeu.getChildren().add(fond);
        jeu.getChildren().add(echelle1);
        jeu.getChildren().add(echelle2);
        jeu.getChildren().add(mario);
        jeu.getChildren().add(tonneau1);

        System.out.println(echelle1.getLayoutX());

        //Attribution des coordonnées etc des échelles
        ArrayList<Echelle> echelles = new ArrayList<>();
        echelles.add(echelle1);
        echelles.add(echelle2);
        ArrayList<ArrayList<Double>> coordonneesEchelles = new ArrayList<>();
        ArrayList<Double> coordonneesEchelle1 = new ArrayList<>();
        coordonneesEchelle1.add(380.0); //x
        coordonneesEchelle1.add(465.0);  //y
        ArrayList<Double> coordonneesEchelle2 = new ArrayList<>();
        coordonneesEchelle2.add(180.0);
        coordonneesEchelle2.add(385.0);
        coordonneesEchelles.add(coordonneesEchelle1);
        coordonneesEchelles.add(coordonneesEchelle2);

        //Tonneaux (faudra penser à essayer de le foutre dans la classe Tonneaux nan ?)
        PauseTransition pause = new PauseTransition();
        pause.setDuration(javafx.util.Duration.seconds(0.1));
        pause.setOnFinished(event -> {
            if (tonneau1.getLayoutX() < 640) {
                tonneau1.directionDroite(640);
                pause.play();
            }
        });
        pause.play();

        root.setCenter(jeu);
        s = new Scene(root);
        move(mario, echelles, coordonneesEchelles);
        stage.setScene(s);
        stage.show();
    }

    private void move(Mario p, ArrayList<Echelle> echelles, ArrayList<ArrayList<Double>> coordonneesEchelles) {
        s.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    System.out.println("X : " + p.getLayoutX());
                    System.out.println("Y : " + p.getLayoutY() + "\n");
                    if (p.collisionEchelle(echelles) && !(p.estEn(coordonneesEchelles))) {
                        p.directionHaut();
                    }
                    break;
                case LEFT:
                    if (!p.estDansEchelle(coordonneesEchelles)) {
                        p.directionGauche();
                    }
                    break;
                case RIGHT:
                    if (!p.estDansEchelle(coordonneesEchelles)) {
                        p.directionDroite(s.getWidth());
                    }
                    break;
                case DOWN:
                    if(p.collisionEchelle(echelles) && !(p.estDansBasEchelle(coordonneesEchelles))) {
                        p.directionBas(s.getHeight());
                    }
                    break;
                case SPACE:
                    if (!p.isEstEnSaut()) {
                    PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(0.5));
                    p.jump();
                    pause.play();
                    pause.setOnFinished(event1 -> p.atterir());
                    break;
                }
            }

        });
    }
    public static void main(String[] args) {
        launch();
    }
}