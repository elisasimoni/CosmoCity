package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.controller.timer_controller.TimeHandlerImpl.EventObserver;

public class DashBoardController {

    EventObserver eventObserver = new EventObserver();

    public String setColonyName(String colonyName) {
        return colonyName;
    }

    public String updateTime() {
        return eventObserver.update() + "s";

    }
    
}
