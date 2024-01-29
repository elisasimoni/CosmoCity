package it.unibo.cosmocity.controller.serialization;

public interface Serialization {

    public void serialize(Object object);

    public Object deserialize();
    
}
