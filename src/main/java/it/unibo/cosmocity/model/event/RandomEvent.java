package it.unibo.cosmocity.model.event;

import it.unibo.cosmocity.model.resources.BaseResource;

import java.util.List;

public class RandomEvent extends Event {
    public RandomEvent(String name, String description, List<BaseResource> eventsResources, List<BaseResource> demageResources) {
        super(name, description, eventsResources,demageResources);
    }
}
