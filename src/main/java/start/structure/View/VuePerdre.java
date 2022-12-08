package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class VuePerdre {

    private VueJeu vueJeu;


    public void screenLose() {

        if (vueJeu == null) {
            vueJeu = new VueJeu();
        }
        Stage stage = new Stage();
        stage = stage;
        stage.setTitle("Koala Kong");
        stage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        borderPane.setCenter(pane);

        Scene scene = new Scene(borderPane, 1084, 610);
        stage.setScene(scene);
        stage.show();

        Label nameGame = new Label("Vous avez perdu");
        nameGame.setFont(new Font("Arial", 70));
        nameGame.setTextFill(Color.WHITE);
        nameGame.setLayoutX(282);
        nameGame.setLayoutY(80);

        Button recommencer = new Button("Recommencer");
        recommencer.setFont(new Font("Arial", 20));
        recommencer.setTextFill(Color.BLACK);
        recommencer.setStyle("-fx-background-radius: 30;");
        recommencer.setLayoutX(415);
        recommencer.setLayoutY(480);

        Button quitter = new Button("Quitter");
        quitter.setFont(new Font("Arial", 20));
        quitter.setTextFill(Color.BLACK);
        quitter.setStyle("-fx-background-radius: 30;");
        quitter.setLayoutX(585);
        quitter.setLayoutY(480);

        Label deadScreen = new Label();
        Image image = new Image("file:src/main/resources/ImageMenu.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(610);
        imageView.setFitWidth(1084);
        deadScreen.setGraphic(imageView);
        pane.getChildren().add(deadScreen);

        pane.getChildren().add(recommencer);
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
