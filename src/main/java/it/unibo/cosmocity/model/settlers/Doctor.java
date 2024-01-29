package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Medicine;

public class Doctor extends MandatorySettler {

    @Override
    public BaseResource getProductedResource() {
        return new Medicine(1);
    }

    @Override
    public String getSectorAssigned() {
        return "Hospital";
    }

}
