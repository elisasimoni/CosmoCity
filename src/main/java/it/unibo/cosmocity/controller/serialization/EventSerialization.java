package it.unibo.cosmocity.controller.serialization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.event.Event;
import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Food;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.Medicine;
import it.unibo.cosmocity.model.resources.MedicineStacked;
import it.unibo.cosmocity.model.resources.Screw;
import it.unibo.cosmocity.model.resources.ScrewStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.resources.Weapons;
import it.unibo.cosmocity.model.resources.WeaponsStacked;
import it.unibo.cosmocity.model.settlers.BaseSettler;

public class EventSerialization implements Serialization {

    private ObjectMapper mapper = new ObjectMapper();
    Path filePath = Path.of("src\\main\\java\\it\\unibo\\resources\\event\\RandomEvent.json");
    TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();

    @Override
    public void serialize(Object object) {
        // not need to implemet now
    }

    @Override
    public List<Event> deserialize() {
        try {
            if (filePath.toFile().exists()) {
                String jsonContent = Files.readString(filePath);

                JsonNode jsonNode = mapper.readTree(jsonContent);

                List<Event> events = new ArrayList<>();

                for (JsonNode eventNode : jsonNode.get("randomEvents")) {

                    List<BaseResource> fixDamageList = new ArrayList<>();
                    for (JsonNode fixDamageNode : eventNode.get("fixDamage")) {
                        BaseResource fixDamage = createResource(fixDamageNode.get("name").asText(),
                                fixDamageNode.get("qta").asInt());
                        fixDamageList.add(fixDamage);
                    }

                    List<BaseResource> damageList = new ArrayList<>();
                    for (JsonNode damageNode : eventNode.get("damage")) {
                        BaseResource damage = createResource(damageNode.get("name").asText(),
                                damageNode.get("qta").asInt());
                        damageList.add(damage);
                    }
                    Event event = new Event(eventNode.get("name").asText(), eventNode.get("description").asText(),
                            fixDamageList, damageList);
                    events.add(event);
                }

                return events;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private BaseResource createResource(String resourceName, int quantity) {
        switch (resourceName) {
            case "Screw":
                return new Screw(quantity);
            case "Weapons":
                return new Weapons(quantity);
            case "Medicine":
                return new Medicine(quantity);
            case "Food":
                return new Food(quantity);
            default:
                return null;
        }

    }

}
