package it.unibo.cosmocity.controller.timer_controller;

import it.unibo.cosmocity.controller.view_controller.DashboardController;
import it.unibo.cosmocity.view.dialog.PauseDialog;
import javafx.application.Platform;

/*
 * Event obsever of the ticking timer 
 */
public class EventObserver {
    private final DashboardController dashBoardController;

    /**
     * @param dashBoardController
     */
    public EventObserver(final DashboardController dashBoardController) {
        this.dashBoardController = dashBoardController;
    }

    /**
     * @param time
     *             Check if the time is equal to the time of the event
     */
    public void update(final long time) {
        if (!dashBoardController.pauseSimulation()) {
            dashBoardController.updateTimeLabel(time);
            dashBoardController.settlerAppetite(time);
            dashBoardController.getProductedResource(time);
            dashBoardController.updateResourceLabel();
            dashBoardController.createRandomEvent(time);
            dashBoardController.changeStatus();
            dashBoardController.zeroResource();
            dashBoardController.populationDoThing(time);
            dashBoardController.createGoodEvent();
        }

    }
}
