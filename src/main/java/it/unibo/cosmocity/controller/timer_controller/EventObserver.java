package it.unibo.cosmocity.controller.timer_controller;

import it.unibo.cosmocity.controller.view_controller.DashboardController;


public class EventObserver {
    private DashboardController dashBoardController;

    public EventObserver(DashboardController dashBoardController) {
        this.dashBoardController = dashBoardController;
    }

    public void update(long time) {
        dashBoardController.updateTimeLabel(time);
        dashBoardController.settlerAppetite(time);
        dashBoardController.getProductedResource(time);
        dashBoardController.updateResourceLabel();
        dashBoardController.createRandomEvent(time);
        dashBoardController.changeStatus();
        dashBoardController.zeroResource();
        dashBoardController.populationDoThing(time);
        dashBoardController.createGoodEvent(time);

    }
}
