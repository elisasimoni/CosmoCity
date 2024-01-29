package it.unibo.cosmocity.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Simulation {
    @JsonProperty("colonyName")
    private String colonyName;
    @JsonProperty("settlers")
    private List<BaseSettler> settlers;
    @JsonProperty("resources")
    private List<StackedResource> resources;
    @JsonProperty("difficulty")
    private DifficultiesType difficulty;
    @JsonProperty("startTime")
    private long startTime;

    public Simulation(String colonyName,List<BaseSettler> settlers, List<StackedResource> resources,
           DifficultiesType difficulty, long startTime) {
        this.colonyName = colonyName;
        this.settlers = settlers;
        this.resources = resources;
        this.startTime = startTime;
        this.difficulty = difficulty;
  

    }

    public List<BaseSettler> getSettlers() {
        return settlers;
    }

    public List<StackedResource> getResources() {
        return resources;
    }

    public DifficultiesType getDifficulty() {
        return difficulty;
    }


    public String getColonyName() {
        return colonyName;
    }

}
