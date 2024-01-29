package it.unibo.cosmocity.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Simulation {
    @JsonProperty("settlers")
    private List<BaseSettler> settlers;
    @JsonProperty("resources")
    private List<StackedResource> resources;
    //@JsonProperty("difficulty")
    //private Difficulty difficulty;
    @JsonProperty("startTime")
    private long startTime;

    public Simulation(List<BaseSettler> settlers, List<StackedResource> resources,long startTime) {
        this.settlers = settlers;
        this.resources = resources;
        this.startTime = startTime;
    }

    public List<BaseSettler> getSettlers() {
        return settlers;
    }

    public List<StackedResource> getResources() {
        return resources;
    }

}
