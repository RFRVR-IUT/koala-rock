package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class VueFinInfiniPartie {
    private VueJeu vueJeu;


    public void screenLose(int scoreGame) {

        if (vueJeu == null) {
            vueJeu = new VueJeu();
        }
        Stage stage = new Stage();
        stage = stage;
        stage.setTitle("Koala Rock");
        stage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        borderPane.setCenter(pane);

        Scene scene = new Scene(borderPane, 1280, 720);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");
        stage.setScene(scene);
        stage.show();

        Label nameGame = new Label("Fin de la partie");
        nameGame.getStyleClass().add("nameGame");
        nameGame.setLayoutX(226);
        nameGame.setLayoutY(80);

        Label score = new Label("Score : " + scoreGame);
        score.getStyleClass().add("nameGame");
        score.setLayoutX(226);
        score.setLayoutY(180);

        Button recommencer = new Button("Recommencer");
        recommencer.getStyleClass().add("buttonEcran");
        recommencer.setLayoutX(475);
        recommencer.setLayoutY(570);

        Button quitter = new Button("Quitter");
        quitter.getStyleClass().add("buttonEcran");
        quitter.setLayoutX(695);
        quitter.setLayoutY(570);

        Label deadScreen = new Label();
        Image image = new Image("file:src/main/resources/ImageMenu.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(720);
        imageView.setFitWidth(1280);
        deadScreen.setGraphic(imageView);
        pane.getChildren().add(deadScreen);

        pane.getChildren().add(recommencer);
        pane.getChildren().add(score);
        pane.getChildren().add(quitter);
        pane.getChildren().add(nameGame);

        Stage finalStage = stage;
        recommencer.setOnMouseClicked(event -> {
            try {
                vueJeu.demarrerJeu(finalStage, vueJeu.getMode());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        quitter.setOnMouseClicked(event -> {
            finalStage.close();
            System.exit(0);
        });
    }
}
