
package it.unibo.cosmocity.model;

import java.util.List;
import java.util.Set;
import it.unibo.cosmocity.model.resources.StackedResource;

public interface SimulationManager {

    /**
     * Starts the game.
     */
    <T> void startSimulation(List<T> settlers, Set<StackedResource> resources, Difficulty difficulty);

    /**
     * Pauses the game.
     */
    void pauseSimulation();

    /**
     * Resumes the game.
     */
    void resumeSimulation();
    
    /**
     * Saves the game.
     */
    void saveSimulation();

    /**
     * Loads the game.
     */
    void loadSimulation();

    /**
     * Exits the game.
     */
    void exitSimulation();

}
