package it.unibo.cosmocity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import it.unibo.cosmocity.model.Simulation;

public class SerializationSimulation{

    private ObjectMapper mapper = new ObjectMapper();
    
    public void serializeSimulation(Simulation simulation) {
        try {
            String json = mapper.writeValueAsString(simulation);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deserializeSimulation() {
        // TODO implement here
    }
    
}
