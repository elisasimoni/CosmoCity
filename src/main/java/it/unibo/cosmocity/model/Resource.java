package it.unibo.cosmocity.model;

public class Resource {

    private int resourceValue;

    Resource(final int resourceValue){
        this.resourceValue = resourceValue;
    }

    public int getResourceValue() {
        return this.resourceValue;
    }

    public void setResourceValue(int resourceValue) {
        this.resourceValue = resourceValue;
    }
    
}
