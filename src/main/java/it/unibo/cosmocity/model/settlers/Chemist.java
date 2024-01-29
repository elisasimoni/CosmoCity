package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;

public class Chemist extends SimpleSettler {

    @Override
    public BaseResource getProductedResource() {
        return setProductedResource(3, 5, 10, 5);
    };

    @Override
    public String getSectorAssigned() {
        return "Hospital";
    }
}
