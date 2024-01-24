package it.unibo.cosmocity.model.settlers;


public class Technician extends PassiveSettler{

    private static final int CONSUMPTION = 1;

    @Override
    public int getConsumptionResource() {
        return CONSUMPTION;
    }
}
