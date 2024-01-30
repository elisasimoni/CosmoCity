package it.unibo.cosmocity.controller;

import org.apache.commons.lang3.builder.Diff;
import org.apiguardian.api.API;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.ResourceHandlerImpl;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Food;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.Resource;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.Cook;
import it.unibo.cosmocity.model.settlers.SimpleSettler;

class ResourceHandlerControllerTest {

    private static ResourceHandlerImpl resourceHandlerController;
    private static StackedResource resource = new FoodStacked(10);
    private static Simulation simulation = new Simulation("test",List.of(), List.of(resource), DifficultiesType.EASY,0);
    
    @BeforeAll
    static void setUp() {
        resourceHandlerController = new ResourceHandlerImpl(simulation);
    }


    @Test
    void testIncrementResource() {
        resourceHandlerController.incrementResource(resource, 5);
        assertEquals(10, resource.getQta());
    }

    @Test
    void testDecrementResource() {
        resourceHandlerController.decrementResource(resource, 5);
        assertEquals(5, resource.getQta());
    }

    @Test
    void testCheckQtaResource() {
        boolean result = resourceHandlerController.checkQtaResource();
        assertEquals(true, result);
    }
}
