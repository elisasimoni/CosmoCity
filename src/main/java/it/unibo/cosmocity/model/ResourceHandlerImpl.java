package it.unibo.cosmocity.model;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import java.util.List;

public class ResourceHandlerImpl implements ResourceHandler {

  private final List<StackedResource> resourcesList;

  public ResourceHandlerImpl(final Simulation simulation) {
    this.resourcesList = simulation.getResources();
  }

  @Override
  public void incrementResource(final BaseResource resource, final int valueToAdd) {
    resourcesList.stream()
      .filter(r -> r.getClass().equals(resource.getClass()))
      .forEach(r -> r.setQta(r.getQta() + valueToAdd));
  }

  @Override
  public void decrementResource(final BaseResource resource, final int valueToSubtract) {
    resourcesList.stream()
      .filter(r -> r.getClass().equals(resource.getClass()))
      .forEach(r -> r.setQta(r.getQta() - valueToSubtract));
  }

  public void settlerGetAppetite(final List<BaseSettler> settlersList) {
    FoodStacked foodEaten = new FoodStacked(settlersList.stream().mapToInt(BaseSettler::getAppetite).sum());
    decrementResource(foodEaten, foodEaten.getQta());
  }

  public List<StackedResource> getResources() {
    return resourcesList;
  }

  @Override
  public boolean checkQtaResource() {
    return resourcesList.stream().allMatch(resource -> resource.getQta() > 0);
  }
}
