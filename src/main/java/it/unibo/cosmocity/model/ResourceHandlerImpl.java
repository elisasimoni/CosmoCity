package it.unibo.cosmocity.model;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Screw;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.resources.Weapons;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResourceHandlerImpl implements ResourceHandler {

    private Set<StackedResource> resources = new HashSet<>();

    BaseResource weapon = new Weapons(1);
    BaseResource screaw = new Screw(1);

    List<BaseResource> resourcesList = List.of(weapon, screaw);

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

    public Set<StackedResource> getResources() {
        return resources;
    }

}
