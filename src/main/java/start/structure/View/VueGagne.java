package start.structure.View;

import javafx.beans.property.IntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class VueGagne {

    private VueJeu vueJeu;

    public void screenWin(IntegerProperty scoreProperty) {

        if (vueJeu == null) {
            vueJeu = new VueJeu();
        }

        Stage stage = new Stage();
        stage.setTitle("Koala Rock");
        stage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        borderPane.setCenter(pane);

        Scene scene = new Scene(borderPane, 1280, 720);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");
        stage.setScene(scene);
        stage.show();


        /////////// BOUTON ///////////
        Button retourMenu = new Button("Retour au menu");
        retourMenu.getStyleClass().add("buttonEcran");
        retourMenu.setLayoutX(325);
        retourMenu.setLayoutY(570);

        Button recommencer = new Button("Recommencer");
        recommencer.getStyleClass().add("buttonEcran");
        recommencer.setLayoutX(540);
        recommencer.setLayoutY(570);

        Button quitter = new Button("Quitter");
        quitter.getStyleClass().add("buttonEcran");
        quitter.setLayoutX(740);
        quitter.setLayoutY(570);

        /////////// LABEL ///////////
        Label nameGame = new Label("Vous avez gagné");
        nameGame.getStyleClass().add("nameGame");
        nameGame.setLayoutX(226);
        nameGame.setLayoutY(80);

        Label menuScreen = new Label();
        Image image = new Image("file:src/main/resources/enu/ImageMenu.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(720);
        imageView.setFitWidth(1280);
        menuScreen.setGraphic(imageView);
        pane.getChildren().add(menuScreen);

        Label scoreLabel = new Label("Score : " + scoreProperty.getValue());
        scoreLabel.getStyleClass().add("Score_Vie");
        scoreLabel.setLayoutX(565);
        scoreLabel.setLayoutY(200);

        pane.getChildren().addAll(menuScreen, recommencer, quitter, nameGame, scoreLabel, retourMenu);

/**
 * Action du bouton recommencer
 */
        recommencer.setOnMouseClicked(event -> {
            try {
                vueJeu.demarrerJeu(stage, vueJeu.getMode());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        quitter.setOnMouseClicked(event -> {
            stage.close();
            System.exit(0);
        });
        retourMenu.setOnMouseClicked(event -> {
            VueMenu vueMenu = new VueMenu();
            vueMenu.demarrerMenu(stage);
        });
    }
}
