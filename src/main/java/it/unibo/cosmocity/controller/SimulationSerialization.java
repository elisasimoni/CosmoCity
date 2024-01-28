package it.unibo.cosmocity.controller;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;

import it.unibo.cosmocity.model.Simulation;

public class SimulationSerialization {

    private ObjectMapper mapper = new ObjectMapper();
    Path filePath = Path.of("src\\main\\java\\it\\unibo\\resources\\saves\\Colony.json");

    public void serializeSimulation(Simulation simulation) {

        try {
            if(filePath.toFile().exists()){
                Files.delete(filePath);
            }
            Files.writeString(filePath, mapper.writeValueAsString(simulation));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Simulation deserializeSimulation() throws IOException {
        System.out.println("Simulation loaded!");
        return mapper.readValue(filePath.toFile(), Simulation.class);

    }

}
