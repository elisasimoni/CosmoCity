package it.unibo.cosmocity;

import javafx.application.Application;
import javafx.stage.Stage;
import it.unibo.cosmocity.view.LandingPage;
import it.unibo.cosmocity.view.Dashboard;

import it.unibo.cosmocity.view.AssignSettler;


public class CosmoCity extends Application {



    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LandingPage landingPage = new LandingPage(primaryStage, 900, 700);
        landingPage.show();
    }
}