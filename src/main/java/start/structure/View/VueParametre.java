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
        Button buttonHide = new Button("Hide");
        buttonHide.getStyleClass().add("buttonEcran");
        buttonHide.setLayoutX(10);
        buttonHide.setLayoutY(10);
        buttonHide.setOnAction(event -> {
            this.hide();
        });
        pane.getChildren().add(buttonHide);


        setTitle("Paramètre");
        setResizable(false);
        setScene(scene);
        pane.setStyle("-fx-background-color: black ;");
    }






}
