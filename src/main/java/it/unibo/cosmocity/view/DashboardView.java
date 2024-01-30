package it.unibo.cosmocity.view;

import java.util.List;
import java.util.Map;

import it.unibo.cosmocity.model.Sector.Status;
import it.unibo.cosmocity.model.event.GoodEvent;
import it.unibo.cosmocity.model.event.RandomEvent;
import it.unibo.cosmocity.model.settlers.BaseSettler;

public interface DashboardView {

    void updateTimeLabel(String time);

    void updateResourceLabel(Map<String, Integer> resources);

    void updateCirle(List<Status> statuses);

    void updateSimulationInfo(String colonyName);

    boolean createRandomEvent(RandomEvent randomEvent);

    void createGoodEvent(GoodEvent goodEvent);

    void showGameOver();

    void updateSettlerInfo(List<String> settlers);

}
