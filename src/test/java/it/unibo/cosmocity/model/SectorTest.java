package it.unibo.cosmocity.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.cosmocity.view.Sector.Status;

class SectorTest {

    private static Sector sector;

    @BeforeAll
    static void setUp() {
        sector = new Sector();
    }

    @Test
    void testSectorStatus(){
        assertEquals(String.valueOf(Status.GREEN),String.valueOf(sector.getStatus()));

    }

    @Test
    void testSetSectorStatus(){
        sector.setStatus(Sector.Status.YELLOW);
        assertEquals(String.valueOf(Status.YELLOW),String.valueOf(sector.getStatus()));
    }

    
}
