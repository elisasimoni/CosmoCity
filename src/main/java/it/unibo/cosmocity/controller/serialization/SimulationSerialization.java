package it.unibo.cosmocity.controller.serialization;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;

import it.unibo.cosmocity.model.Simulation;

public class SimulationSerialization implements Serialization{

    private ObjectMapper mapper = new ObjectMapper();
    Path filePath = Path.of("src\\main\\java\\it\\unibo\\resources\\saves\\Colony.json");
    
    @Override
    public void serialize(Object object) {
        try {
            if(!filePath.toFile().exists()){
                new File(filePath.toFile().getParent());
            }
            Files.writeString(filePath, mapper.writeValueAsString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Object deserialize() {

        if (filePath.toFile().exists()) {
            
            try {
                return mapper.readValue(filePath.toFile(), Simulation.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    
        
    }

}
