package it.unibo.cosmocity.controller;

import it.unibo.cosmocity.controller.serialization.SimulationSerialization;
import it.unibo.cosmocity.controller.view_controller.DashboardController;
import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.ResourceHandler;
import it.unibo.cosmocity.model.ResourceHandlerImpl;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.SimpleSettler;
import it.unibo.cosmocity.view.Dashboard;
import it.unibo.cosmocity.view.LandingPageView;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.stage.Stage;

public class SimulationController {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private Simulation simulation;
    private final TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    private DashboardController dashBoardController;
    private final SimulationSerialization serializationSimulation = new SimulationSerialization();
    private final ResourceHandler resourceHandler;
    private final SceneController sceneController = new SceneController();
    private LandingPageView landingPageView;

    public SimulationController(final Simulation simulation) {
        this.simulation = simulation;
        this.resourceHandler = new ResourceHandlerImpl(simulation);
    }

    public void startSimulation() {
        final Dashboard dashboard = new Dashboard(new Stage(), WIDTH, HEIGHT, this);
        sceneController.nextSceneNavigator(dashboard);
        this.dashBoardController = new DashboardController(dashboard, simulation);
    }

    /**
     * The function loads simulation information from a JSON file and starts the
     * simulation with the
     * loaded data.
     *
     * @param settlers   A list of strings representing the settlers in the
     *                   simulation.
     * @param colonyName The name of the colony that will be loaded from the save
     *                   file.
     */
    public void loadSimulationInfo(
            final List<String> settlers,
            final String colonyName) {
        try {
            final var simulationFromJson = serializationSimulation.deserialize();
            if (simulationFromJson instanceof Simulation) {
                this.simulation = (Simulation) simulationFromJson;
                this.startSimulation();
            } else {
                throw new IOException("Invalid save file");
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSimulation(
            final String colonyName,
            final List<String> settlers,
            final Map<String, Integer> resources,
            final DifficultiesType difficulty) {
        this.simulation = new Simulation(
                colonyName,
                translator.translateSettler(settlers),
                translator.translateResources(resources),
                difficulty);
    }

    public void updateSimulationSettler(final Map<String, String> settlers) {
        final var settlerList = translator.translateSettlerToListFromMapMO(
                translator.translateSettlerToMapMandatory(settlers),
                translator.translateSettlerToMapOptional(settlers));
        this.simulation = new Simulation(
                this.simulation.getColonyName(),
                settlerList,
                this.simulation.getResources(),
                this.simulation.getDifficulty());
    }

    public void modifyOptionalSettler(final Map<String, String> settlers) {
        final List<BaseSettler> settlerListTotal = this.simulation.getSettlers();
        settlers.forEach((name, sector) -> {
            for (final var settler : settlerListTotal) {
                if (settler instanceof SimpleSettler) {
                    final SimpleSettler simpleSettler = (SimpleSettler) settler;
                    if (simpleSettler.getClass().getSimpleName().equals(name)) {
                        simpleSettler.setSectorAssigned(sector);
                    }
                }
            }
        });

        this.simulation = new Simulation(
                this.simulation.getColonyName(),
                settlerListTotal,
                this.simulation.getResources(),
                this.simulation.getDifficulty());
        startSimulation();
    }

    public void modifyOptionalSettlerDuringSim(
            final Map<String, String> settlers) {
        final List<BaseSettler> settlerListTotal = this.simulation.getSettlers();
        settlers.forEach((name, sector) -> {
            for (final var settler : settlerListTotal) {
                if (settler instanceof SimpleSettler) {
                    final SimpleSettler simpleSettler = (SimpleSettler) settler;
                    if (simpleSettler.getClass().getSimpleName().equals(name)) {
                        simpleSettler.setSectorAssigned(sector);
                    }
                }
            }
        });

        this.simulation = new Simulation(
                this.simulation.getColonyName(),
                settlerListTotal,
                this.simulation.getResources(),
                this.simulation.getDifficulty());
   
    }

    public void exitSimulation() {
        System.exit(0);
    }

    public void loadSimulation() {
        try {
            final var objectDeserialization = serializationSimulation.deserialize();
            if (objectDeserialization instanceof Simulation) {
                this.simulation = (Simulation) objectDeserialization;
                this.startSimulation();
            } else {
                System.out.println("Invalid save file");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void saveSimulation() {
        serializationSimulation.serialize(simulation);
        exitSimulation();
    }

    public boolean gameOverSimulation() {
        return resourceHandler.checkQtaResource();
    }

    public List<String> getSettlers() {
        return this.simulation.getSettlers()
                .stream()
                .map(s -> s.getClass().getSimpleName())
                .toList();
    }

    public Simulation getSimulation() {
        return this.simulation;
    }

    public void addSettler(final BaseSettler settler) {
        this.simulation.getSettlers().add(settler);
    }

    public Map<String, String> getSettlerSectorMap() {
        return this.simulation.getSettlers()
                .stream()
                .collect(
                        Collectors.toMap(
                                s -> s.getClass().getSimpleName(),
                                s -> s.getSectorAssigned()));
    }
}
