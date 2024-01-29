package it.unibo.cosmocity.model.event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.cosmocity.model.resources.BaseResource;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("fixDamage")
    private List<BaseResource> fixResources;
    @JsonProperty("damage")
    private List<BaseResource> demageResources;

    public Event(String name, String description, List<BaseResource> fixResources, List<BaseResource> demageResources) {
        this.name = name;
        this.description = description;
        this.fixResources = fixResources;
        this.demageResources = demageResources;
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
