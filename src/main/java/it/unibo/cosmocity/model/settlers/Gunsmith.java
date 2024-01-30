package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Weapons;

public class Gunsmith extends MandatorySettler {

    @Override
    public BaseResource getProductedResource(){
        return new Weapons(1);
    };

    @Override
    public String getSectorAssigned() {
        return "Military Base";
    }

}
