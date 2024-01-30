package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.model.settlers.BaseSettler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssignSettlerController {
    private final List<String> settlersToAssign;
    private final SimulationController simulationController;

    public AssignSettlerController(List<String> settlers, SimulationController simulationController) {
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

    public List<String> getSectorOptions() {
        return List.of("Farm", "Hospital", "Military Base", "Workshop");
    }

    public void sendSimulation(Map<String, String> settlerAssigned) {
        this.simulationController.modifyOptionalSettler(settlerAssigned);
    }

}
