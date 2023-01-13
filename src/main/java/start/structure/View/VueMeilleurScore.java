package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import start.structure.Model.Fond;
import start.structure.RessourcesAccess;
import start.structure.metier.entite.Score;
import start.structure.metier.manager.ScoreManager;
import start.structure.stockage.Session;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class VueMeilleurScore {

    public void affichageVueMeilleurScore(Stage stage) throws IOException {
        Pane pane = new Pane();
        Fond fond = new Fond(1280, 720);
        Scene scene = new Scene(pane, 1280, 720);
        scene.getStylesheets().add(String.valueOf(RessourcesAccess.class.getResource("css/style.css")));

        Label menuScreen = new Label();
        menuScreen.setGraphic(fond.getChoixFond());

        Label labelMeilleurScore = new Label("Meilleurs scores");
        labelMeilleurScore.setLayoutX(480);
        labelMeilleurScore.setLayoutY(40);
        labelMeilleurScore.setFont(new javafx.scene.text.Font("Goldman", 40));
        labelMeilleurScore.setTextFill(javafx.scene.paint.Color.WHITE);
        labelMeilleurScore.setUnderline(true);

        Label labelClassic = new Label("Classic");
        labelClassic.setLayoutX(250);
        labelClassic.setLayoutY(120);
        labelClassic.setFont(new javafx.scene.text.Font("Goldman", 25));
        labelClassic.setTextFill(javafx.scene.paint.Color.WHITE);

        Label labelInfini = new Label("Infini");
        labelInfini.setLayoutX(900);
        labelInfini.setLayoutY(120);
        labelInfini.setFont(new javafx.scene.text.Font("Goldman", 25));
        labelInfini.setTextFill(javafx.scene.paint.Color.WHITE);

        Line line = new Line();
        line.setStartX(640.0f);
        line.setStartY(200.0f);
        line.setEndX(640.0f);
        line.setEndY(450.0f);
        line.setStrokeWidth(2);
        line.setStroke(Color.WHITE);

        Button buttonRetour = new Button("Retour");
        buttonRetour.getStyleClass().add("btnGrey");
        buttonRetour.setLayoutX(645);
        buttonRetour.setLayoutY(620);

        Button menu = new Button("Menu");
        menu.getStyleClass().add("btnGrey");
        menu.setLayoutX(545);
        menu.setLayoutY(620);

        //  Retour sur le menu
        buttonRetour.setOnAction(event -> {
            VueParametre vueParametre = new VueParametre();
            try {
                vueParametre.affichageVueParametre(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //  Retour sur le menu
        menu.setOnAction(event -> {
            VueMenu vueMenu = new VueMenu();
            vueMenu.demarrerMenu(stage);
        });

        //         // Lors du click sur le bouton quitter de la fenetre (affichage confirmation)
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

        // Affichage des meilleurs scores
        List<Score> scores = ScoreManager.getInstance().getScores();
        int i = 0;
        while (i < 10 && i < scores.size()) {
            Label labelPseudoInfini;
            if (scores.get(i).getLogin() == null) {
                labelPseudoInfini = new Label("Invité");
            } else {
                labelPseudoInfini = new Label(scores.get(i).getLogin());
            }
            Label labelScoreInfini = new Label(String.valueOf(scores.get(i).getScore()));
            Label placeInfini = new Label(i + 1 + ".");

            if (i == 0) {
                labelScoreInfini.setTextFill(javafx.scene.paint.Color.GOLD);
                labelPseudoInfini.setTextFill(javafx.scene.paint.Color.GOLD);
                placeInfini.setTextFill(javafx.scene.paint.Color.GOLD);
            } else if (i == 1) {
                labelScoreInfini.setTextFill(javafx.scene.paint.Color.GRAY);
                labelPseudoInfini.setTextFill(javafx.scene.paint.Color.GRAY);
                placeInfini.setTextFill(javafx.scene.paint.Color.GRAY);
            } else if (i == 2) {
                labelScoreInfini.setTextFill(javafx.scene.paint.Color.BROWN);
                labelPseudoInfini.setTextFill(javafx.scene.paint.Color.BROWN);
                placeInfini.setTextFill(javafx.scene.paint.Color.BROWN);
            } else {
                labelScoreInfini.setTextFill(javafx.scene.paint.Color.WHITE);
                labelPseudoInfini.setTextFill(javafx.scene.paint.Color.WHITE);
                placeInfini.setTextFill(javafx.scene.paint.Color.WHITE);
            }

            labelScoreInfini.getStyleClass().add("LabelScore");
            labelPseudoInfini.getStyleClass().add("LabelScore");
            placeInfini.getStyleClass().add("LabelScore");

            labelPseudoInfini.setLayoutX(800);
            labelPseudoInfini.setLayoutY(165 + 35 * (i + 1));
            labelScoreInfini.setLayoutX(1100);
            labelScoreInfini.setLayoutY(165 + 35 * (i + 1));
            placeInfini.setLayoutX(750);
            placeInfini.setLayoutY(165 + 35 * (i + 1));

            pane.getChildren().add(labelPseudoInfini);
            pane.getChildren().add(labelScoreInfini);
            pane.getChildren().add(placeInfini);
            i++;
        }

        int j = 0;
        Map<Integer,Double> scoresTemps = ScoreManager.getInstance().getScoresTemps();
        while (j<10 && j<scoresTemps.size()){
            Label labelPseudoTemps;
            if (ScoreManager.getInstance().getLoginTemps((Integer) scoresTemps.keySet().toArray()[j]) == null) {
                labelPseudoTemps = new Label("Invité");
            } else {
                labelPseudoTemps = new Label(ScoreManager.getInstance().getLoginTemps((Integer) scoresTemps.keySet().toArray()[j]));
            }
            Label labelScoreTemps = new Label(String.valueOf(scoresTemps.values().toArray()[j] + " S"));
            Label placeTemps = new Label(j + 1 + ".");
            if (j == 0) {
                labelScoreTemps.setTextFill(javafx.scene.paint.Color.GOLD);
                labelPseudoTemps.setTextFill(javafx.scene.paint.Color.GOLD);
                placeTemps.setTextFill(javafx.scene.paint.Color.GOLD);
            } else if (j == 1) {
                labelScoreTemps.setTextFill(javafx.scene.paint.Color.GRAY);
                labelPseudoTemps.setTextFill(javafx.scene.paint.Color.GRAY);
                placeTemps.setTextFill(javafx.scene.paint.Color.GRAY);
            } else if (j == 2) {
                labelScoreTemps.setTextFill(javafx.scene.paint.Color.BROWN);
                labelPseudoTemps.setTextFill(javafx.scene.paint.Color.BROWN);
                placeTemps.setTextFill(javafx.scene.paint.Color.BROWN);
            } else {
                labelScoreTemps.setTextFill(javafx.scene.paint.Color.WHITE);
                labelPseudoTemps.setTextFill(javafx.scene.paint.Color.WHITE);
                placeTemps.setTextFill(javafx.scene.paint.Color.WHITE);
            }

            labelScoreTemps.getStyleClass().add("LabelScore");
            labelPseudoTemps.getStyleClass().add("LabelScore");
            placeTemps.getStyleClass().add("LabelScore");

            labelPseudoTemps.setLayoutX(200);
            labelPseudoTemps.setLayoutY(165 + 35 * (j + 1));
            labelScoreTemps.setLayoutX(500);
            labelScoreTemps.setLayoutY(165 + 35 * (j + 1));
            placeTemps.setLayoutX(150);
            placeTemps.setLayoutY(165 + 35 * (j + 1));

            pane.getChildren().add(labelPseudoTemps);
            pane.getChildren().add(labelScoreTemps);
            pane.getChildren().add(placeTemps);
            j++;
        }

        // pane.getChildren().add(menuScreen);
        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");
        pane.getChildren().addAll(labelMeilleurScore, buttonRetour, menu, labelClassic, labelInfini, line);
        stage.setScene(scene);
        stage.setTitle("Meilleurs scores");
        stage.show();
    }
}
