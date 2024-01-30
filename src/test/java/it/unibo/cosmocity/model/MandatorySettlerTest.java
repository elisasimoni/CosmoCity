package it.unibo.cosmocity.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.cosmocity.model.resources.Medicine;
import it.unibo.cosmocity.model.settlers.Doctor;
import it.unibo.cosmocity.model.settlers.MandatorySettler;

class MandatorySettlerTest {

    private static MandatorySettler mandatorySettler;

    @BeforeAll
    static void setUp() {
        mandatorySettler = new Doctor();
    }

    @Test
    void testGetSectorAssigned() {
        assertEquals("Hospital", mandatorySettler.getSectorAssigned());
    }

    @Test
    void testGetResource(){
        assertEquals(new Medicine(1).getClass(), mandatorySettler.getProductedResource().getClass());
    }
    
}
