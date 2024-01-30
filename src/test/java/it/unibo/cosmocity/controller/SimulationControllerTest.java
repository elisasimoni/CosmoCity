package it.unibo.cosmocity.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.Cook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

class SimulationControllerTest {

    private static SimulationController simulationController;
    private static StackedResource resource = new FoodStacked(10);
    private static BaseSettler settler = new Cook();
    private static Simulation simulation = new Simulation("test",List.of(settler), List.of(resource), DifficultiesType.EASY,0);
    @BeforeAll
    static void setUp() {
        simulationController = new SimulationController(simulation);
    }

    @Test
    void testGetSettler() {
        var result = simulationController.getSettlers();
        assertEquals(List.of("Cook"),result);
    }


    @Test
    void testGetSimulation(){
        assertEquals(simulation, simulationController.getSimulation());
    }



    
}
