package it.unibo.cosmocity.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.cosmocity.controller.serialization.EventSerialization;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.event.Event;
import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Food;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.Cook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

class SerializationTest {

    private static SimulationSerialization simulationSerialization;
    private static EventSerialization eventSerialization;
    private static StackedResource resourceStacked = new FoodStacked(10);
    private static BaseResource resourceBase = new Food(0);
    private static BaseSettler settler = new Cook();
    private static Simulation simulation = new Simulation("test", List.of(settler), List.of(resourceStacked),
            DifficultiesType.EASY, 0);
    private static Event event = new Event("test","description",List.of(resourceStacked),List.of(resourceBase));

    @BeforeAll
    static void setUp() {
        simulationSerialization = new SimulationSerialization();
        eventSerialization = new EventSerialization();
    }

    @Test
    void testSimulationSerialization() throws IOException {
        simulationSerialization.serializeSimulation(simulation);
        assertEquals(simulation.getColonyName(), simulationSerialization.deserializeSimulation().getColonyName());
        assertEquals(simulation.getDifficulty(), simulationSerialization.deserializeSimulation().getDifficulty());
        assertEquals(simulation.getStartTime(), simulationSerialization.deserializeSimulation().getStartTime());
        assertEquals(simulation.getSettlers().get(0).getClass().getSimpleName(), simulationSerialization.deserializeSimulation().getSettlers().get(0).getClass().getSimpleName());
        assertEquals(simulation.getSettlers().get(0).getSectorAssigned(), simulationSerialization.deserializeSimulation().getSettlers().get(0).getSectorAssigned());

    }

}
