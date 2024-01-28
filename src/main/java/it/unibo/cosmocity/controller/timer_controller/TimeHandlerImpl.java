package it.unibo.cosmocity.controller.timer_controller;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TimeHandlerImpl implements TimeHandler{
    private static final int STEP = 1; 
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final Semaphore pauseSemaphore = new Semaphore(1);
    
    private int time;

    public TimeHandlerImpl(int time) {
        this.time = time;
    }

    public Optional<Integer> getCurrentTime() {
        if (executorService.isShutdown()) {
            return Optional.empty();
        } else {
            return Optional.of(time);
        }
    }

    public void startInfiniteTimer() {
        this.executorService.scheduleAtFixedRate(() -> {
            this.time += STEP;
        }, 0, STEP, TimeUnit.SECONDS);

    }

    public void stopTimer() {
        this.executorService.shutdown();
    }

    public void pauseTimer() {
        try {
            pauseSemaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Timer interrupted", e);
        }
    }

    public void resumeTimer() {
        pauseSemaphore.release();
    }

}
