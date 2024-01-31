package it.unibo.cosmocity;

import java.util.ArrayList;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.view.LandingPage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class
 */
public class CosmoCity extends Application {

    /**
     * Main method
     * 
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }

    public void start(final Stage stage) {
        final Simulation simulation = new Simulation("", new ArrayList<>(), new ArrayList<>(),  DifficultiesType.EASY,0);
        final SimulationController simulationController = new SimulationController(simulation);
        final LandingPage landingPage = new LandingPage(stage, 900, 700, simulationController);
        landingPage.show();
    }
}