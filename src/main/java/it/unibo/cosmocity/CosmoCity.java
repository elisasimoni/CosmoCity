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

    private static final int WIDTH = 900;
    private static final int HEIGHT = 700;

    /**
     * Main method
     * 
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * The start function initializes a simulation, simulation controller, and
     * landing page, and then
     * displays the landing page.
     * 
     * @param stage
     */
    public void start(final Stage stage) {
        final Simulation simulation = new Simulation("", new ArrayList<>(), new ArrayList<>(), DifficultiesType.EASY);
        final SimulationController simulationController = new SimulationController(simulation);
        final LandingPage landingPage = new LandingPage(stage, WIDTH, HEIGHT, simulationController);
        landingPage.show();
    }
}