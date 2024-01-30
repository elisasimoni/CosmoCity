package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.controller.SimulationController;
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
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.view.DashboardView;
import javafx.application.Platform;

import java.util.List;
import java.util.Timer;

public class DashboardController {
    private static final int TIMER_PERIOD = 1000;
    private static final int TIMER_DELAY = 0;

    private static final int TIMER_HOUR = 3600;
    private static final int TIMER_MINUT = 60;

    private static final int TIME_APPETITE = 11;
    private static final int TIME_RANDOM_EVENT = 120;

    private static final int RESOURCE_TO_ADD = 5;
    private static final int RESOURCE_QUANTITY = 2;

    private DashboardView dashboardView;;
    private Simulation simulation;
    private ResourceHandler resourceHandler;
    private SimulationController simulationController;
    private Timer timer;
    private TimerObservable timerObservable = new TimerObservable();
    private EventObserver eventObserver;
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    private EventManager eventManager = new EventManager();

    public DashboardController(DashboardView dashboardView, Simulation simulation) {
        this.dashboardView = dashboardView;
        this.simulation = simulation;
        this.resourceHandler = new ResourceHandlerImpl(simulation);
        this.simulationController = new SimulationController(simulation);
        updateSimulationInfo();
        timer = new Timer();
        timerObservable.addObserver(eventObserver);
        timer.scheduleAtFixedRate(timerObservable, TIMER_DELAY, TIMER_PERIOD);
    }

    public void updateTimeLabel(long time) {
        if (time % TIME_APPETITE == 0) {

            resourceHandler.incrementResource(new FoodStacked(RESOURCE_QUANTITY), RESOURCE_TO_ADD);
        }

        long hours = time / TIMER_HOUR;
        long minutes = (time % TIMER_HOUR) / TIMER_MINUT;
        long seconds = time % TIMER_MINUT;

        Platform.runLater(() -> {
            dashboardView.updateTimeLabel(String.format("%02d:%02d:%02d", hours, minutes, seconds));
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
            this.resourceHandler.decrementResource(resource, resource.getQta());
        });

    }

    public void changeStatus() {
        SectorManager sectorManager = new SectorManager(this.simulation.getResources());
        List<Status> status = sectorManager.checkStatus();
        dashboardView.updateCirle(status);
    }

}