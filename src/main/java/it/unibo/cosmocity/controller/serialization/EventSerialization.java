package it.unibo.cosmocity.controller.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.model.event.Event;
import it.unibo.cosmocity.model.resources.StackedResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EventSerialization implements Serialization {

  private final ObjectMapper mapper = new ObjectMapper();
  TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();

  /*
   * Not need to implement now
   */
  @Override
  public void serialize(final Object object, File file) {
    // not need to implemet now
  }

  /*
   * Deserialize jsonEvent file and return a list of event
   * 
   */
  @Override
  public List<Event> deserialize() {
    try {
      InputStream inputStream = EventSerialization.class.getResourceAsStream("/it/unibo/asset/event/RandomEvent.json");

      if (inputStream != null) {
        final String jsonContent = new String(
            Objects.requireNonNull(inputStream).readAllBytes());

        final JsonNode jsonNode = mapper.readTree(jsonContent);

        final List<Event> events = new ArrayList<>();

        for (final JsonNode eventNode : jsonNode.get("randomEvents")) {
          final List<StackedResource> fixDamageList = new ArrayList<>();
          for (final JsonNode fixDamageNode : eventNode.get("fixDamage")) {
            final StackedResource fixDamage = translator.createResourceFromNameAndQta(
                fixDamageNode.get("name").asText(),
                fixDamageNode.get("qta").asInt());
            fixDamageList.add(fixDamage);
          }

          final List<StackedResource> damageList = new ArrayList<>();
          for (final JsonNode damageNode : eventNode.get("damage")) {
            final StackedResource damage = translator.createResourceFromNameAndQta(
                damageNode.get("name").asText(),
                damageNode.get("qta").asInt());
            damageList.add(damage);
          }
          final Event event = new Event(
              eventNode.get("name").asText(),
              eventNode.get("description").asText(),
              fixDamageList,
              damageList);
          events.add(event);
        }

        return events;
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }

  @Override
  public Object deserializeFromExtern(File file) {
    // not need to implemet now
    return null;
  }
}
