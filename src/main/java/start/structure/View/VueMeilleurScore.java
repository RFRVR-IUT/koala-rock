package start.structure.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
        //labelMeilleurScore.getStyleClass().add("labelMeilleurScore");
        labelMeilleurScore.setLayoutX(320);
        labelMeilleurScore.setLayoutY(50);
        labelMeilleurScore.setFont(new javafx.scene.text.Font("Goldman", 40));
        labelMeilleurScore.setTextFill(javafx.scene.paint.Color.WHITE);

        List<Score> scores = ScoreManager.getInstance().getScores();
        int i = 0;
        //afficher les trois joueurs ayant le plus de score avec des couleurs differentes

        while(i<10 && i<scores.size()){
            Label labelPseudo;
            if (scores.get(i).getLogin()==null){
                labelPseudo = new Label("Invité");
            }else {
                labelPseudo = new Label(scores.get(i).getLogin());
            }
            Label labelScore = new Label(String.valueOf(scores.get(i).getScore()));
            Label place = new Label(String.valueOf(i+1)+".");

            if(i==0){
                labelScore.setTextFill(javafx.scene.paint.Color.GOLD);
                labelPseudo.setTextFill(javafx.scene.paint.Color.GOLD);
                place.setTextFill(javafx.scene.paint.Color.GOLD);
            }
            else if(i==1){
                labelScore.setTextFill(javafx.scene.paint.Color.GRAY);
                labelPseudo.setTextFill(javafx.scene.paint.Color.GRAY);
                place.setTextFill(javafx.scene.paint.Color.GRAY);
            }
            else if(i==2){
                labelScore.setTextFill(javafx.scene.paint.Color.BROWN);
                labelPseudo.setTextFill(javafx.scene.paint.Color.BROWN);
                place.setTextFill(javafx.scene.paint.Color.BROWN);
            }
            else{
                labelScore.setTextFill(javafx.scene.paint.Color.WHITE);
                labelPseudo.setTextFill(javafx.scene.paint.Color.WHITE);
                place.setTextFill(javafx.scene.paint.Color.WHITE);
            }
/*
            labelScore.setFont(new javafx.scene.text.Font("Arial", 40));
            labelPseudo.setFont(new javafx.scene.text.Font("Goldman", 40));
            place.setFont(new javafx.scene.text.Font("Goldman", 40));
*/
            labelScore.getStyleClass().add("LabelConnexionField");
            labelPseudo.getStyleClass().add("LabelConnexionField");
            place.getStyleClass().add("LabelConnexionField");

            labelPseudo.setLayoutX(350);
            labelPseudo.setLayoutY(115+25*(i+1));
            labelScore.setLayoutX(650);
            labelScore.setLayoutY(115+25*(i+1));
            place.setLayoutX(300);
            place.setLayoutY(115+25*(i+1));

            pane.getChildren().add(labelPseudo);
            pane.getChildren().add(labelScore);
            pane.getChildren().add(place);
            i++;
        }

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");
        pane.getChildren().add(labelMeilleurScore);
        this.setScene(scene);

    }
}
