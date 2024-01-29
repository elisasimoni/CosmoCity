package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.controller.timer_controller.EventObserver;
import it.unibo.cosmocity.controller.timer_controller.TimerObservable;
import it.unibo.cosmocity.model.ResourceHandler;
import it.unibo.cosmocity.model.ResourceHandlerImpl;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.resources.Food;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.ScrewStacked;
import it.unibo.cosmocity.model.resources.WeaponsStacked;
import it.unibo.cosmocity.view.Dashboard;
import javafx.application.Platform;
import java.util.Timer;

public class DashBoardController {
    private Dashboard dashboard;
    private Simulation simulation;
    private ResourceHandler resourceHandler;
    private Timer timer;
    private TimerObservable timerObservable = new TimerObservable();
    private EventObserver eventObserver = new EventObserver(this);
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();

    public DashBoardController(Dashboard dashboard, Simulation simulation) {
        this.dashboard = dashboard;
        this.simulation = simulation;
        timer = new Timer();
        timerObservable.addObserver(eventObserver);
        timer.scheduleAtFixedRate(timerObservable, 0, 1000);
    }

    public void updateTimeLabel(long time) {
        if (time%10 == 0) {
            
            timerObservable.pause(3000);
            
        }
   

        Platform.runLater(() -> {
        dashboard.updateTimeLabel(time);
        updateResourceLabel();
    });

    }

    public void updateResourceLabel() {
        

    }

}
