package it.unibo.cosmocity.model.event;

import it.unibo.cosmocity.model.resources.StackedResource;

import java.util.List;

public class RandomEvent extends Event {
    public RandomEvent(String name, String description, List<StackedResource> eventsResources, List<StackedResource> demageResources) {
        super(name, description, eventsResources,demageResources);
    }
}
  