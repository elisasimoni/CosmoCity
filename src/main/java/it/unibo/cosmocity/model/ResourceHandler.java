package it.unibo.cosmocity.model;

import it.unibo.cosmocity.model.resources.BaseResource;

public interface ResourceHandler {

    public void incrementResource(BaseResource resource, int valueToAdd);

    public void decrementResource(BaseResource resource, int valueToSubtract);

}
