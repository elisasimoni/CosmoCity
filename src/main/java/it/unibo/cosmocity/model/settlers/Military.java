package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;

public class Military extends SimpleSettler {

    @Override
    public BaseResource getProductedResource() {
        return setProductedResource(10, 10, 2, 2);
    };

    @Override
    public String getSectorAssigned() {
        return "Military Base";
    }

}
