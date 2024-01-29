package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Food;

public class Cook extends SimpleSettler{

    @Override
    public BaseResource getProductedResource(){
        return setProductedResource(4, 10, 15, 5);
    };

    @Override
    public String getSectorAssigned() {
        return "Farm";
    }
    
}
