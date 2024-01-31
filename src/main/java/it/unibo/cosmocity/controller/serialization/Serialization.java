package it.unibo.cosmocity.controller.serialization;

import java.io.File;

public interface Serialization {

    /**
     * @param object to serialize
     */
    public abstract void serialize(Object object, File file);

    /**
     * @return a list of object deserialize
     */
    public abstract Object deserialize();
    
    /**
     * @return a list of object deserialize
     */
    public abstract Object deserializeFromExtern(File file);
    
}
