package it.unibo.cosmocity;

import javafx.application.Application;
import javafx.stage.Stage;
import it.unibo.cosmocity.view.LandingPage;
import it.unibo.cosmocity.view.CreateColonyPage;

public class CosmoCity extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        LandingPage landingPage = new LandingPage(stage, 900, 700);
        CreateColonyPage colony = new CreateColonyPage(stage, 700, 900);
        landingPage.show();
        colony.show();

    }
}