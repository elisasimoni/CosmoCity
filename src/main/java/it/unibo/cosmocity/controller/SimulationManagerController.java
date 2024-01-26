
package it.unibo.cosmocity.controller;

import java.util.List;
import java.util.Set;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.Difficulty;
/**
 * The GameManagerController interface represents the controller of the game state.
 * It provides methods for starting, pausing, resuming, stopping, saving,
 * loading, and exiting the game.
 */
public interface SimulationManagerController {

    /**
     * Starts the game.
     */
    <T> void startGame(List<T> settlers, Set<StackedResource> resources, Difficulty difficulty);

    /**
     * Pauses the game.
     */
    void pauseGame();

    /**
     * Resumes the game.
     */
    void resumeGame();

    /**
     * Stops the game.
     */
    void stopGame();

    /**
     * Saves the game.
     */
    void saveGame();

    /**
     * Loads the game.
     */
    void loadGame();

    /**
     * Exits the game.
     */
    void exitGame();

}
