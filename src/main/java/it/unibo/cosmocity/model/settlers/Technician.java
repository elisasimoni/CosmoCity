package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Screw;

public class Technician extends SimpleSettler{

    @Override
    public String getSectorAssigned() {
        return "Workshop";
    }

    @Override
    public BaseResource getProductedResource() {
        return setProductedResource(10, 10, 2, 2);
    
    }
}
