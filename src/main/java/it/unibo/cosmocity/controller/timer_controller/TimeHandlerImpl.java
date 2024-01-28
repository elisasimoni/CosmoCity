package it.unibo.cosmocity.controller.timer_controller;

import java.util.ArrayList;
import java.util.List;


public class TimeHandlerImpl implements TimeHandler, TimerObservable {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void run() {
        // Ogni 5 secondi, notifica gli osservatori
        notifyObservers("Nuovo evento generato");
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public static class EventoObserver implements Observer {
        private long startTime; // Memorizza il tempo di inizio

        public EventoObserver() {
            startTime = System.currentTimeMillis();
        }

        @Override
        public long update() {
            // Calcola il tempo trascorso in secondi
            long currentTime = System.currentTimeMillis();
            long elapsedTimeInSeconds = ((currentTime - startTime) / 1000) + 1;
            // Stampa il tempo trascorso insieme all'evento
            return elapsedTimeInSeconds;
        }
    }
}
