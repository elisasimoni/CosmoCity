package it.unibo.cosmocity.model.settlers;

public class Military extends PassiveSettler{

    /**
     * The amount of food consumed by a military settler
     */
    private static final int CONSUMPTION = 2;

    @Override
    public int getConsumptionResource() {
        return CONSUMPTION;
    }

    
}
