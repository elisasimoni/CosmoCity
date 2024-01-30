package it.unibo.cosmocity.model.event;

import java.util.List;


import it.unibo.cosmocity.model.resources.BaseResource;

public class Event {
    private String name;
    private String description;
    private List<BaseResource> fixResources;
    private List<BaseResource> demageResources;

    public Event(String name, String description, List<BaseResource> fixResources, List<BaseResource> demageResources) {
        this.name = name;
        this.description = description;
        this.fixResources = fixResources;
        this.demageResources = demageResources;
    }

    public Event(String name, String description, List<BaseResource> fixResources) {
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

    public List<BaseResource> getFixResources() {
        return this.fixResources;
    }

    public List<BaseResource> getDemageResources() {
        return this.demageResources;
    }

}
