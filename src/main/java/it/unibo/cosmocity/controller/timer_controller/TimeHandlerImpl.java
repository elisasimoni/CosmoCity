package it.unibo.cosmocity.controller.timer_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer; 

public class TimeHandlerImpl implements TimeHandler, TimerObservable {
    private List<Observer> observers = new ArrayList<>();
    Timer timer = new Timer();
    
    /**
     * @param observer
     * Add an observer to the list of observers
     */

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    /**
     * @param observer
     * Remove an observer to the list of observers
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
/**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        notifyObservers("Nuovo evento generato");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public static class EventObserver implements Observer {
        private long startTime;

        public EventObserver() {
            startTime = System.currentTimeMillis();
        }

        @Override
        public long update() {
            return ((System.currentTimeMillis() - startTime) / 1000) + 1;
        }
    }
}
