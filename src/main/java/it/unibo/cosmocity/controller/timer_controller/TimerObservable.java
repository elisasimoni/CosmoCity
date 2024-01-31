package it.unibo.cosmocity.controller.timer_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/*
 * Timer observable 
 */
public class TimerObservable extends TimerTask {

    private static final int DELAY = 2;
    private static final int MILLIS = 1000;

    private final List<EventObserver> observers = new ArrayList<>();
    private boolean isPaused = false;
    private long pauseTime;
    private long pauseDuration;
    private long totalPauseTime = 0;

    private final Object lockTimer = new Object(); // Object to manage synchronization

    private final long startTime = System.currentTimeMillis();

    /**
     * @param observer
     *                 Add an observer to the list
     */
    public void addObserver(final EventObserver observer) {
        observers.add(observer);
    }

    /**
     * @param observer
     *                 Remove an observer from the list
     */
    public void removeObserver(final EventObserver observer) {
        observers.remove(observer);
    }

    /**
     * @return true if the timer is paused
     */
    public boolean isPaused() {
        return this.isPaused;
    }

    @Override
    public void run() {

        long waitTime = 0;

        while (true) {

            synchronized (lockTimer) {

                if (isPaused) {

                    try {

                        waitTime = pauseTime + pauseDuration + MILLIS - System.currentTimeMillis();

                        if (waitTime > 0) {

                            lockTimer.wait(waitTime);

                        }

                    } catch (final InterruptedException e) {

                        e.printStackTrace();

                    }

                    totalPauseTime += pauseDuration;
                    isPaused = false;
                    pauseDuration = 0;

                }
            }

            final long timeMillis = System.currentTimeMillis() / MILLIS;

            final long timeElapsedSeconds = ((timeMillis) - startTime / MILLIS);

            notifyObservers(
                    timeElapsedSeconds - waitTime / MILLIS + pauseDuration / MILLIS - totalPauseTime / MILLIS + DELAY);

            try {

                Thread.sleep(MILLIS); // Sleep for 1 second

            } catch (final InterruptedException e) {

                e.printStackTrace();

            }

        }
    }

    /**
     * @param howMuchPause
     *                     Do a pause of the timer
     */
    public void pause(final long howMuchPause) {
        synchronized (lockTimer) {
            this.isPaused = true;
            this.pauseTime = System.currentTimeMillis();
            this.pauseDuration = howMuchPause;
        }
    }

    /*
     * Resume the timer
     */
    public void resume() {
        synchronized (lockTimer) {
            this.isPaused = false;
            totalPauseTime += System.currentTimeMillis() - pauseTime;
            lockTimer.notifyAll();
        }
    }

    /**
     * @param time
     *             Notify the observers
     */
    private void notifyObservers(final long time) {
        for (final EventObserver observer : observers) {
            observer.update(time);
        }
    }
}
