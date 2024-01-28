package it.unibo.cosmocity.controller.timer_controller;

public interface TimerObservable {

    /**
     * @param observer
     * Add an observer to the list of observers
     */
    void notifyObservers(String event);
}

