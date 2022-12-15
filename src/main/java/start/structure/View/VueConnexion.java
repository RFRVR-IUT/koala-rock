package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import start.structure.metier.entite.AuthPlayer;
import start.structure.metier.manager.PlayerManager;
import start.structure.stockage.Security;
import start.structure.stockage.Session;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class VueConnexion extends Stage {
    Pane pane = new Pane();
    Scene scene = new Scene(pane, 950, 650);
    Security security = new Security();

    public VueConnexion() {
        scene.getStylesheets().add("file:src/main/resources/css/style.css");

        /////////////// CONNEXION SCENE ///////////////
        Label label = new Label("Connexion");
        label.getStyleClass().add("labelConnexion");
        label.setLayoutX(100);
        label.setLayoutY(50);

        Label labelPseudo = new Label("Pseudo");
        labelPseudo.getStyleClass().add("LabelConnexionField");
        labelPseudo.setLayoutX(175);
        labelPseudo.setLayoutY(250);
        Label labelMotDePasse = new Label("Mot de passe");
        labelMotDePasse.getStyleClass().add("LabelConnexionField");
        labelMotDePasse.setLayoutX(175);
        labelMotDePasse.setLayoutY(350);

        TextField textFieldPseudo = new TextField();
        textFieldPseudo.getStyleClass().add("TextFieldConnexion");
        textFieldPseudo.setLayoutX(175);
        textFieldPseudo.setLayoutY(275);
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("TextFieldConnexion");
        passwordField.setLayoutX(175);
        passwordField.setLayoutY(375);

        Button buttonConnexion = new Button("Se connecter");
        buttonConnexion.getStyleClass().add("buttonConnexion");
        buttonConnexion.setLayoutX(185);
        buttonConnexion.setLayoutY(450);

        //INSCRIPTION
        Label labelInscription = new Label("Inscription");
        labelInscription.getStyleClass().add("labelConnexion");
        labelInscription.setLayoutX(520);
        labelInscription.setLayoutY(50);

        Label labelPseudoInscription = new Label("Pseudo");
        labelPseudoInscription.getStyleClass().add("LabelConnexionField");
        labelPseudoInscription.setLayoutX(590);
        labelPseudoInscription.setLayoutY(150);
        Label labelMotDePasseInscription = new Label("Mot de passe");
        labelMotDePasseInscription.getStyleClass().add("LabelConnexionField");
        labelMotDePasseInscription.setLayoutX(590);
        labelMotDePasseInscription.setLayoutY(250);
        Label labelMotDePasseInscription2 = new Label("Confirmation");
        labelMotDePasseInscription2.getStyleClass().add("LabelConnexionField");
        labelMotDePasseInscription2.setLayoutX(590);
        labelMotDePasseInscription2.setLayoutY(350);

        TextField textFieldPseudoInscription = new TextField();
        textFieldPseudoInscription.getStyleClass().add("TextFieldConnexion");
        textFieldPseudoInscription.setLayoutX(590);
        textFieldPseudoInscription.setLayoutY(175);
        PasswordField passwordFieldInscription = new PasswordField();
        passwordFieldInscription.getStyleClass().add("TextFieldConnexion");
        passwordFieldInscription.setLayoutX(590);
        passwordFieldInscription.setLayoutY(275);
        PasswordField passwordFieldInscription2 = new PasswordField();
        passwordFieldInscription2.getStyleClass().add("TextFieldConnexion");
        passwordFieldInscription2.setLayoutX(590);
        passwordFieldInscription2.setLayoutY(375);

        Button buttonInscription = new Button("S'inscrire");
        buttonInscription.getStyleClass().add("buttonConnexion");
        buttonInscription.setLayoutX(610);
        buttonInscription.setLayoutY(450);


        //RETOUR
        Button buttonRetour = new Button("Retour");
        buttonRetour.getStyleClass().add("buttonConnexionRetour");
        buttonRetour.setLayoutX(50);
        buttonRetour.setLayoutY(550);

        Label labelErreur = new Label();
        labelErreur.getStyleClass().add("LabelConnexionField");
        labelErreur.setLayoutX(340);
        labelErreur.setLayoutY(580);


        pane.getChildren().addAll(label, labelPseudo, labelMotDePasse, textFieldPseudo, passwordField, buttonConnexion, labelInscription, labelPseudoInscription, labelMotDePasseInscription, labelMotDePasseInscription2, textFieldPseudoInscription, passwordFieldInscription, passwordFieldInscription2, buttonInscription, buttonRetour, labelErreur);

        buttonInscription.setOnAction(event -> {
            //if error -> labelErreur.setText("Erreur");
            labelErreur.setText("");
            if (PlayerManager.getInstance().getPlayer(textFieldPseudoInscription.getText()) == null) {
                if (textFieldPseudoInscription.getText().equals("") || passwordFieldInscription.getText().equals("") || passwordFieldInscription2.getText().equals("")) {
                    labelErreur.setText("Veuillez remplir tous les champs d'incription");
                } else if (!passwordFieldInscription.getText().equals(passwordFieldInscription2.getText())) {
                    labelErreur.setText("Les mots de passe ne correspondent pas");
                    passwordFieldInscription.setText("");
                    passwordFieldInscription2.setText("");
                } else {
                    try {
                        PlayerManager.getInstance().createPlayer(textFieldPseudoInscription.getText(), passwordFieldInscription.getText());
                        Session.getInstance().connect(textFieldPseudoInscription.getText());
                        textFieldPseudoInscription.setText("");
                        passwordFieldInscription.setText("");
                        passwordFieldInscription2.setText("");
                        this.close();
//                        labelErreur.setText("Inscription réussie");
                    } catch (Exception e) {
                        labelErreur.setText("Erreur lors de l'inscription");
                    }
                }
            } else {
                labelErreur.setText("Ce pseudo est déjà utilisé");
            }
        });

        buttonConnexion.setOnAction(event -> {
            //if error -> labelErreur.setText("Erreur");
            labelErreur.setText("");
            if (textFieldPseudo.getText().equals("") || passwordField.getText().equals("") ) {
                labelErreur.setText("Veuillez remplir tous les champs d'incription");
            } else {
                if (PlayerManager.getInstance().getPlayer(textFieldPseudo.getText()) == null) {
                    labelErreur.setText("Ce pseudo n'existe pas");
                } else {
                    AuthPlayer authPlayer = PlayerManager.getInstance().getPlayer(textFieldPseudo.getText());
                    try {
                        if (security.checkPassword(passwordField.getText(), authPlayer.getSalt(), authPlayer.getHashedPassword())) {
//                            labelErreur.setText("Connexion réussie");
                            Session.getInstance().connect(textFieldPseudo.getText());
                            textFieldPseudo.setText("");
                            passwordField.setText("");
                            this.close();
                        } else {
                            labelErreur.setText("Mot de passe incorrect");
                            passwordField.setText("");
                        }
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidKeyException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        buttonRetour.setOnAction(event -> {
            this.close();
        });

        setTitle("Connexion");
        setResizable(false);
        setScene(scene);

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");

    }
}
