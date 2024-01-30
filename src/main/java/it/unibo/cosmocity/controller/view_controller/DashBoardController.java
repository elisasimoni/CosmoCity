package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.controller.timer_controller.EventObserver;
import it.unibo.cosmocity.controller.timer_controller.TimerObservable;
import it.unibo.cosmocity.model.ResourceHandler;
import it.unibo.cosmocity.model.ResourceHandlerImpl;
import it.unibo.cosmocity.model.SectorManager;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.Sector.Status;
import it.unibo.cosmocity.model.event.Event;
import it.unibo.cosmocity.model.event.EventManager;
import it.unibo.cosmocity.model.event.RandomEvent;
import it.unibo.cosmocity.model.resources.Food;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.ScrewStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.resources.WeaponsStacked;
import it.unibo.cosmocity.view.Dashboard;
import it.unibo.cosmocity.view.DashboardView;
import javafx.application.Platform;

import java.util.List;
import java.util.Timer;

public class DashBoardController {
    private static final int TIME_APPETITE = 11;
    private static final int TIME_RANDOM_EVENT = 7;
    private DashboardView dashboardView;;
    private Simulation simulation;
    private ResourceHandler resourceHandler;
    private Timer timer;
    private TimerObservable timerObservable = new TimerObservable();
    private EventObserver eventObserver = new EventObserver(this);
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    private EventManager eventManager = new EventManager();

    public DashBoardController(DashboardView dashboardView, Simulation simulation) {
        this.dashboardView = dashboardView;
        this.simulation = simulation;
        this.resourceHandler = new ResourceHandlerImpl(simulation);
        updateSimulationInfo();
        timer = new Timer();
        timerObservable.addObserver(eventObserver);
        timer.scheduleAtFixedRate(timerObservable, 0, 1000);
    }

    public void updateTimeLabel(long time) {
        if (time % TIME_APPETITE == 0) {

            timerObservable.pause(3000);

            resourceHandler.incrementResource(new FoodStacked(2), 5);
        }

        Platform.runLater(() -> {
            dashboardView.updateTimeLabel(time);
            updateResourceLabel();
        });

    }

    public void updateResourceLabel() {
        dashboardView.updateResourceLabel(translator.translateResourceToMap(this.simulation.getResources()));
    }

    public void updateSimulationInfo() {
        dashboardView.updateSimulationInfo(simulation.getColonyName());
    }

    public void createEvent(long time) {
        Platform.runLater(() -> {
            if (time % TIME_RANDOM_EVENT == 0) {
                RandomEvent event = eventManager.generateRandomEvent();
                dashboardView.createRandomEvent(event);
                getDamage(event);

            }
        });
    }

    public void getDamage(Event event) {
        event.getDemageResources().forEach(resource -> {
            System.out.println(resource.getQta());
            resourceHandler.decrementResource(resource,resource.getQta());
        });
        
    }



    private void productioN(List<StackedResource> resources) {
        resourceHandler.incrementResource(new FoodStacked(2), 5);
        resourceHandler.incrementResource(new FoodStacked(2), 5);
        resourceHandler.incrementResource(new FoodStacked(2), 5);
    }

    public void changeStatus() {
        SectorManager sectorManager = new SectorManager(this.simulation.getResources());
        List<Status> status = sectorManager.checkStatus();
        dashboardView.updateCirle(status);
    }

}