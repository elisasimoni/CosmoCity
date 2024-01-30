package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.controller.timer_controller.EventObserver;
import it.unibo.cosmocity.controller.timer_controller.TimerObservable;
import it.unibo.cosmocity.model.ResourceHandler;
import it.unibo.cosmocity.model.ResourceHandlerImpl;
import it.unibo.cosmocity.model.SectorManager;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.event.EventManager;
import it.unibo.cosmocity.model.resources.Food;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.ScrewStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.resources.WeaponsStacked;
import it.unibo.cosmocity.view.Dashboard;
import it.unibo.cosmocity.view.Sector.Status;
import javafx.application.Platform;

import java.util.List;
import java.util.Timer;

public class DashBoardController {
    private Dashboard dashboard;
    private Simulation simulation;
    private ResourceHandler resourceHandler;
    private Timer timer;
    private TimerObservable timerObservable = new TimerObservable();
    private EventObserver eventObserver = new EventObserver(this);
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    private CreateColonyController createColonyController = new CreateColonyController();
    private EventManager eventManager = new EventManager();

    public DashBoardController(Dashboard dashboard, Simulation simulation) {
        this.dashboard = dashboard;
        this.simulation = simulation;
        this.resourceHandler = new ResourceHandlerImpl(simulation);
   
        timer = new Timer();
        timerObservable.addObserver(eventObserver);
        timer.scheduleAtFixedRate(timerObservable, 0, 1000);
    }

    public void updateTimeLabel(long time) {
        if (time % 10 == 0) {

            timerObservable.pause(3000);
            
            resourceHandler.incrementResource(new FoodStacked(2), 5);
        }

        Platform.runLater(() -> {
            dashboard.updateTimeLabel(time);
            updateResourceLabel();
        });

    }

    public void updateResourceLabel() {
        

        dashboard.updateResourceLabel(translator.translateResourceToMap(this.simulation.getResources()));
    }

    public void createEvent(long time) {
        if (time % 30 == 0) {
            eventManager.generateRandomEvent();
        }
    }

    public void changeStatus() {
        SectorManager sectorManager = new SectorManager();
        List<Status> status = sectorManager.checkStatus(this.simulation.getResources());
        dashboard.updateCirle(status);
    }

}