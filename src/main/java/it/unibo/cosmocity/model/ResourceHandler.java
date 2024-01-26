package it.unibo.cosmocity.model;

import it.unibo.cosmocity.model.resources.BaseResource;

public interface ResourceHandler {

    /**
     * @param resource
     * @param valueToAdd
     */
    public void incrementResource(BaseResource resource, int valueToAdd);

    /**
     * @param resource
     * @param valueToSubtract
     */
    public void decrementResource(BaseResource resource, int valueToSubtract);

}
