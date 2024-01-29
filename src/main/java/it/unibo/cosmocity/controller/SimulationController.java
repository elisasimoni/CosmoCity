package it.unibo.cosmocity.controller;

import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.event.Event;
import it.unibo.cosmocity.model.settlers.Doctor;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.ResourceHandler;
import it.unibo.cosmocity.view.Dashboard;
import it.unibo.cosmocity.controller.serialization.EventSerialization;
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
        this.simulation = new Simulation("PINO COLONY",translator.translateSettler(settlers),
                translator.translateResources(resources), DifficultiesType.EASY, 0);
        
        new DashBoardController(dashboard, simulation);

    }

    public void loadSimulationInfo(List<String> settlers, String colonyName) {
        /*this.simulation = new Simulation(translator.translateSettler(settlers), DifficultiesType.EASY, 0);
        this.simulation.setColonyName(colonyName);
        this.resourceHandler = new ResourceHandlerImpl(simulation);*/


    }

    public void exitSimulation() {
        System.exit(0);
        System.out.println("Simulation exited");
    }

    public void loadSimulation() throws IOException {
        var objectDesirialize = serializationSimulation.deserialize();
        if (objectDesirialize instanceof Simulation) {
            this.simulation = (Simulation) objectDesirialize;
        } else {
            throw new IOException();
        }

    }

    public void pauseSimulation() {
        // Implementazione della pausa della simulazione

    }

    public void resumeSimulation() {
        System.out.println("Simulation resumed");

    }

    public void saveSimulation() {
        serializationSimulation.serialize(simulation);
        System.out.println("Simulation saved!");

    }

    public boolean gameOverSimulation() {
        return resourceHandler.checkQtaResource();
    }
}