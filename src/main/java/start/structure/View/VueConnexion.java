package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import start.structure.metier.entite.AuthPlayer;
import start.structure.metier.manager.PlayerManager;
import start.structure.stockage.Security;
import start.structure.stockage.Session;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static java.lang.constant.ConstantDescs.NULL;

public class VueConnexion extends Stage {
    Pane pane = new Pane();
    Scene scene = new Scene(pane, 950, 650);
    Security security = new Security();

    public VueConnexion() {
        scene.getStylesheets().add("file:src/main/resources/css/style.css");

   /////////////// CONNEXION SCENE ///////////////
        Label label = new Label("Connexion");
        label.getStyleClass().add("labelConnexion");
        label.setLayoutX(50);
        label.setLayoutY(100);

        Label labelPseudo = new Label("Pseudo");
        labelPseudo.getStyleClass().add("LabelConnexionField");
        labelPseudo.setLayoutX(125);
        labelPseudo.setLayoutY(200);

        Label labelMotDePasse = new Label("Mot de passe");
        labelMotDePasse.getStyleClass().add("LabelConnexionField");
        labelMotDePasse.setLayoutX(125);
        labelMotDePasse.setLayoutY(300);

        TextField textFieldPseudo = new TextField();
        textFieldPseudo.getStyleClass().add("TextFieldConnexion");
        textFieldPseudo.setLayoutX(125);
        textFieldPseudo.setLayoutY(225);

        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("TextFieldConnexion");
        passwordField.setLayoutX(125);
        passwordField.setLayoutY(325);

        Button buttonConnexion = new Button("Se connecter");
        buttonConnexion.getStyleClass().add("buttonConnexion");
        buttonConnexion.setLayoutX(130);
        buttonConnexion.setLayoutY(400);

///////////////// INSCRIPTION SCENE ///////////////
        Label labelInscription = new Label("Inscription");
        labelInscription.getStyleClass().add("labelConnexion");
        labelInscription.setLayoutX(500);
        labelInscription.setLayoutY(100);

        Label labelPseudoInscription = new Label("Pseudo");
        labelPseudoInscription.getStyleClass().add("LabelConnexionField");
        labelPseudoInscription.setLayoutX(575);
        labelPseudoInscription.setLayoutY(200);
        Label labelMotDePasseInscription = new Label("Mot de passe");
        labelMotDePasseInscription.getStyleClass().add("LabelConnexionField");
        labelMotDePasseInscription.setLayoutX(575);
        labelMotDePasseInscription.setLayoutY(300);
        Label labelMotDePasseInscription2 = new Label("Confirmer mot de passe");
        labelMotDePasseInscription2.getStyleClass().add("LabelConnexionField");
        labelMotDePasseInscription2.setLayoutX(575);
        labelMotDePasseInscription2.setLayoutY(400);

        TextField textFieldPseudoInscription = new TextField();
        textFieldPseudoInscription.getStyleClass().add("TextFieldConnexion");
        textFieldPseudoInscription.setLayoutX(575);
        textFieldPseudoInscription.setLayoutY(225);
        PasswordField passwordFieldInscription = new PasswordField();
        passwordFieldInscription.getStyleClass().add("TextFieldConnexion");
        passwordFieldInscription.setLayoutX(575);
        passwordFieldInscription.setLayoutY(325);
        PasswordField passwordFieldInscription2 = new PasswordField();
        passwordFieldInscription2.getStyleClass().add("TextFieldConnexion");
        passwordFieldInscription2.setLayoutX(575);
        passwordFieldInscription2.setLayoutY(425);

        Button buttonInscription = new Button("S'inscrire");
        buttonInscription.getStyleClass().add("buttonConnexion");
        buttonInscription.setLayoutX(580);
        buttonInscription.setLayoutY(500);

        /////////////////// RETOUR ///////////////
        Button buttonRetour = new Button("Retour");
        buttonRetour.getStyleClass().add("buttonConnexionRetour");
        buttonRetour.setLayoutX(125);
        buttonRetour.setLayoutY(500);

        Label labelErreur = new Label();
        labelErreur.getStyleClass().add("LabelConnexionField");
        labelErreur.setLayoutX(250);
        labelErreur.setLayoutY(600);



        pane.getChildren().addAll(label, labelPseudo, labelMotDePasse, textFieldPseudo, passwordField, buttonConnexion, labelInscription, labelPseudoInscription, labelMotDePasseInscription, labelMotDePasseInscription2, textFieldPseudoInscription, passwordFieldInscription, passwordFieldInscription2, buttonInscription, buttonRetour, labelErreur);


        /**
         * Bouton connexion
         * Si le pseudo et le mot de passe sont corrects, on passe à la vue suivante
         * Sinon, on affiche un message d'erreur
         * @param event
         */
        buttonInscription.setOnAction(event -> {
            labelErreur.setText("");
//            if (new AuthPlayer(textFieldPseudoInscription.getText()).getLogin() == null){
                if (textFieldPseudoInscription.getText().equals("") || passwordFieldInscription.getText().equals("") || passwordFieldInscription2.getText().equals("")) {
                    labelErreur.setText("Veuillez remplir tous les champs d'incription");
                } else if (!passwordFieldInscription.getText().equals(passwordFieldInscription2.getText())) {
                    labelErreur.setText("Les mots de passe ne correspondent pas");
                } else {
                    try {
                        PlayerManager.getInstance().createPlayer(textFieldPseudoInscription.getText(), passwordFieldInscription.getText());
                        labelErreur.setText("Inscription réussie");
                    } catch (Exception e) {
                        labelErreur.setText("Erreur lors de l'inscription");
                    }
                }
//            } else {
//                labelErreur.setText("Ce pseudo est déjà utilisé");
//            }
        });


        /**
         * Fermer la fenêtre si on clique sur le bouton retour
         */
        buttonRetour.setOnAction(event -> {
            this.close();
        });

        setTitle("Connexion");
        setResizable(false);
        setScene(scene);

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");

    }
}
