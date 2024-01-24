package it.unibo.cosmocity.model;

public interface ResourcesCounter {

    /**
     * Resource value decrement
     * 
     * @param resources the resources type
     */
    void decrement(ResourcesType resources);

    /**
     * Resource value increment
     * 
     * @param resources the resources type
     */
    void increment(ResourcesType resources);
    
    /**
     * Refresh resources counter
     * 
     */
    void refresh();
    
}

