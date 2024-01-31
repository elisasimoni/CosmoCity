package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;

public abstract class MandatorySettler implements BaseSettler {

    /**
     * Appetite of the settler
     */
    private int appetite;

    /**
     * @return the appetite of the settler
     */
    public int getAppetite() {
        return this.appetite;
    }


    /**
     * @return the resource produced by the settler
     */
    public abstract BaseResource getProductedResource();

}
