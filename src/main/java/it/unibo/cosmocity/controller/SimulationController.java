package it.unibo.cosmocity.controller;

import it.unibo.cosmocity.model.SimulationManager;
import it.unibo.cosmocity.model.SimulationManagerImpl;
import it.unibo.cosmocity.controller.timer_controller.TimeHandler;
import it.unibo.cosmocity.controller.timer_controller.TimeHandlerImpl;

public class SimulationController {
    private SimulationManager simulationManager = new SimulationManagerImpl();
    //private TimerHandler timerHandler = new TimerHandlerImpl(); // Fix the TimerHandler type

    public void startSimulation(SimulationManager simulationManager){ //TimerHandler timerHandler) {
        simulationManager.startSimulation(null, null, null);
    
    }

    public void exitSimulation() {
        simulationManager.exitSimulation();
    }

    public void loadSimulation() {
        simulationManager.loadSimulation();
    }

    public void pauseSimulation() {
        simulationManager.pauseSimulation();
  
    }

    public void resumeSimulation() {
        simulationManager.resumeSimulation();
        
    }

    public void saveSimulation() {
        simulationManager.saveSimulation();
    }
    
}
