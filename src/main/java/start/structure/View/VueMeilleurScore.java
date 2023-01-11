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
import start.structure.RessourcesAccess;
import start.structure.metier.entite.Score;
import start.structure.metier.manager.ScoreManager;
import start.structure.stockage.Session;

import java.io.IOException;
import java.util.List;

public class VueMeilleurScore {
    Pane pane = new Pane();

    public void affichageVueMeilleurScore(Stage stage) throws IOException {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1280, 720);
        scene.getStylesheets().add(String.valueOf(RessourcesAccess.class.getResource("css/style.css")));


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
        buttonRetour.setOnAction(event -> {
            VueParametre vueParametre = new VueParametre();
            try {
                vueParametre.affichageVueParametre(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button menu = new Button("Menu");
        menu.getStyleClass().add("btnGrey");
        menu.setLayoutX(545);
        menu.setLayoutY(620);
        menu.setOnAction(event -> {
            VueMenu vueMenu = new VueMenu();
            vueMenu.demarrerMenu(stage);
        });

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

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");
        pane.getChildren().addAll(labelMeilleurScore, buttonRetour, menu, labelClassic, labelInfini, line);
        stage.setScene(scene);
        stage.setTitle("Meilleurs scores");
        stage.show();

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
    }
}
