package it.unibo.cosmocity.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.cosmocity.controller.serialization.EventSerialization;
import it.unibo.cosmocity.controller.serialization.SimulationSerialization;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.event.Event;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.Cook;

class SerializationTest {

    private static SimulationSerialization simulationSerialization;
    private static EventSerialization eventSerialization;
    private static StackedResource resourceStacked = new FoodStacked(10);
    private static StackedResource resourceBase = new FoodStacked(0);
    private static BaseSettler settler = new Cook();
    private static File file = new File("it/unibo/asset/saves/Colony.json");
    private static Simulation simulation = new Simulation("test", List.of(settler), List.of(resourceStacked),
            DifficultiesType.EASY);
    private static Event event = new Event("test", "description", List.of(resourceStacked), List.of(resourceBase));

    @BeforeAll
    static void setUp() {
        simulationSerialization = new SimulationSerialization();
        eventSerialization = new EventSerialization();
    }

    @Test
    void testSimulationSerialization() throws IOException {
        simulationSerialization.serialize(simulation, file);
        assertEquals(simulation.getColonyName(), simulationSerialization.deserializeFromExtern(file).getColonyName());
        assertEquals(simulation.getDifficulty(), simulationSerialization.deserializeFromExtern(file).getDifficulty());
        assertEquals(simulation.getSettlers().get(0).getClass().getSimpleName(),
                simulationSerialization.deserializeFromExtern(file).getSettlers().get(0).getClass().getSimpleName());
        assertEquals(simulation.getSettlers().get(0).getSectorAssigned(),
                simulationSerialization.deserializeFromExtern(file).getSettlers().get(0).getSectorAssigned());

    }

}
