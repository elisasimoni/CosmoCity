package it.unibo.cosmocity;

import javafx.application.Application;
import javafx.stage.Stage;
import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.view.LandingPage;
import java.util.*;

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

    public void start(Stage stage) {
        Simulation simulation = new Simulation("", new ArrayList<>(), new ArrayList<>(),  DifficultiesType.EASY,0);
        SimulationController simulationController = new SimulationController(simulation);
        LandingPage landingPage = new LandingPage(stage, 900, 700, simulationController);
        landingPage.show();
    }
}