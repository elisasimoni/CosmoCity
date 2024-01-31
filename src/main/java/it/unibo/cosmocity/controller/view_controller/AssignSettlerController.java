package it.unibo.cosmocity.controller.view_controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.view.Dashboard;
import javafx.stage.Stage;

/*
 * Controller of the settler assignment
 */
public class AssignSettlerController {

    private final List<String> settlersToAssign;
    private final SimulationController simulationController;

    /**
     * @param settlers
     * @param simulationController
     *                             the simulation controller
     */
    public AssignSettlerController(
            final List<String> settlers,
            final SimulationController simulationController) {
        this.settlersToAssign = settlers;
        this.simulationController = simulationController;
    }

    /**
     * @return a list of settlers names
     * @implNote the list is sorted
     */
    public List<String> getSettlersNames() {
        return settlersToAssign.stream().sorted().collect(Collectors.toList());
    }

    /**
     * @return a list of sector names
     */
    public List<String> getSectorOptions() {
        return List.of("Farm", "Hospital", "Military Base", "Workshop");
    }

    /**
     * @param settlerAssigned
     */
    public void sendSimulation( final Map<String, String> settlerAssigned) {
        this.simulationController.modifyOptionalSettler(settlerAssigned);
    }
}
