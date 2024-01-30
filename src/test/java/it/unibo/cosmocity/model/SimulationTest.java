package it.unibo.cosmocity.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



class SimulationTest{

    private static Simulation simulation = new Simulation("test",List.of(), List.of(), DifficultiesType.EASY,0);

    @BeforeAll
    static void setUp() {
    }

    @Test
    void testGetSettler() {
        var result = simulation.getSettlers();
        assertEquals(List.of(),result);
    }

    @Test
    void testGetResources() {
        var result = simulation.getResources();
        assertEquals(List.of(),result);
    }

    @Test
    void testGetDifficultiesType() {
        var result = simulation.getDifficulty();
        assertEquals(DifficultiesType.EASY, result);
    }

    @Test
    void testGetColonyName() {
        var result = simulation.getColonyName();
        assertEquals("", result);
    }

    @Test
    void testGetStart() {
        var result = simulation.getStartTime();
        assertEquals(0,result);
    }



}