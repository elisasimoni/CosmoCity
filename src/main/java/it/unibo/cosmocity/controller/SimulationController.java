package it.unibo.cosmocity.controller;

import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.SimpleSettler;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.ResourceHandler;
import it.unibo.cosmocity.model.ResourceHandlerImpl;
import it.unibo.cosmocity.view.Dashboard;
import it.unibo.cosmocity.view.LandingPageView;
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
    private LandingPageView landingPageView;

    public SimulationController(Simulation simulation) {
        this.simulation = simulation;
    }

    public void startSimulation(String colonyName, List<String> settlers, Map<String, Integer> resources,
            DifficultiesType difficulty) {
        Dashboard dashboard = new Dashboard(new Stage(), 900, 700);

        sceneController.nextSceneNavigator(dashboard);
        this.resourceHandler = new ResourceHandlerImpl(simulation);
        this.dashBoardController = new DashBoardController(dashboard, simulation);

    }

    public void loadSimulationInfo(List<String> settlers, String colonyName) {
        try {
            var simulationFromJson = serializationSimulation.deserialize();
            if (simulationFromJson instanceof Simulation) {
                this.simulation = (Simulation) simulationFromJson;
                this.startSimulation(
                        this.simulation.getColonyName(),
                        translator.translateSettlerToString(this.simulation.getSettlers()),
                        translator.translateResourceToMap(this.simulation.getResources()),

                        this.simulation.getDifficulty());

            } else {

                throw new IOException("Invalid save file");
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void updateSimulation(String colonyName, List<String> settlers, Map<String, Integer> resources,
            DifficultiesType difficulty) {
        this.simulation = new Simulation(colonyName, translator.translateSettler(settlers),
                translator.translateResources(resources), difficulty, 0);

    }

    public void updateSimulationSettler(Map<String, String> settlers) {
        var settlerList = translator.translateSettlerToListFromMapMO(
                translator.translateSettlerToMapMandatory(settlers),
                translator.translateSettlerToMapOptional(settlers));
        this.simulation = new Simulation(this.simulation.getColonyName(), settlerList,
                this.simulation.getResources(), this.simulation.getDifficulty(), 0);

    }

    public void modifyOptionalSettler(Map<String, String> settlers) {
        List<BaseSettler> settlerListTotal = this.simulation.getSettlers();
        for (var settler : settlerListTotal) {
            if (settler instanceof SimpleSettler) {
                SimpleSettler simpleSettler = (SimpleSettler) settler;
                if (simpleSettler.getClass().getSimpleName().equals(settlers.get("name"))) {
                    simpleSettler.setSectorAssigned(settlers.get("sector"));
                }
            }
        }
        startSimulation(this.simulation.getColonyName(),
                translator.translateSettlerToString(this.simulation.getSettlers()),
                translator.translateResourceToMap(this.simulation.getResources()), this.simulation.getDifficulty());

    }

    public void exitSimulation() {

        System.out.println("Simulation exited");
    }

    public void loadSimulation() throws IOException {
        var objectDesirialize = serializationSimulation.deserialize();
        if (objectDesirialize instanceof Simulation) {
            this.simulation = (Simulation) objectDesirialize;
            this.startSimulation(this.simulation.getColonyName(),
                    translator.translateSettlerToString(this.simulation.getSettlers()),
                    translator.translateResourceToMap(this.simulation.getResources()), this.simulation.getDifficulty());
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

    public List<String> getSettlers() {
        return this.simulation.getSettlers().stream().map(s -> s.getClass().getSimpleName()).toList();
    }
}