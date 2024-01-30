package it.unibo.cosmocity.controller;

import it.unibo.cosmocity.model.Simulation;

import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.ResourceHandler;
import it.unibo.cosmocity.model.ResourceHandlerImpl;
import it.unibo.cosmocity.view.Dashboard;
import it.unibo.cosmocity.view.dialog.PauseDialog;
import it.unibo.cosmocity.controller.serialization.SimulationSerialization;
import it.unibo.cosmocity.controller.view_controller.DashBoardController;
import it.unibo.cosmocity.controller.view_controller.SceneController;

import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SimulationController {
    private Simulation simulation;
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    private DashBoardController dashBoardController;
    private SimulationSerialization serializationSimulation = new SimulationSerialization();
    private ResourceHandler resourceHandler;
    private SceneController sceneController = new SceneController();

    public void startSimulation(List<String> settlers, Map<String, Integer> resources) {
        Dashboard dashboard = new Dashboard(new Stage(), 900, 700);
        sceneController.nextSceneNavigator(dashboard);
        this.simulation = new Simulation("PINO COLONY", translator.translateSettler(settlers),
                translator.translateResources(resources), DifficultiesType.EASY, 0);
        this.resourceHandler = new ResourceHandlerImpl(simulation);
        this.dashBoardController = new DashBoardController(dashboard, simulation);

    }

    public void loadSimulationInfo(List<String> settlers, String colonyName) {
        /*
         * this.simulation = new Simulation(translator.translateSettler(settlers),
         * DifficultiesType.EASY, 0);
         * this.simulation.setColonyName(colonyName);
         * this.resourceHandler = new ResourceHandlerImpl(simulation);
         */

    }

    public void updateSimulation(List<String> settlers, Map<String, Integer> resources, String colonyName) {
        this.simulation = new Simulation(colonyName, translator.translateSettler(settlers),
                translator.translateResources(resources), DifficultiesType.EASY, 0);
        this.resourceHandler = new ResourceHandlerImpl(simulation);

    }

    public void exitSimulation() {
        System.exit(0);
        System.out.println("Simulation exited");
    }

    public void loadSimulation() throws IOException {
        var objectDesirialize = serializationSimulation.deserialize();
        if (objectDesirialize instanceof Simulation) {
            this.simulation = (Simulation) objectDesirialize;
            this.startSimulation(translator.translateSettlerToString(this.simulation.getSettlers()),
                    translator.translateResourceToMap(this.simulation.getResources()));
        } else {
            throw new IOException();
        }

    }

    public void pauseSimulation() {

        Thread.onSpinWait();

    }

    public void saveSimulation() {
        serializationSimulation.serialize(simulation);
        exitSimulation();

    }

    public boolean gameOverSimulation() {
        return resourceHandler.checkQtaResource();
    }
}