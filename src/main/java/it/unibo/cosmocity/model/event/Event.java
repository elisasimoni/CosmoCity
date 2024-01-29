package it.unibo.cosmocity.model.event;

import java.util.List;

import it.unibo.cosmocity.model.resources.BaseResource;

public class Event {
    private String name;
    private String description;
    private List<BaseResource> eventsResources;

    public Event(String name, String description, List<BaseResource> eventsResources) {
        this.name = name;
        this.description = description;
        this.eventsResources = eventsResources;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<BaseResource> getEventsResources() {
        return eventsResources;
    }
    

}
