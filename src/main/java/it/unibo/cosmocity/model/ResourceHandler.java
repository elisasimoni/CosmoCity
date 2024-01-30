package it.unibo.cosmocity.model;

import java.util.List;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.MandatorySettler;
import it.unibo.cosmocity.model.settlers.SimpleSettler;

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

    public boolean checkQtaResource();

    public void settlerGetAppetite(List<BaseSettler> settlers);

    public void populationGetAppetite();

    public void populationGetSick();

    public void populationBreakThing();

    public void populationNewBorn();

    public List<StackedResource> settlerProductionOptional(List<SimpleSettler> settlers);

    public List<StackedResource> settlerProductionMandatory(List<MandatorySettler> settlers);

    public void incrementWithProduction(List<StackedResource> resources);

}
