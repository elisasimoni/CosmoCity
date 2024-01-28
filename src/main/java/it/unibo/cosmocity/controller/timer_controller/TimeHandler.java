package it.unibo.cosmocity.controller.timer_controller;

interface Observer {
    long update();


interface TimerObservable {
    void notifyObservers(String event);
}

public interface TimeHandler {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void run();
}
