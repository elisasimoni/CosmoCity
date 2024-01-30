package it.unibo.cosmocity.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.cosmocity.model.resources.FoodStacked;

class StackedResourceTest {
    private static FoodStacked stackedResource;
    @BeforeAll
    static void setUp() {
        stackedResource = new FoodStacked(10);
    }

    @Test
    void testGetQta(){
        assertEquals(10, stackedResource.getQta());
    }

    @Test
    void testSetQta(){
        stackedResource.setQta(20);
        assertEquals(20, stackedResource.getQta());
    }
}
