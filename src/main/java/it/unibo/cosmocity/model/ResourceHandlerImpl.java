package it.unibo.cosmocity.model;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.MedicineStacked;
import it.unibo.cosmocity.model.resources.Population;
import it.unibo.cosmocity.model.resources.ScrewStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.MandatorySettler;
import it.unibo.cosmocity.model.settlers.SimpleSettler;

/**
 * The ResourceHandlerImpl class is an implementation of the ResourceHandler interface.
 */
public class ResourceHandlerImpl implements ResourceHandler {

  private final List<StackedResource> resourcesList;

  public ResourceHandlerImpl(final Simulation simulation) {
    this.resourcesList = simulation.getResources();
  }

  /**
   * The function increments the quantity of a specific resource by a given value.
   * 
   * @param resource The "resource" parameter is an instance of the "BaseResource" class.
   * @param valueToAdd The parameter "valueToAdd" is an integer value that represents the amount by
   * which the resource's quantity should be incremented.
   */
  @Override
  public void incrementResource(final BaseResource resource, final int valueToAdd) {
    resourcesList.stream()
        .filter(r -> r.getClass().equals(resource.getClass()))
        .forEach(r -> r.setQta(r.getQta() + valueToAdd));
  }

  /**
   * The function decrements the quantity of a specific resource by a given value.
   * 
   * @param resource The "resource" parameter is an instance of the "BaseResource" class.
   * @param valueToSubtract The value that needs to be subtracted from the quantity of the resource.
   */
  @Override
  public void decrementResource(final BaseResource resource, final int valueToSubtract) {
    resourcesList.stream()
        .filter(r -> r.getClass().equals(resource.getClass()))
        .forEach(r -> r.setQta(r.getQta() - valueToSubtract));
  }

  public void settlerGetAppetite(final List<BaseSettler> settlersList) {
    final int foodEaten = settlersList.stream().mapToInt(BaseSettler::getAppetite).sum();
    decrementResource(new FoodStacked(0), foodEaten);
  }

  public void populationGetAppetite() {
    decrementResource(new FoodStacked(0), 10);
  }

  public void populationGetSick() {
    incrementResource(new Population(0), 1);
    decrementResource(new MedicineStacked(0), 5);
  }

  public void populationBreakThing() {
    decrementResource(new ScrewStacked(0), 5);
  }

  public void populationNewBorn() {
    incrementResource(new Population(0), 10);
  }

  /**
   * The function takes a list of MandatorySettler objects, retrieves the producted resource from each
   * settler, creates a StackedResource object with the same name and quantity, and returns a list of
   * these StackedResource objects.
   * 
   * @param settlersList A list of MandatorySettler objects.
   * @return The method is returning a List of StackedResource objects.
   */
  public List<StackedResource> settlerProductionMandatory(final List<MandatorySettler> settlersList) {
    final TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    return settlersList.stream().map(settler -> {
      final BaseResource resource = settler.getProductedResource();
      return translator
          .createResourceFromNameAndQta(resource.getClass().getSimpleName() + "Stacked", resource.getQta());
    }).collect(Collectors.toList());
  }

  /**
   * The function takes a list of settlers and returns a list of stacked resources produced by each
   * settler.
   * 
   * @param settlersList A list of SimpleSettler objects.
   * @return The method is returning a List of StackedResource objects.
   */
  public List<StackedResource> settlerProductionOptional(final List<SimpleSettler> settlersList) {
    final TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    return settlersList.stream().map(settler -> {
      final BaseResource resource = settler.getProductedResource();
      return translator.createResourceFromNameAndQta(resource.getClass().getSimpleName() + "Stacked",
          resource.getQta());
    }).collect(Collectors.toList());
  }

  public void incrementWithProduction(final List<StackedResource> resourcesList) {
    resourcesList.stream().forEach(resource -> incrementResource(resource, resource.getQta()));
  }

  public List<StackedResource> getResources() {
    return resourcesList;
  }

  @Override
  public boolean checkQtaResource() {
    return resourcesList.stream().allMatch(resource -> resource.getQta() > 0);
  }
}
