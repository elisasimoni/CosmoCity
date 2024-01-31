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

    /**
     * @return the list of the resources produced by the population
     */
    public boolean checkQtaResource();

    /**
     * @param settlers
     */
    public void settlerGetAppetite(List<BaseSettler> settlers);

    /**
     *  @return the list of the resources produced by the population
     */
    public void populationGetAppetite();

    /**
     *  @return the list of the resources produced by the population
     */
    public void populationGetSick();

    /**
     *  @return the list of the resources produced by the population
     */
    public void populationBreakThing();

    /**
     *  @return the list of the resources produced by the population
     */
    public void populationNewBorn();

    /**
     * @param settlers
     * @return the list of the resources produced by the settlers
     */
    public List<StackedResource> settlerProductionOptional(List<SimpleSettler> settlers);

    /**
     * @param settlers
     * @return the list of the resources produced by the settlers
     */
    public List<StackedResource> settlerProductionMandatory(List<MandatorySettler> settlers);

    /**
     * @param resources
     * increment the resources with the production of the settler
    */
    public void incrementWithProduction(List<StackedResource> resources);

}
