package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;

public class Chemist extends SimpleSettler {

    private static final int WORKSHOP_PRODUCTION = 1;
    private static final int MILITARY_BASE_PRODUCTION = 20;
    private static final int HOSPITAL_PRODUCTION = 15;
    private static final int FARM_PRODUCTION = 5;

    @Override
    public BaseResource getProductedResource() {
        return setProductedResource(WORKSHOP_PRODUCTION, MILITARY_BASE_PRODUCTION, HOSPITAL_PRODUCTION,
                FARM_PRODUCTION);

    }

}
