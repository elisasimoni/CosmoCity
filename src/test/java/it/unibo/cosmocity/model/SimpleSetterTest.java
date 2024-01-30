package it.unibo.cosmocity.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.cosmocity.model.settlers.SimpleSettler;
import it.unibo.cosmocity.model.settlers.Technician;

class SimpleSetterTest {

    private static SimpleSettler simpleSetter;

    @BeforeAll
    static void setUp() {
        simpleSetter = new Technician();
    }

    @Test
    void testGetSectorAssigned() {
        assertEquals("Workshop", simpleSetter.getSectorAssigned());
    }

}
