package it.unibo.cosmocity.controller.view_controller;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.controller.timer_controller.EventObserver;
import it.unibo.cosmocity.controller.timer_controller.TimerObservable;
import it.unibo.cosmocity.model.ResourceHandler;
import it.unibo.cosmocity.model.ResourceHandlerImpl;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.event.Event;
import it.unibo.cosmocity.model.event.EventManager;
import it.unibo.cosmocity.model.event.GoodEvent;
import it.unibo.cosmocity.model.event.RandomEvent;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.sector.SectorManager;
import it.unibo.cosmocity.model.sector.Sector.Status;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.MandatorySettler;
import it.unibo.cosmocity.view.DashboardView;
import it.unibo.cosmocity.view.dialog.PauseDialog;
import javafx.application.Platform;

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

    private static final int GOOD_EVENT = 57;

    private final DashboardView dashboardView;
    private final Simulation simulation;
    private final ResourceHandler resourceHandler;
    private final SimulationController simulationController;
    private final Timer timer;
    private final TimerObservable timerObservable = new TimerObservable();
    private final EventObserver eventObserver = new EventObserver(this);
    private final TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    private final EventManager eventManager = new EventManager();
    private int goodPlayer = 0;
    private int isPause = 0;
    private boolean p = false;

    public DashboardController(final DashboardView dashboardView, final Simulation simulation) {
        this.dashboardView = dashboardView;
        this.simulation = simulation;
        this.resourceHandler = new ResourceHandlerImpl(simulation);
        this.simulationController = new SimulationController(simulation);
        updateSimulationInfo();
        timer = new Timer();
        timerObservable.addObserver(eventObserver);
        timer.scheduleAtFixedRate(timerObservable, TIMER_DELAY, TIMER_PERIOD);
    }

    public void settlerAppetite(final long time) {
        if (time % TIME_SETTLER_APPETITE == 0) {
            resourceHandler.settlerGetAppetite(this.simulation.getSettlers());
        }
    }

    public void updateTimeLabel(final long time) {

        final long hours = time / TIMER_HOUR;
        final long minutes = (time % TIMER_HOUR) / TIMER_MINUT;
        final long seconds = time % TIMER_MINUT;

        Platform.runLater(() -> {
            dashboardView.updateTimeLabel(String.format("%02d : %02d : %02d", hours, minutes, seconds));
            updateResourceLabel();
        });

    }

    public void updateResourceLabel() {
        dashboardView.updateResourceLabel(translator.translateResourceToMap(this.simulation.getResources()));
        dashboardView.settlerToSectorUpdate();
    }

    public void updateSimulationInfo() {
        dashboardView.updateSimulationInfo(simulation.getColonyName());

    }

    public void createRandomEvent(final long time) {
        Platform.runLater(() -> {
            if (time % TIME_RANDOM_EVENT == 0) {
                final RandomEvent event = eventManager.generateRandomEvent();
                if (dashboardView.createRandomEvent(event)) {
                    getFixDamage(event);
                } else {
                    getDamage(event);
                }

            }
        });
    }

    /**
     * @param time
     */
    public void createGoodEvent() {

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
            final List<BaseSettler> settlers = simulation.getSettlers();
            Collections.shuffle(settlers);
            if (goodPlayer == GOOD_EVENT) {
                final GoodEvent event = eventManager.generateGoodEvent(settlers.get(0));

                dashboardView.createGoodEvent(event);
                simulationController.addSettler(event.getSettler());

            }
        });

    }

    /**
     * @param event
     * 
     */
    public void getDamage(final Event event) {
        final List<StackedResource> listResources = event.getDemageResources();
        for (final StackedResource baseResource : listResources) {
            resourceHandler.decrementResource(baseResource, baseResource.getQta());
        }

    }

    /**
     * @param event
     * 
     */
    public void getFixDamage(final Event event) {
        final List<StackedResource> listResources = event.getFixResources();
        for (final StackedResource baseResource : listResources) {
            resourceHandler.decrementResource(baseResource, baseResource.getQta());
        }

    }

    /**
     * @param time
     *             the time of the simulation
     */
    public void populationDoThing(final long time) {
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

    /**
     * @param time
     *             the time of the simulation
     */
    public void getProductedResource(final long time) {
        final List<BaseSettler> settlers = simulation.getSettlers();
        final List<MandatorySettler> mandatorySettlersList = translator.getMandatorySettlerFromAMixedList(settlers);
        final var resM = resourceHandler.settlerProductionMandatory(mandatorySettlersList);
        if (time % TIME_MANDATORY_SETTLER == 0) {
            resourceHandler.incrementWithProduction(resM);
        }

    }

    /**
     * The function "pauseSimulation" pauses the simulation and displays a pause
     * dialog, allowing the
     * user to resume or cancel the pause.
     * 
     * @return The method is returning a boolean value, which is stored in the
     *         variable "p".
     */
    public boolean pauseSimulation() {

        if (dashboardView.pauseSimulation()) {
            isPause++;
            if (isPause == 1) {

                p = true;
                timerObservable.pause(3000);
                Platform.runLater(() -> {

                    PauseDialog pausD = new PauseDialog();
                    pausD.show();
                    p = !pausD.getResume();

                });

            }

        }
        if (!p) {
            isPause = 0;
            dashboardView.setPause(false);
        } else {

        }
        return p;
    }

    /**
     * control if the resource is zero.
     * so the game is over
     */
    public void zeroResource() {
        if (!resourceHandler.checkQtaResource()) {
            simulationController.gameOverSimulation();
            dashboardView.showGameOver();
            timer.cancel();
        }
    }

    /**
     * Control the status of the sector and update the circle in the dashboard.
     */
    public void changeStatus() {
        final SectorManager sectorManager = new SectorManager(this.simulation.getResources());
        final List<Status> status = sectorManager.checkStatus();
        Platform.runLater(() -> {
            dashboardView.updateCirle(status);
        });

    }

}