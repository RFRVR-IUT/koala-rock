package start.structure;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class StartGame extends Application {
    private Scene s;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Mario mario = new Mario(20, 0, 20, 20);
        Echelle echelle1 = new Echelle(50, 100, 20, 50, 0);
        Echelle echelle2 = new Echelle(200, 100, 20, 50, 0);
        mario.setLayoutX(20 * 10);
        mario.setLayoutY(130);

        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(echelle1);
        jeu.getChildren().add(echelle2);
        jeu.getChildren().add(mario);

        System.out.println(echelle1.getLayoutX());

        ArrayList<Echelle> echelles = new ArrayList<>();
        ArrayList<Double> coordonneesEchelles = new ArrayList<>();
        echelles.add(echelle1);
        echelles.add(echelle2);


        root.setCenter(jeu);
        s = new Scene(root);
        move(mario, echelles);
        stage.setScene(s);
        stage.show();

    }

    private void move(Mario p, ArrayList<Echelle> echelles) {
        s.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    if(p.collisionEchelle(echelles)) {
                        p.directionHaut();
                    }
                    break;
                case LEFT:
                    p.directionGauche();
                    break;
                case RIGHT:
                    p.directionDroite(s.getWidth());
                    break;
                case DOWN:
                    if(p.collisionEchelle(echelles)) {
                        p.directionBas(s.getHeight());
                    }
                    break;
                case SPACE:
                    p.jump();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    p.atterir();
                    break;
            }

        });
    }

    public static void main(String[] args) {
        launch();
    }
}