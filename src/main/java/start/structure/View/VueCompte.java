package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import start.structure.metier.entite.Score;
import start.structure.metier.manager.PlayerManager;
import start.structure.metier.manager.ScoreManager;
import start.structure.stockage.Session;

import java.util.List;

public class VueCompte extends Stage {

    public VueCompte() {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 950, 650);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");

        Label label = new Label(Session.getInstance().getLogin());
        System.out.println(Session.getInstance().getLogin());
        label.getStyleClass().add("nomJeu");
        label.setLayoutX(45);
        label.setLayoutY(25);

        //chemp de modification du mot de passe avec un bouton de validation

        Label labelModificationMDP = new Label("Modification du mot de passe");
        labelModificationMDP.getStyleClass().add("LabelConnexionField");
        labelModificationMDP.setLayoutX(25);
        labelModificationMDP.setLayoutY(130);

        Line line = new Line();
        line.setStartX(350.0f);
        line.setStartY(100.0f);
        line.setEndX(350.0f);
        line.setEndY(500.0f);
        line.setStrokeWidth(5);
        line.setStroke(Color.WHITE);

        Label labelMotDePasse = new Label("Mot de passe");
        labelMotDePasse.getStyleClass().add("LabelConnexionField");
        labelMotDePasse.setLayoutX(90);
        labelMotDePasse.setLayoutY(200);
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("TextFieldConnexion");
        passwordField.setLayoutX(90);
        passwordField.setLayoutY(225);


        Label labelMotDePasse2 = new Label("Confirmation");
        labelMotDePasse2.getStyleClass().add("LabelConnexionField");
        labelMotDePasse2.setLayoutX(90);
        labelMotDePasse2.setLayoutY(300);
        PasswordField passwordField2 = new PasswordField();
        passwordField2.getStyleClass().add("TextFieldConnexion");
        passwordField2.setLayoutX(90);
        passwordField2.setLayoutY(325);

        Button buttonConnexion = new Button("Changer mot de passe");
        buttonConnexion.getStyleClass().add("buttonConnexion");
        buttonConnexion.setLayoutX(60);
        buttonConnexion.setLayoutY(400);

        Label labelErreur = new Label();
        labelErreur.getStyleClass().add("LabelError");
        labelErreur.setLayoutX(50);
        labelErreur.setLayoutY(450);

        Button boutonRetour = new Button("Retour");
        boutonRetour.getStyleClass().add("buttonConnexionRetour");
        boutonRetour.setLayoutX(50);
        boutonRetour.setLayoutY(550);

        Button supprimerCompte = new Button("SupprimerCompte");
        supprimerCompte.getStyleClass().add("buttonConnexionRetour");
        supprimerCompte.setLayoutX(710);
        supprimerCompte.setLayoutY(550);

        Button deconnexion = new Button("Deconnexion");
        deconnexion.getStyleClass().add("buttonConnexion");
        deconnexion.setLayoutX(400);
        deconnexion.setLayoutY(550);

        boutonRetour.setOnMouseClicked(event -> {
            this.close();
        });


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


        //10 meilleurs score du joueurs avec la date
        Label labelMeilleurScore = new Label("Meilleurs scores");
        labelMeilleurScore.getStyleClass().add("nomJeu");
        labelMeilleurScore.setLayoutX(400);
        labelMeilleurScore.setLayoutY(75);
        List<Score> scores = ScoreManager.getInstance().getScores();
        int i = 0;
        for (Score score : scores) {
            if (score.getLogin().equals(Session.getInstance().getLogin())) {
                Label labelScore = new Label(score.getScore() + "");
                labelScore.getStyleClass().add("LabelConnexionField");
                labelScore.setLayoutX(450);
                labelScore.setLayoutY(150 + i * 35);
                Label labelDate = new Label(score.getHorodatage() + "");
                labelDate.getStyleClass().add("LabelConnexionField");
                labelDate.setLayoutX(600);
                labelDate.setLayoutY(150 + i * 35);
                Label place = new Label((i + 1) + ".");
                place.getStyleClass().add("LabelConnexionField");
                place.setLayoutX(400);
                place.setLayoutY(150 + i * 35);
                pane.getChildren().addAll(labelScore, labelDate, place);
                i++;
            }
        }

        supprimerCompte.setOnMouseClicked(event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppression du compte");
            alert.setHeaderText("Voulez-vous vraiment supprimer votre compte ?");
            alert.setContentText("Attention, cette action est irréversible");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                PlayerManager.getInstance().deletePlayer(Session.getInstance().getLogin());
                Session.getInstance().disconnect();
                this.close();
            } else if (alert.getResult() == ButtonType.CANCEL) {
                alert.close();
            }
        });

        deconnexion.setOnMouseClicked(event -> {
            Session.getInstance().disconnect();
            this.close();
        });

        pane.getChildren().addAll(label, labelMotDePasse, passwordField, labelMotDePasse2, passwordField2, buttonConnexion, labelMeilleurScore, boutonRetour, supprimerCompte, deconnexion);
        pane.getChildren().add(labelErreur);
        pane.getChildren().add(labelModificationMDP);
        pane.getChildren().add(line);

        setTitle("Paramètre");
        setResizable(false);
        setScene(scene);

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");
    }
}
