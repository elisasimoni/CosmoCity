package it.unibo.cosmocity.model.event;

import it.unibo.cosmocity.model.resources.StackedResource;

import java.util.List;

/*
 * Class used to create the random bad event
 *
 */
public class RandomEvent extends Event {
    public RandomEvent(final String name, final String description, final List<StackedResource> eventsResources,
            final List<StackedResource> demageResources) {
        super(name, description, eventsResources, demageResources);
    }
}
