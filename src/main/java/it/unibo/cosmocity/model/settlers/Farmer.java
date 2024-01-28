package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Food;

public class Farmer extends SimpleSettler{

    @Override
    public BaseResource getProductedResource() {
        return new Food(1);
    }

    
}
