package start. structure.Client;

import javafx.application.Application;
import javafx.stage.Stage;
import start.structure.Sound.Son;
import start.structure.View.VueDemarrage;

import java.io.IOException;

public class StartGame extends Application {

    private final VueDemarrage vueDemarrage = new VueDemarrage();

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        vueDemarrage.screenStart();
        Son.playMusic();
    }


}