package it.unibo.cosmocity.controller.timer_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class TimerObservable extends TimerTask {

    private final static int DELAY = 2;
    
    private List<EventObserver> observers = new ArrayList<>();
    private boolean isPaused = false;
    private long pauseTime;
    private long pauseDuration;
    private long totalPauseTime = 0;

    private final Object lock = new Object(); // Object to manage synchronization
    private long startTime = System.currentTimeMillis();

    // Add Event Observer
    public void addObserver(EventObserver observer) {
        observers.add(observer);
    }

    // Add Event Observer
    public void removeObserver(EventObserver observer) {
        observers.remove(observer);
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    @Override
    public void run() {

        long waitTime = 0;

        while (true) {

            synchronized (lock) {

                if (isPaused) {

                    try {

                        waitTime = pauseTime + pauseDuration + 1000 - System.currentTimeMillis();

                        if (waitTime > 0) {

                            lock.wait(waitTime);

                        }

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                    totalPauseTime += pauseDuration;
                    isPaused = false;
                    pauseDuration = 0;

                }
            }

            long timeMillis = System.currentTimeMillis() / 1000;

            long timeElapsedSeconds = ((timeMillis) - startTime / 1000);

            notifyObservers(
                    timeElapsedSeconds - waitTime / 1000 + pauseDuration / 1000 - totalPauseTime / 1000 + DELAY);

            try {

                Thread.sleep(1000); // Sleep for 1 second

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }
    }

    public void pause(long howMuchPause) {
        synchronized (lock) {
            this.isPaused = true;
            this.pauseTime = System.currentTimeMillis();
            this.pauseDuration = howMuchPause;
        }
    }

    private void notifyObservers(long time) {
        for (EventObserver observer : observers) {
            observer.update(time);
        }
    }

    public void resume() {
        synchronized (lock) {
            this.isPaused = false;
            totalPauseTime += System.currentTimeMillis() - pauseTime;
            lock.notifyAll();
        }
    }
}
