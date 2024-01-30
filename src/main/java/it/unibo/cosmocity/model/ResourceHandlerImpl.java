package it.unibo.cosmocity.model;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.StackedResource;

import java.util.List;

public class ResourceHandlerImpl implements ResourceHandler {

    private List<StackedResource> resourcesList;

    public ResourceHandlerImpl(Simulation simulation) {
        this.resourcesList = simulation.getResources();
    }

    @Override
    public void incrementResource(BaseResource resource, int valueToAdd) {
        resourcesList.stream().filter(r -> r.getClass().equals(resource.getClass()))
                .forEach(r -> r.setQta(r.getQta() + valueToAdd));

    }

    @Override
    public void decrementResource(BaseResource resource, int valueToSubtract) {
        resourcesList.stream().filter(r -> r.getClass().equals(resource.getClass()))
                .forEach(r -> r.setQta(r.getQta() - valueToSubtract));
    }

    public List<StackedResource> getResources() {
        return resourcesList;
    }

    @Override
    public boolean checkQtaResource() {
        return resourcesList.stream().allMatch(resource -> resource.getQta() > 0);
    }

}
