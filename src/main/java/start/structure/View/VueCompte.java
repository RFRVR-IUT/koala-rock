package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import start.structure.RessourcesAccess;
import start.structure.metier.entite.Score;
import start.structure.metier.manager.PlayerManager;
import start.structure.metier.manager.ScoreManager;
import start.structure.stockage.Session;

import java.io.IOException;
import java.util.Objects;

public class VueCompte {

    public void affichageVueCompte(Stage stage) throws IOException {
        int varScore = 0;
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1280, 720);
        scene.getStylesheets().add(String.valueOf(RessourcesAccess.class.getResource("css/style.css")));

        Line line = new Line();
        line.setStartX(400.0f);
        line.setStartY(150.0f);
        line.setEndX(400.0f);
        line.setEndY(500.0f);
        line.setStrokeWidth(2);
        line.setStroke(Color.WHITE);

        Label label = new Label(Session.getInstance().getLogin());
        label.getStyleClass().add("nomJeu");
        label.setLayoutX(55);
        label.setLayoutY(25);

        Label labelModificationMDP = new Label("Modification du mot de passe");
        labelModificationMDP.getStyleClass().add("LabelConnexionField");
        labelModificationMDP.setLayoutX(50);
        labelModificationMDP.setLayoutY(130);

        Label labelMotDePasse = new Label("Mot de passe");
        labelMotDePasse.getStyleClass().add("LabelConnexionField");
        labelMotDePasse.setLayoutX(110);
        labelMotDePasse.setLayoutY(200);
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("TextFieldConnexion");
        passwordField.setLayoutX(110);
        passwordField.setLayoutY(225);

        Label labelMotDePasse2 = new Label("Confirmation");
        labelMotDePasse2.getStyleClass().add("LabelConnexionField");
        labelMotDePasse2.setLayoutX(110);
        labelMotDePasse2.setLayoutY(300);
        PasswordField passwordField2 = new PasswordField();
        passwordField2.getStyleClass().add("TextFieldConnexion");
        passwordField2.setLayoutX(110);
        passwordField2.setLayoutY(325);

        Label labelErreur = new Label();
        labelErreur.getStyleClass().add("LabelError");
        labelErreur.setLayoutX(60);
        labelErreur.setLayoutY(470);

        Label labelMeilleurScore = new Label("Meilleurs scores");
        labelMeilleurScore.getStyleClass().add("nomJeu");
        labelMeilleurScore.setLayoutX(630);
        labelMeilleurScore.setLayoutY(75);

        Button buttonModifPassword = new Button("Changer mot de passe");
        buttonModifPassword.getStyleClass().add("btnGrey");
        buttonModifPassword.setLayoutX(83);
        buttonModifPassword.setLayoutY(420);

        Button supprimerCompte = new Button("Supprimer Compte");
        supprimerCompte.getStyleClass().add("btnRed");
        supprimerCompte.setLayoutX(1040);
        supprimerCompte.setLayoutY(600);

        Button deconnexion = new Button("Deconnexion");
        deconnexion.getStyleClass().add("btnGrey");
        deconnexion.setLayoutX(878);
        deconnexion.setLayoutY(600);

        Button boutonRetour = new Button("Retour");
        boutonRetour.getStyleClass().add("btnGrey");
        boutonRetour.setLayoutX(150);
        boutonRetour.setLayoutY(600);

        Button menu = new Button("Menu");
        menu.getStyleClass().add("btnGrey");
        menu.setLayoutX(50);
        menu.setLayoutY(600);

        // Retour sur le menu
        menu.setOnAction(event -> {
            VueMenu vueMenu = new VueMenu();
            vueMenu.demarrerMenu(stage);
        });

        // Afficher vue parametre
        boutonRetour.setOnAction(event -> {
            VueParametre vueParametre = new VueParametre();
            try {
                vueParametre.affichageVueParametre(stage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        // Modification du mot de passe
        buttonModifPassword.setOnAction(event -> {
            if (!Objects.equals(passwordField.getText(), "") && !Objects.equals(passwordField2.getText(), "")) {
                if (passwordField.getText().equals(passwordField2.getText())) {
                    PlayerManager.getInstance().updatePlayer(Session.getInstance().getLogin(), passwordField.getText());
                    labelErreur.setText("Mot de passe modifié");
                } else {
                    labelErreur.setText("Les mots de passe" + "\n" + "ne correspondent pas");
                }
            } else {
                labelErreur.setText("Veuillez remplir les champs");
            }
        });

        // Affichage des 10 meilleurs joueurs avec date + Espacement entre les scores
        for (Score score : ScoreManager.getInstance().getScores()) {
            if (score.getLogin() != null && score.getLogin().equals(Session.getInstance().getLogin())) {

                Label labelScore = new Label(score.getScore() + "");
                labelScore.getStyleClass().add("LabelConnexionField");
                labelScore.setLayoutX(450);
                labelScore.setLayoutY(150 + varScore * 35);

                Label labelDate = new Label(score.getHorodatage().toLocalDateTime().getDayOfMonth() + "-" + score.getHorodatage().toLocalDateTime().getMonth() + "-" + score.getHorodatage().toLocalDateTime().getYear() + " " + score.getHorodatage().toLocalDateTime().getHour() + "h" + score.getHorodatage().toLocalDateTime().getMinute());
                labelDate.getStyleClass().add("LabelConnexionField");
                labelDate.setLayoutX(600);
                labelDate.setLayoutY(150 + varScore * 35);

                Label place = new Label((varScore + 1) + ".");
                place.getStyleClass().add("LabelConnexionField");
                place.setLayoutX(400);
                place.setLayoutY(150 + varScore * 35);

                pane.getChildren().addAll(labelScore, labelDate, place);
                varScore++;
            }
        }

        // Suppression du compte
        supprimerCompte.setOnMouseClicked(event -> {
            Label alerte = new Label("Voulez vous vraiment" + "\n" + "supprimer votre compte ?");
            alerte.getStyleClass().add("LabelError");
            alerte.setLayoutX(520);
            alerte.setLayoutY(250);

            Rectangle rectangle = new Rectangle();
            rectangle.setX(500);
            rectangle.setY(200);
            rectangle.setWidth(300);
            rectangle.setHeight(200);

            rectangle.setArcHeight(50);
            rectangle.setArcWidth(50);
            rectangle.setFill(Color.BLACK);
            rectangle.setEffect(new DropShadow(10, Color.WHITE));

            Button oui = new Button("Oui");
            oui.getStyleClass().add("btnGrey");
            oui.setLayoutX(520);
            oui.setLayoutY(325);

            Button non = new Button("Non");
            non.getStyleClass().add("btnRed");
            non.setLayoutX(720);
            non.setLayoutY(325);

            oui.setOnAction(e -> {
                PlayerManager.getInstance().deletePlayer(Session.getInstance().getLogin());
                Session.getInstance().disconnect();
                VueMenu vueMenu = new VueMenu();
                vueMenu.demarrerMenu(stage);
                pane.getChildren().removeAll(oui, non, rectangle, alerte);
            });
            non.setOnAction(e -> {
                pane.getChildren().removeAll(oui, non, rectangle, alerte);
            });

            pane.getChildren().addAll(rectangle, oui, non, alerte);
        });

        // Deconnection du compte
        deconnexion.setOnMouseClicked(event -> {
            Session.getInstance().disconnect();
            VueMenu vueMenu = new VueMenu();
            vueMenu.demarrerMenu(stage);
        });

        // Lors du click sur le bouton quitter de la fenetre (affichage confirmation)
        stage.setOnCloseRequest(event -> {
            event.consume();
            Label alerte = new Label("Voulez vous vraiment \n" + "quitter le jeu ?");
            alerte.getStyleClass().add("LabelError");
            alerte.setLayoutX(520);
            alerte.setLayoutY(250);

            Rectangle rectangle = new Rectangle();
            rectangle.setX(500);
            rectangle.setY(200);
            rectangle.setWidth(300);
            rectangle.setHeight(200);

            rectangle.setArcHeight(50);
            rectangle.setArcWidth(50);
            rectangle.setFill(Color.BLACK);
            rectangle.setEffect(new DropShadow(10, Color.WHITE));

            Button oui = new Button("Oui");
            oui.getStyleClass().add("btnGrey");
            oui.setLayoutX(520);
            oui.setLayoutY(325);

            Button non = new Button("Non");
            non.getStyleClass().add("btnRed");
            non.setLayoutX(720);
            non.setLayoutY(325);

            oui.setOnAction(e -> {
                Session.getInstance().disconnect();
                System.exit(0);
            });
            non.setOnAction(e -> {
                pane.getChildren().removeAll(oui, non, rectangle, alerte);
            });
            pane.getChildren().addAll(rectangle, alerte, oui, non);
        });

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");
        pane.getChildren().addAll(label, labelMotDePasse, passwordField, labelMotDePasse2, passwordField2, buttonModifPassword, labelMeilleurScore, boutonRetour, supprimerCompte, deconnexion, menu);
        pane.getChildren().add(labelErreur);
        pane.getChildren().add(labelModificationMDP);
        pane.getChildren().add(line);

        stage.setTitle("Paramètre");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
