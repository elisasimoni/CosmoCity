package it.unibo.cosmocity.controller;

import it.unibo.cosmocity.model.Difficulty;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.SimulationManager;
import it.unibo.cosmocity.model.SimulationManagerImpl;
import it.unibo.cosmocity.controller.timer_controller.TimeHandler;
import it.unibo.cosmocity.controller.timer_controller.TimeHandlerImpl;

import java.util.List;
import java.util.Map;



public class SimulationController {
    private Simulation simulation;
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    private TimeHandler timerHandler = new TimeHandlerImpl(); /*è un metodo del timer che richiama un metodo del controllo  */
    private SimulationManager simulationManager = new SimulationManagerImpl(); // Provide the parametrized type for the generic
    SerializationSimulation serializationSimulation = new SerializationSimulation();

    public void startSimulation(List<String> settlers, Map<String,Integer> resources) {
        timerHandler.run();
        this.simulation = new Simulation(translator.translateSettler(settlers), translator.translateResource(resources),0);
        serializationSimulation.serializeSimulation(simulation);
        
        
    }

    public void exitSimulation() {
        this.simulationManager.exitSimulation();
    }

    public void loadSimulation(){
        //this.simulation = new Simulation(translator.translateSettler(settlers), translator.translateResource(resources));
        timerHandler.run();
    }

    public void pauseSimulation() {
        this.simulationManager.pauseSimulation();
        timerHandler.run();

    }

    public void resumeSimulation() {
        this.simulationManager.resumeSimulation();
        timerHandler.run();

    }

    public void saveSimulation() {
        this.simulationManager.saveSimulation();
    }

}
