package it.unibo.cosmocity.controller.timer_controller;

import it.unibo.cosmocity.controller.view_controller.DashBoardController;


public class EventObserver {
    private DashBoardController dashBoardController;

    public EventObserver(DashBoardController dashBoardController) {
        this.dashBoardController = dashBoardController;
    }

    public void update(long time) {
        dashBoardController.updateTimeLabel(time);
        dashBoardController.updateResourceLabel();
    }
}
