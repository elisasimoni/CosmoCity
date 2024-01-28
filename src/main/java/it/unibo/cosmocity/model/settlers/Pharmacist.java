package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Medicine;

public class Pharmacist extends SimpleSettler{

    @Override
    public BaseResource getProductedResource() {
        return new Medicine(2);
    }

    
}
