package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import start.structure.Model.Fond;
import start.structure.Model.PersonneEnnemie;
import start.structure.Model.PersonnePrincipale;

public class VueParametre extends Stage {
    private static String choixPersonnage = "Panda";
    private static String choixEnnemi = "Koala";

    public VueParametre() {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 950, 650);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");

        /////////// PARAMETRE SCENE ///////////

        ComboBox<String> comboBoxJoueurPrincipale = new ComboBox<>();
        comboBoxJoueurPrincipale.getItems().addAll("Panda", "Samurai");
        comboBoxJoueurPrincipale.setValue("Panda");

        comboBoxJoueurPrincipale.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            choixPersonnage = newValue;
            if(choixPersonnage.equals("Panda")) {
                PersonnePrincipale.setPersonnePrincipale("PANDA");
            } else if (choixPersonnage.equals("Samurai")) {
                PersonnePrincipale.setPersonnePrincipale("SAMURAI");
            }
        });

        comboBoxJoueurPrincipale.setLayoutX(400);
        comboBoxJoueurPrincipale.setLayoutY(300);

        ComboBox<String> comboBoxPersonnageEnnemie = new ComboBox<>();
        comboBoxPersonnageEnnemie.getItems().addAll("Koala", "Ninja");
        comboBoxPersonnageEnnemie.setValue("Koala");

        comboBoxPersonnageEnnemie.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            choixEnnemi = newValue;
            if(choixEnnemi.equals("Koala")) {
                PersonneEnnemie.setChoixPersonnage("KOALA");
                Fond.setChoixFond("fondKoala.png");
            } else if (choixEnnemi.equals("Ninja")) {
                PersonneEnnemie.setChoixPersonnage("NINJA");
                Fond.setChoixFond("fondNinja.png");
            }
        });

        comboBoxPersonnageEnnemie.setLayoutX(500);
        comboBoxPersonnageEnnemie.setLayoutY(300);


        ////////////////////////////Bouton////////////////////////////
        Label button_Bas = new Label();
        ImageView image_Bas = new ImageView("file:src/main/resources/Button/Button_bas.png");
        image_Bas.setFitHeight(50);
        image_Bas.setFitWidth(50);
        button_Bas.setGraphic(image_Bas);
        button_Bas.getStyleClass().add("button_Action");
        button_Bas.setLayoutX(200);
        button_Bas.setLayoutY(485);

        Label button_Haut = new Label();
        ImageView image_Haut = new ImageView("file:src/main/resources/Button/Button_haut.png");
        image_Haut.setFitHeight(50);
        image_Haut.setFitWidth(50);
        button_Haut.setGraphic(image_Haut);
        button_Haut.getStyleClass().add("button_Action");
        button_Haut.setLayoutX(200);
        button_Haut.setLayoutY(400);

        Label button_Gauche = new Label();
        ImageView image_Gauche = new ImageView("file:src/main/resources/Button/Button_gauche.png");
        image_Gauche.setFitHeight(50);
        image_Gauche.setFitWidth(50);
        button_Gauche.setGraphic(image_Gauche);
        button_Gauche.getStyleClass().add("button_Action");
        button_Gauche.setLayoutX(110);
        button_Gauche.setLayoutY(485);

        Label button_Droite = new Label();
        ImageView image_Droite = new ImageView("file:src/main/resources/Button/Button_droite.png");
        image_Droite.setFitHeight(50);
        image_Droite.setFitWidth(50);
        button_Droite.setGraphic(image_Droite);
        button_Droite.getStyleClass().add("button_Action");
        button_Droite.setLayoutX(285);
        button_Droite.setLayoutY(485);

        Label button_Espace = new Label();
        ImageView image_Espace = new ImageView("file:src/main/resources/Button/Button_espace.png");
        image_Espace.setFitHeight(50);
        image_Espace.setFitWidth(200);
        button_Espace.setGraphic(image_Espace);
        button_Espace.getStyleClass().add("button_Action");
        button_Espace.setLayoutX(600);
        button_Espace.setLayoutY(485);
        ////////////////////////////End Bouton////////////////////////////

        ////////////////////////////Texte////////////////////////////

        Label texte_Bas = new Label("Bas");
        texte_Bas.getStyleClass().add("LabelUnderButton");
        texte_Bas.setLayoutX(206);
        texte_Bas.setLayoutY(540);

        Label texte_Haut = new Label("Haut");
        texte_Haut.getStyleClass().add("LabelUnderButton");
        texte_Haut.setLayoutX(204);
        texte_Haut.setLayoutY(455);

        Label texte_Gauche = new Label("Gauche");
        texte_Gauche.getStyleClass().add("LabelUnderButton");
        texte_Gauche.setLayoutX(104);
        texte_Gauche.setLayoutY(540);

        Label texte_Droite = new Label("Droite");
        texte_Droite.getStyleClass().add("LabelUnderButton");
        texte_Droite.setLayoutX(282);
        texte_Droite.setLayoutY(540);

        Label texte_Espace = new Label("Espace");
        texte_Espace.getStyleClass().add("LabelUnderButton");
        texte_Espace.setLayoutX(670);
        texte_Espace.setLayoutY(540);

        ////////////////////////////End Texte////////////////////////////


        Button buttonHide = new Button("quitter et sauvegarder");
        buttonHide.getStyleClass().add("buttonEcran");
        buttonHide.setStyle("-fx-font-size: 12px; -fx-pref-width: 200px; -fx-pref-height: 30px;");


        buttonHide.setLayoutX(350);
        buttonHide.setLayoutY(580);
        buttonHide.setOnAction(event -> {
            this.hide();
            //System.out.println("Personnage : " + personnePrincipale.getChoixPersonnage());
        });
        pane.getChildren().add(buttonHide);
        pane.getChildren().addAll(button_Bas, button_Haut, button_Gauche, button_Droite, button_Espace);
        pane.getChildren().addAll(texte_Bas, texte_Haut, texte_Gauche, texte_Droite, texte_Espace);
        pane.getChildren().addAll(comboBoxPersonnageEnnemie, comboBoxJoueurPrincipale);


        setTitle("Paramètre");
        setResizable(false);
        setScene(scene);

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");


    }

    public static String getChoixPersonnage() {
        return choixPersonnage;
    }
}
