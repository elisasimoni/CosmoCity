package it.unibo.cosmocity.view;

import java.util.List;
import java.util.Map;

import it.unibo.cosmocity.model.event.GoodEvent;
import it.unibo.cosmocity.model.event.RandomEvent;
import it.unibo.cosmocity.model.sector.Sector.Status;

public interface DashboardView {

    /**
     * @param time
     * update the time label
     */
    void updateTimeLabel(String time);

    /**
     * @param resources
     * update the resource label
     */
    void updateResourceLabel(Map<String, Integer> resources);

    /**
     * @param statuses
     * update the circle of the sectors
     */
    void updateCirle(List<Status> statuses);

    /**
     * @param colonyName
     * update the simulation info
     */
    void updateSimulationInfo(String colonyName);

    /**
     * @param randomEvent
     * @return true if the user has fixed the event, false otherwise
     */
    boolean createRandomEvent(RandomEvent randomEvent);

    /**
     * @param goodEvent
     * @return true if the user has accepted the event, false otherwise
     */
    void createGoodEvent(GoodEvent goodEvent);

    /**
     *  Show the game over dialog
     */
    void showGameOver();

}
