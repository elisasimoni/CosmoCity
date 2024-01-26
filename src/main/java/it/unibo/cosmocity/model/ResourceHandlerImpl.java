package it.unibo.cosmocity.model;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.StackedResource;

import java.util.HashSet;
import java.util.Set;

public class ResourceHandlerImpl implements ResourceHandler {

    private Set<StackedResource> resources = new HashSet<>();

    @Override
    public void incrementResource(BaseResource resource, int valueToAdd) {
        resources.stream().filter(r -> r.getClass().equals(resource.getClass()))
                .forEach(r -> r.setQta(r.getQta() + valueToAdd));

    }

    @Override
    public void decrementResource(BaseResource resource, int valueToSubtract) {
        resources.stream().filter(r -> r.getClass().equals(resource.getClass()))
                .forEach(r -> r.setQta(r.getQta() - valueToSubtract));
    }

    public Set<StackedResource> getResources() {
        return resources;
    }

}
