package it.unibo.cosmocity.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.cosmocity.model.event.EventManager;
import it.unibo.cosmocity.model.event.RandomEvent;


class EventManagerTest  {
    private static EventManager eventManager;

    @BeforeAll
    static void setUp() {
        eventManager = new EventManager();
    }

    @Test
    void testGenerateRandomEvent() {
        RandomEvent event = eventManager.generateRandomEvent();
        assertNotNull(event);
    }

    @Test
    void testIsEmptyResource() {
        boolean result = eventManager.isEmptyResource();
        assertEquals(false, result);
    }
    
}

