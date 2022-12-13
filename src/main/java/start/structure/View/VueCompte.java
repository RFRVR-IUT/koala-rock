package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import start.structure.Model.*;
import start.structure.metier.manager.PlayerManager;
import start.structure.stockage.Session;

public class VueCompte extends Stage {

    public VueCompte() {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 950, 650);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");

        Label label = new Label(Session.getInstance().getLogin());
        System.out.println(Session.getInstance().getLogin());
        label.getStyleClass().add("nomJeu");
        label.setLayoutX(25);
        label.setLayoutY(25);

        //chemp de modification du mot de passe avec un bouton de validation
        Label labelMotDePasse = new Label("Mot de passe");
        labelMotDePasse.getStyleClass().add("LabelConnexionField");
        labelMotDePasse.setLayoutX(50);
        labelMotDePasse.setLayoutY(200);
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("TextFieldConnexion");
        passwordField.setLayoutX(50);
        passwordField.setLayoutY(225);


        Label labelMotDePasse2 = new Label("Valider mot de passe");
        labelMotDePasse2.getStyleClass().add("LabelConnexionField");
        labelMotDePasse2.setLayoutX(50);
        labelMotDePasse2.setLayoutY(300);
        PasswordField passwordField2 = new PasswordField();
        passwordField2.getStyleClass().add("TextFieldConnexion");
        passwordField2.setLayoutX(50);
        passwordField2.setLayoutY(325);

        Button buttonConnexion = new Button("Modifier");
        buttonConnexion.getStyleClass().add("buttonConnexion");
        buttonConnexion.setLayoutX(50);
        buttonConnexion.setLayoutY(400);

        Label labelErreur = new Label();
        labelErreur.getStyleClass().add("LabelError");
        labelErreur.setLayoutX(50);
        labelErreur.setLayoutY(450);

            buttonConnexion.setOnAction(event -> {
                if (passwordField.getText() != "" && passwordField2.getText() != "") {
                    if (passwordField.getText().equals(passwordField2.getText())) {
                        PlayerManager.getInstance().updatePlayer(Session.getInstance().getLogin(), passwordField.getText());
                        labelErreur.setText("Mot de passe modifié");
                    } else {
                        labelErreur.setText("Les mots de passe ne correspondent pas");
                    }
                } else {
                    labelErreur.setText("Veuillez remplir les champs");
                }
            });

        pane.getChildren().addAll(label, labelMotDePasse, passwordField, labelMotDePasse2, passwordField2, buttonConnexion);
        pane.getChildren().add(labelErreur);

        setTitle("Paramètre");
        setResizable(false);
        setScene(scene);

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");
    }
}
