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
import it.unibo.cosmocity.model.event.GoodEvent;
import it.unibo.cosmocity.model.event.RandomEvent;
import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Food;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.MandatorySettler;
import it.unibo.cosmocity.model.settlers.SimpleSettler;
import it.unibo.cosmocity.view.DashboardView;
import javafx.application.Platform;

import java.util.*;

import org.jooq.lambda.Collectable;

public class DashboardController {
    private static final int TIMER_PERIOD = 1000;
    private static final int TIMER_DELAY = 0;

    private static final int TIMER_HOUR = 3600;
    private static final int TIMER_MINUT = 60;

    private static final int TIME_SETTLER_APPETITE = 11;
    private static final int TIME_POPULATION_APPETITE = 30;
    private static final int TIME_POPULATION_SICK = 65;
    private static final int TIME_POPULATION_BREAK = 70;
    private static final int TIME_RANDOM_EVENT = 123;
    private static final int TIME_MANDATORY_SETTLER = 150;
    private static final int TIME_OPTIONAL_SETTLER = 70;

    private static final int RESOURCE_TO_ADD = 5;
    private static final int RESOURCE_TO_REMOVE = 50;
    private static final int RESOURCE_QUANTITY_DEFAULT = 0;

    private static final int GOOD_EVENT = 57;

    private DashboardView dashboardView;
    private Simulation simulation;
    private ResourceHandler resourceHandler;
    private SimulationController simulationController;
    private Timer timer;
    private TimerObservable timerObservable = new TimerObservable();
    private EventObserver eventObserver = new EventObserver(this);
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    private EventManager eventManager = new EventManager();
    private int goodPlayer = 0;

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

    public void settlerAppetite(long time) {
        if (time % TIME_SETTLER_APPETITE == 0) {
            resourceHandler.settlerGetAppetite(this.simulation.getSettlers());
        }
    }

    public void updateTimeLabel(long time) {

        long hours = time / TIMER_HOUR;
        long minutes = (time % TIMER_HOUR) / TIMER_MINUT;
        long seconds = time % TIMER_MINUT;

        Platform.runLater(() -> {
            dashboardView.updateTimeLabel(String.format("%02d : %02d : %02d", hours, minutes, seconds));
            updateResourceLabel();
        });

    }

    public void updateResourceLabel() {
        dashboardView.updateResourceLabel(translator.translateResourceToMap(this.simulation.getResources()));
    }

    public void updateSimulationInfo() {
        dashboardView.updateSimulationInfo(simulation.getColonyName());
        dashboardView.updateSettlerInfo(translator.translateSettlerToString(simulation.getSettlers()));

    }

    public void createRandomEvent(long time) {
        Platform.runLater(() -> {
            if (time % TIME_RANDOM_EVENT == 0) {
                RandomEvent event = eventManager.generateRandomEvent();
                if (dashboardView.createRandomEvent(event)) {
                    getFixDamage(event);
                } else {
                    getDamage(event);
                }

            }
        });
    }

    public void createGoodEvent(long time) {

        this.simulation.getResources().stream().forEach(
                resource -> {
                    if (resource.getQta() > this.simulation.getResources().get(0).getQta() / 2) {
                        goodPlayer++;
                    } else {
                        goodPlayer = 0;
                    }
                }

        );
        Platform.runLater(() -> {
            List<BaseSettler> settlers = simulation.getSettlers();
            Collections.shuffle(settlers);
            if (goodPlayer == GOOD_EVENT) {
                GoodEvent event = eventManager.generateGoodEvent(settlers.get(0));
                dashboardView.createGoodEvent(event);
                simulationController.addSettler(event.getSettler());

            }
        });

    }

    public void getDamage(Event event) {
        List<StackedResource> listResources = event.getDemageResources();
        for (StackedResource baseResource : listResources) {
            resourceHandler.decrementResource(baseResource, baseResource.getQta());
        }

    }

    public void getFixDamage(Event event) {
        List<StackedResource> listResources = event.getFixResources();
        for (StackedResource baseResource : listResources) {
            resourceHandler.decrementResource(baseResource, baseResource.getQta());
        }

    }

    public void populationDoThing(long time) {
        if (time % TIME_POPULATION_APPETITE == 0) {
            resourceHandler.populationGetAppetite();
        } else if (time % TIME_POPULATION_SICK == 0) {
            resourceHandler.populationGetSick();
        } else if (time % TIME_POPULATION_BREAK == 0) {
            resourceHandler.populationBreakThing();
        } else if (time % 350 == 0) {
            resourceHandler.populationNewBorn();
        }
    }

    public void getProductedResource(long time) {
        List<BaseSettler> settlers = simulation.getSettlers();
        List<SimpleSettler> optionalSettlers = translator.getOptionalSettlerFromAMixedList(settlers);
        List<MandatorySettler> mandatorySettlersList = translator.getMandatorySettlerFromAMixedList(settlers);
        var resM = resourceHandler.settlerProductionMandatory(mandatorySettlersList);
        var resO = resourceHandler.settlerProductionOptional(optionalSettlers);
        if (time % TIME_MANDATORY_SETTLER == 0) {
            resourceHandler.incrementWithProduction(resM);
        } else if (time % TIME_OPTIONAL_SETTLER == 0) {
            resourceHandler.incrementWithProduction(resO);

        }

    }


    public void zeroResource() {
        if (!resourceHandler.checkQtaResource()) {
            simulationController.gameOverSimulation();
            Thread.currentThread().interrupt();
            dashboardView.showGameOver();

        }
    }

    public void changeStatus() {
        SectorManager sectorManager = new SectorManager(this.simulation.getResources());
        List<Status> status = sectorManager.checkStatus();
        Platform.runLater(() -> {
            dashboardView.updateCirle(status);
        });

    }

}