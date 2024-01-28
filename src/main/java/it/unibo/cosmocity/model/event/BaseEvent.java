package it.unibo.cosmocity.model.event;

import it.unibo.cosmocity.model.resources.BaseResource;

import java.util.List;

public class BaseEvent extends Event {
    public BaseEvent(String name, String description, List<BaseResource> eventsResources) {
        super(name, description, eventsResources);
    }
}
