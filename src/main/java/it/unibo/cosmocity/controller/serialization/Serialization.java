package it.unibo.cosmocity.controller.serialization;

public interface Serialization {

    /**
     * @param object to serialize
     */
    public void serialize(Object object);

    /**
     * @return a list of object deserialize
     */
    public Object deserialize();
    
}
