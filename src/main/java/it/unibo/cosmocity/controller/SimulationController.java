package it.unibo.cosmocity.controller;

import it.unibo.cosmocity.model.SimulationManager;
import it.unibo.cosmocity.model.SimulationManagerImpl;
import it.unibo.cosmocity.controller.timer_controller.TimeHandler;
import it.unibo.cosmocity.controller.timer_controller.TimeHandlerImpl;

public class SimulationController {
    private SimulationManager simulationManager = new SimulationManagerImpl();

    private TimeHandler timerHandler = new TimeHandlerImpl();

    public void startSimulation(SimulationManager simulationManager, TimeHandler timerHandler) {
        simulationManager.startSimulation(null, null, null);
        timerHandler.run(); // Fix the method call

    }

    public void exitSimulation() {
        simulationManager.exitSimulation();
    }

    public void loadSimulation() {
        simulationManager.loadSimulation();
    }

    public void pauseSimulation() {
        simulationManager.pauseSimulation();
        timerHandler.run(); // Fix the method call

    }

    public void resumeSimulation() {
        simulationManager.resumeSimulation();

        timerHandler.run(); // Fix the method call

    }

    public void saveSimulation() {
        simulationManager.saveSimulation();
    }
    
}
