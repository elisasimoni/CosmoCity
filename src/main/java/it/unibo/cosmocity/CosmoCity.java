package it.unibo.cosmocity;

import javafx.application.Application;
import javafx.stage.Stage;
import it.unibo.cosmocity.view.Dashboard;
import it.unibo.cosmocity.view.LandingPage;

/**
 * Main class
 */
public class CosmoCity extends Application {

    /**
     * Main method
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        LandingPage landingPage = new LandingPage(stage, 900, 700);
        landingPage.show();
    }
}