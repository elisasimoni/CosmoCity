package it.unibo.cosmocity.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.cosmocity.model.resources.Food;

class BaseResourceTest {
    private static Food baseResource;
    @BeforeAll
    static void setUp() {
        baseResource = new Food(10);
    }

    @Test
    void testGetQta(){
        assertEquals(10, baseResource.getQta());
    }

}
