package it.unibo.cosmocity.controller.serialization;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.cosmocity.model.event.Event;

public class EventSerialization implements Serialization{

    private ObjectMapper mapper = new ObjectMapper();
    Path filePath = Path.of("src\\main\\java\\it\\unibo\\resources\\saves\\Colony.json");

    @Override
    public void serialize(Object object) {
        //not need to implemet now
    }

    @Override
    public List<Event> deserialize() {
        if (filePath.toFile().exists()) {
            try {
                JsonNode json = mapper.readTree(filePath.toFile());
                JsonNode jsonEvents = json.get("randomEvents");
                return mapper.readValue(jsonEvents.traverse(), new TypeReference<List<Event>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
}
