package it.unibo.cosmocity.model.settlers;

public abstract class PassiveSettler implements BaseSettler{
    
    /**
     * Appetite of the settler
     */
    private int appetite;
    
    /**
     * @return the appetite of the settler
     */
    public int getAppetite(){
        return this.appetite;
    }

    /**
     * @return the resource consumed by the settler
     */
    public abstract int getConsumptionResource();
    
}
