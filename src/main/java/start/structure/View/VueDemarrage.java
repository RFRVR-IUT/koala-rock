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

public class VueDemarrage {

    private VueJeu vueJeu = new VueJeu();


    public void screenStart() {
        Stage stage = new Stage();
        stage.setTitle("Koala Rock");
        stage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        borderPane.setCenter(pane);

        pane.setStyle("-fx-border-color: green ;");
        borderPane.setStyle("-fx-background-color: transparent ;");


        Scene scene = new Scene(borderPane, 1280, 720);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");
        stage.setScene(scene);
        stage.show();


        Label nameGame = new Label("Koala Rock");
        nameGame.getStyleClass().add("nameGame");
        nameGame.setLayoutX(355);
        nameGame.setLayoutY(100);

        Button demarrerPartie = new Button("Mode Classic");
        demarrerPartie.getStyleClass().add("buttonEcran");
        demarrerPartie.setLayoutX(541);
        demarrerPartie.setLayoutY(370);

        Button demarrerInfinit = new Button("Mode Infini");
        demarrerInfinit.getStyleClass().add("buttonEcran");
        demarrerInfinit.setLayoutX(554);
        demarrerInfinit.setLayoutY(450);

        Label menuScreen = new Label();
        Image image = new Image("file:src/main/resources/ImageMenu.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(720);
        imageView.setFitWidth(1280);
        menuScreen.setGraphic(imageView);

        //////////////// Button ///////////////////////

        Button button_Bas = new Button();
        ImageView image_Bas = new ImageView("file:src/main/resources/Button/Button_bas.png");
        image_Bas.setFitHeight(50);
        image_Bas.setFitWidth(50);
        button_Bas.setGraphic(image_Bas);
        button_Bas.getStyleClass().add("button_Action");
        button_Bas.setLayoutX(225);
        button_Bas.setLayoutY(480);

        Button button_Haut = new Button();
        ImageView image_Haut = new ImageView("file:src/main/resources/Button/Button_haut.png");
        image_Haut.setFitHeight(50);
        image_Haut.setFitWidth(50);
        button_Haut.setGraphic(image_Haut);
        button_Haut.getStyleClass().add("button_Action");
        button_Haut.setLayoutX(225);
        button_Haut.setLayoutY(415);

        Button button_Gauche = new Button();
        ImageView image_Gauche = new ImageView("file:src/main/resources/Button/Button_gauche.png");
        image_Gauche.setFitHeight(50);
        image_Gauche.setFitWidth(50);
        button_Gauche.setGraphic(image_Gauche);
        button_Gauche.getStyleClass().add("button_Action");
        button_Gauche.setLayoutX(155);
        button_Gauche.setLayoutY(480);

        Button button_Droite = new Button();
        ImageView image_Droite = new ImageView("file:src/main/resources/Button/Button_droite.png");
        image_Droite.setFitHeight(50);
        image_Droite.setFitWidth(50);
        button_Droite.setGraphic(image_Droite);
        button_Droite.getStyleClass().add("button_Action");
        button_Droite.setLayoutX(296);
        button_Droite.setLayoutY(480);

        Button button_Espace = new Button();
        ImageView image_Espace = new ImageView("file:src/main/resources/Button/Button_espace.png");
        image_Espace.setFitHeight(50);
        image_Espace.setFitWidth(200);
        button_Espace.setGraphic(image_Espace);
        button_Espace.getStyleClass().add("button_Action");
        button_Espace.setLayoutX(150);
        button_Espace.setLayoutY(550);

        //////////////// End Button ///////////////////////


        pane.getChildren().add(menuScreen);
        pane.getChildren().add(demarrerPartie);
        pane.getChildren().add(demarrerInfinit);
        pane.getChildren().add(nameGame);
        pane.getChildren().addAll(button_Bas, button_Haut, button_Gauche, button_Droite, button_Espace);


        demarrerPartie.setOnMouseClicked(event -> {
            try {
                vueJeu.demarrerJeu(stage, "Normal");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        demarrerInfinit.setOnMouseClicked(event -> {
            try {
                vueJeu.demarrerJeu(stage, "Infini");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
