package it.unibo.cosmocity.controller;


import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.ResourceHandler;

import it.unibo.cosmocity.view.Dashboard;
import javafx.stage.Stage;
import it.unibo.cosmocity.controller.serialization.SimulationSerialization;
import it.unibo.cosmocity.controller.timer_controller.TimerObservable;
import it.unibo.cosmocity.controller.view_controller.DashBoardController;
import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.controller.timer_controller.EventObserver;


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
        this.simulation = new Simulation(translator.translateSettler(settlers), translator.translateResource(resources), 0);
        this.dashBoardController = new DashBoardController(dashboard,simulation);
        
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
        System.out.println("Simulation loaded!");
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