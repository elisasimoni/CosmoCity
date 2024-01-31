package it.unibo.cosmocity.model;

import java.util.List;

import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;

/*
 * This class is used to store the simulation data
 */
public class Simulation {

    private final String colonyName;

    private final List<BaseSettler> settlers;

    private final List<StackedResource> resources;

    private final DifficultiesType difficulty;

    private final long startTime;

    /**
     * @param colonyName
     * @param settlers
     * @param resources
     * @param difficulty
     * @param startTime
     */
    public Simulation(final String colonyName, final List<BaseSettler> settlers, final List<StackedResource> resources,
            final DifficultiesType difficulty, final long startTime) {
        this.colonyName = colonyName;
        this.settlers = settlers;
        this.resources = resources;
        this.startTime = startTime;
        this.difficulty = difficulty;

    }

    /**
     * @return the settlers
     */
    public List<BaseSettler> getSettlers() {
        return settlers;
    }

    /**
     * @return the resources
     */
    public List<StackedResource> getResources() {
        return resources;
    }

    /**
     * @return the difficulty
     */
    public DifficultiesType getDifficulty() {
        return difficulty;
    }

    /**
     * @return the colonyName
     */
    public String getColonyName() {
        return colonyName;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

}
