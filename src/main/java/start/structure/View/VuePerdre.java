package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import start.structure.RessourcesAccess;

import java.io.IOException;
import java.util.Objects;

public class VuePerdre {

    private VueJeu vueJeu;


    public void screenLose() {

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
        scene.getStylesheets().add(String.valueOf(RessourcesAccess.class.getResource("css/style.css")));
        stage.setScene(scene);
        stage.show();

        /////////// LABEL ///////////
        Label nameGame = new Label("Vous avez perdu");
        nameGame.getStyleClass().add("nameGame");
        nameGame.setLayoutX(226);
        nameGame.setLayoutY(80);

        Label deadScreen = new Label();
        Image image = new Image(Objects.requireNonNull(RessourcesAccess.class.getResourceAsStream("menu/ImageMenu.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(720);
        imageView.setFitWidth(1280);
        deadScreen.setGraphic(imageView);
        pane.getChildren().add(deadScreen);


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


        pane.getChildren().addAll(nameGame, recommencer, quitter, retourMenu);

        /**
         * Bouton recommencer
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
