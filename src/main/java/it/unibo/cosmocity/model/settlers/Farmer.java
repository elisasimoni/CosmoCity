package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Food;

public class Farmer extends MandatorySettler {

    @Override
    public BaseResource getProductedResource() {
        return new Food(1);
    }

    @Override
    public String getSectorAssigned() {
        return "Farm";
    }

}
