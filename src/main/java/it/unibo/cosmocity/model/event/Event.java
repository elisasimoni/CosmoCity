package it.unibo.cosmocity.model.event;

import java.util.List;


import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.StackedResource;

public class Event {
    private String name;
    private String description;
    private List<StackedResource> fixResources;
    private List<StackedResource> demageResources;

    public Event(String name, String description, List<StackedResource> fixResources, List<StackedResource> demageResources) {
        this.name = name;
        this.description = description;
        this.fixResources = fixResources;
        this.demageResources = demageResources;
    }

    public Event(String name, String description, List<StackedResource> fixResources) {
        this.name = name;
        this.description = description;
        this.fixResources = fixResources;
    }

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<StackedResource> getFixResources() {
        return this.fixResources;
    }

    public List<StackedResource> getDemageResources() {
        return this.demageResources;
    }

}
