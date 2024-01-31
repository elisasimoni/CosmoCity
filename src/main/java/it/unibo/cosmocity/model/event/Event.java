package it.unibo.cosmocity.model.event;

import java.util.List;

import it.unibo.cosmocity.model.resources.StackedResource;

/**
 * The Event class represents an event with a name, description, and lists of
 * fix and damage resources.
 */
public class Event {
    private final String name;
    private final String description;
    private List<StackedResource> fixResources;
    private List<StackedResource> demageResources;

    /**
     * @param name
     * @param description
     * @param fixResources
     * @param demageResources
     */
    public Event(final String name, final String description, final List<StackedResource> fixResources,
            final List<StackedResource> demageResources) {
        this.name = name;
        this.description = description;
        this.fixResources = fixResources;
        this.demageResources = demageResources;
    }

    /**
     * @param name
     * @param description
     * @param fixResources
     */
    public Event(final String name, final String description, final List<StackedResource> fixResources) {
        this.name = name;
        this.description = description;
        this.fixResources = fixResources;
    }

    /**
     * @param name
     * @param description
     */
    public Event(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the fixResources
     */
    public List<StackedResource> getFixResources() {
        return this.fixResources;
    }

    /**
     * @return the demageResources
     */
    public List<StackedResource> getDemageResources() {
        return this.demageResources;
    }

}
