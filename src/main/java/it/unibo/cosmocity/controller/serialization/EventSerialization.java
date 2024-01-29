package it.unibo.cosmocity.controller.serialization;

import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.event.Event;

public class EventSerialization implements Serialization{

    private ObjectMapper mapper = new ObjectMapper();
    Path filePath = Path.of("src\\main\\java\\it\\unibo\\resources\\saves\\Colony.json");

    @Override
    public void serialize(Object object) {
        //not need to implemet now
    }

    @Override
    public Object deserialize() {
        if (filePath.toFile().exists()) {
            
            try {
                return mapper.readValue(filePath.toFile(), Event.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
}
