package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import start.structure.stockage.Session;

import java.io.IOException;

public class VueDemarrage {

    private final VueJeu vueJeu = new VueJeu();
    private final VueParametre vueParametre = new VueParametre();

    private final VueConnexion vueConnexion = new VueConnexion();
    private final VueMeilleurScore vueMeilleurScore = new VueMeilleurScore();


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
        demarrerPartie.setLayoutY(350);

        Button demarrerInfinit = new Button("Mode Infini");
        demarrerInfinit.getStyleClass().add("buttonEcran");
        demarrerInfinit.setLayoutX(554);
        demarrerInfinit.setLayoutY(430);

        Button connexionRegister = new Button("Connexion/Inscription");
        connexionRegister.getStyleClass().add("buttonEcran");
        connexionRegister.setLayoutX(900);
        connexionRegister.setLayoutY(580);

        Button meilleurScore = new Button("Meilleur Score");
        meilleurScore.getStyleClass().add("buttonEcran");
        meilleurScore.setLayoutX(900);
        meilleurScore.setLayoutY(510);

        Button parametre = new Button("Paramètres");
        parametre.getStyleClass().add("buttonEcran");
        parametre.setLayoutX(555);
        parametre.setLayoutY(540);

        Button monCompte = new Button("Mon Compte");
        monCompte.getStyleClass().add("buttonEcran");
        monCompte.setLayoutX(100);
        monCompte.setLayoutY(580);


        Label menuScreen = new Label();
        Image image = new Image("file:src/main/resources/ImageMenu.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(720);
        imageView.setFitWidth(1280);
        menuScreen.setGraphic(imageView);

        Label labelError = new Label();
        labelError.getStyleClass().add("LabelError");
        labelError.setLayoutX(100);
        labelError.setLayoutY(500);

        pane.getChildren().add(menuScreen);
        pane.getChildren().add(demarrerPartie);
        pane.getChildren().add(demarrerInfinit);
        pane.getChildren().add(parametre);
        pane.getChildren().add(nameGame);
        pane.getChildren().add(connexionRegister);
        pane.getChildren().add(meilleurScore);
        pane.getChildren().add(monCompte);
        pane.getChildren().add(labelError);

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

        parametre.setOnMouseClicked(event -> {
            vueParametre.show();
        });

        connexionRegister.setOnMouseClicked(event -> {
            vueConnexion.show();
        });

        meilleurScore.setOnMouseClicked(event -> {
            vueMeilleurScore.show();
        });

        monCompte.setOnMouseClicked(event -> {
            labelError.setText("");
            if (Session.getInstance().isConnected()) {
                VueCompte vueCompte = new VueCompte();
                vueCompte.show();
            } else {
                labelError.setText("Vous devez être connecté \n " +
                        "pour accéder à votre compte");
            }
        });

    }
}
