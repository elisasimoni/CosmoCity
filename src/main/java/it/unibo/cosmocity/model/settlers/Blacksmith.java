package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Screw;

public class Blacksmith extends SimpleSettler{

    @Override
    public BaseResource getProductedResource() {
        return new Screw(1);
    }

    
}
