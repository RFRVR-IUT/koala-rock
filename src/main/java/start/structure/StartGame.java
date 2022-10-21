package start.structure;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGame extends Application {
    private Scene Deplacement;

    private Stage primaryStage;
    Deplacement deplacement;

    @FXML
    Pane mainJeu;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartGame.class.getResource("fond.fxml"));
        Scene scene = new Scene(loader.load());

        //mainJeu = new Pane();
        mainJeu = (Pane) loader.getNamespace().get("mainJeu");

        deplacement = new Deplacement();
        deplacement.mouvement();
        mainJeu.getChildren().add(deplacement);

        stage.setTitle("Donkey Kong");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}