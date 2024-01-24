package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Weapons;

public class Gunsmith extends BaseSettlerImpl{

    @Override
    public BaseResource getProductedResource() {
        return new Weapons(1);
    }
    
}
