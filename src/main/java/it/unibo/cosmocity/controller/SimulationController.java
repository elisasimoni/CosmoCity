package it.unibo.cosmocity.controller;

import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.controller.timer_controller.TimeHandler;
import it.unibo.cosmocity.controller.timer_controller.TimeHandlerImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;



public class SimulationController {
    private Simulation simulation;
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    private TimeHandler timerHandler = new TimeHandlerImpl(); /*è un metodo del timer che richiama un metodo del controllo  */
    SimulationSerialization serializationSimulation = new SimulationSerialization();

    public void startSimulation(List<String> settlers, Map<String,Integer> resources) {
        timerHandler.run();
        this.simulation = new Simulation(translator.translateSettler(settlers), translator.translateResource(resources),0);
        serializationSimulation.serializeSimulation(simulation);
        
        
    }

    public void exitSimulation() {
        System.exit(0);
        System.out.println("Simulation exited");
    }

    public void loadSimulation() throws IOException{
        this.simulation = serializationSimulation.deserializeSimulation();
        System.out.println("Simulation loaded!");

    }

    public void pauseSimulation() {
        System.out.println("Simulation paused");


    }

    public void resumeSimulation() {
        System.out.println("Simulation resumed");

    }

    public void saveSimulation() {
        serializationSimulation.serializeSimulation(simulation);
        System.out.println("Simulation saved!");
        
    }

}
