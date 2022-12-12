package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import start.structure.metier.entite.AuthPlayer;
import start.structure.metier.entite.Score;
import start.structure.metier.manager.PlayerManager;
import start.structure.metier.manager.ScoreManager;

import java.util.List;

import static java.lang.constant.ConstantDescs.NULL;

public class VueMeilleurScore extends Stage {
    Pane pane = new Pane();
    Scene scene = new Scene(pane, 950, 650);

    public VueMeilleurScore() {;
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 950, 650);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");

        //Recuperation des meilleurs scores
        Label labelMeilleurScore = new Label("Meilleurs scores");
        labelMeilleurScore.getStyleClass().add("labelMeilleurScore");
        labelMeilleurScore.setLayoutX(50);
        labelMeilleurScore.setLayoutY(100);

        List<Score> scores = ScoreManager.getInstance().getScores();
        int i = 0;
        while(i<10 || i<scores.size()){
            Label labelPseudo = new Label(scores.get(i).getLogin());
            Label labelScore = new Label(String.valueOf(scores.get(i).getScore()));
            Label place = new Label(String.valueOf(i+1));

            labelPseudo.setLayoutX(50*(i+1));
            labelPseudo.setLayoutY(150);
            labelScore.setLayoutX(50*(i+1));
            labelScore.setLayoutY(200);
            place.setLayoutX(50*(i+1));
            place.setLayoutY(250);

            pane.getChildren().add(labelPseudo);
            i++;
        }

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");
        pane.getChildren().add(labelMeilleurScore);
        this.setScene(scene);

    }
}
