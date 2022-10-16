package start.structure;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Scene s;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Mario mario = new Mario(20, 20, 20, 20);
        mario.setLayoutX(20 * 10);

        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(mario);

        root.setCenter(jeu);
        s = new Scene(root);
        move(mario);
        stage.setScene(s);
        stage.show();
    }

    private void move(Mario p) {
        s.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    p.directionHaut();
                    break;
                case LEFT:
                    p.directionGauche();
                    break;
                case RIGHT:
                    p.directionDroite(s.getWidth());
                    break;
                case DOWN:
                    p.directionBas(s.getHeight());
                    break;
                case SPACE:
                    try {
                        p.jump();
                    } catch (InterruptedException e) {
                        System.out.println("jump stop");
                    }
                    break;
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}