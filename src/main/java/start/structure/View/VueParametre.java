package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class VueParametre extends Stage {


    public VueParametre() {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 550, 650);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");

        // button to hide
        Button buttonHide = new Button("quitter et sauvegarder");
        buttonHide.getStyleClass().add("buttonEcran");
        buttonHide.setStyle("-fx-font-size: 12px; -fx-pref-width: 200px; -fx-pref-height: 30px;");


        buttonHide.setLayoutX(170);
        buttonHide.setLayoutY(580);
        buttonHide.setOnAction(event -> {
            this.hide();
        });
        pane.getChildren().add(buttonHide);


        setTitle("Paramètre");
        setResizable(false);
        setScene(scene);

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");


    }






}
