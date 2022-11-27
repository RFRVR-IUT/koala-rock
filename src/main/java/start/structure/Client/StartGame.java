package start.structure.Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import start.structure.Model.*;
import start.structure.View.VueDemarrage;
import start.structure.View.VuesJeu;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class StartGame extends Application {

    private final VueDemarrage vueDemarrage = new VueDemarrage();

    public static void main(String[] args) {
        launch();
    }


        @Override
    public void start(Stage stage) throws IOException, InterruptedException {
            vueDemarrage.screenStart();
    }


}