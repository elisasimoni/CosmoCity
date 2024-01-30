package it.unibo.cosmocity.model;

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

import java.util.stream.Collectors;
import java.util.List;

public class ResourceHandlerImpl implements ResourceHandler {

  private final List<StackedResource> resourcesList;

  public ResourceHandlerImpl(final Simulation simulation) {
    this.resourcesList = simulation.getResources();
  }

  @Override
  public void incrementResource(BaseResource resource, int valueToAdd) {
    resourcesList.stream()
        .filter(r -> r.getClass().equals(resource.getClass()))
        .forEach(r -> r.setQta(r.getQta() + valueToAdd));
  }

  @Override
  public void decrementResource(BaseResource resource, int valueToSubtract) {
    resourcesList.stream()
        .filter(r -> r.getClass().equals(resource.getClass()))
        .forEach(r -> r.setQta(r.getQta() - valueToSubtract));
  }

  public void settlerGetAppetite(List<BaseSettler> settlersList) {
    FoodStacked foodEaten = new FoodStacked(settlersList.stream().mapToInt(BaseSettler::getAppetite).sum());
    decrementResource(foodEaten, foodEaten.getQta());
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

  public List<StackedResource> settlerProductionMandatory(List<MandatorySettler> settlersList) {
    TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    return settlersList.stream().map(settler -> {
      BaseResource resource = settler.getProductedResource();
      StackedResource stackedResource = translator
          .createResourceFromNameAndQta(resource.getClass().getSimpleName() + "Stacked", resource.getQta());
      incrementResource(stackedResource, stackedResource.getQta());
      return stackedResource;
    }).collect(Collectors.toList());
  }

  public List<StackedResource> settlerProductionOptional(List<SimpleSettler> settlersList) {
    TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    return settlersList.stream().map(settler -> {
      BaseResource resource = settler.getProductedResource();
      StackedResource stackedResource = translator
          .createResourceFromNameAndQta(resource.getClass().getSimpleName() + "Stacked", resource.getQta());
      incrementResource(stackedResource, stackedResource.getQta());
      return stackedResource;
    }).collect(Collectors.toList());
  }
  

  public List<StackedResource> getResources() {
    return resourcesList;
  }

  @Override
  public boolean checkQtaResource() {
    return resourcesList.stream().allMatch(resource -> resource.getQta() > 0);
  }
}
