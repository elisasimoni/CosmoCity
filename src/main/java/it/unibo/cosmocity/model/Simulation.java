package it.unibo.cosmocity.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.resources.Weapons;

public class Simulation<T> {

    private List<T> settlers;
    private Set<StackedResource> resources;
    private Difficulty difficulty;
    private ResourceHandler resourceHandler = new ResourceHandlerImpl();
    

    public Simulation(List<T> settlers, Set<StackedResource> resources, Difficulty difficulty) {
        this.settlers = settlers;
        this.resources = resources;
        this.difficulty = new Difficulty(difficulty.getName());
        resourceHandler.incrementResource(new Weapons(5), 1);
    }

}
