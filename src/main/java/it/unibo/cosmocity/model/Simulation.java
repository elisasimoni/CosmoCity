package it.unibo.cosmocity.model;

import java.util.List;

import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;

public class Simulation {

    private String colonyName;

    private List<BaseSettler> settlers;

    private List<StackedResource> resources;

    private DifficultiesType difficulty;

    private long startTime;

    public Simulation(String colonyName, List<BaseSettler> settlers, List<StackedResource> resources,
            DifficultiesType difficulty, long startTime) {
        this.colonyName = colonyName;
        this.settlers = settlers;
        this.resources = resources;
        this.startTime = startTime;
        this.difficulty = difficulty;

    }

    public Simulation() {
        //TODO Auto-generated constructor stub
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

    public long getStartTime() {
        return startTime;
    }

}
