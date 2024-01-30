package it.unibo.cosmocity.view;

import java.util.List;
import java.util.Map;

import it.unibo.cosmocity.model.Sector.Status;
import it.unibo.cosmocity.model.event.RandomEvent;

public interface DashboardView {

    void updateTimeLabel(String time);

    void updateResourceLabel(Map<String, Integer> resources);

    void updateCirle(List<Status> statuses);

    void updateSimulationInfo(String colonyName);

    void createRandomEvent(RandomEvent randomEvent);

}
