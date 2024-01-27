package it.unibo.cosmocity.controller.timer_controller;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public interface TimeHandler {

    public Optional<Integer> getCurrentTime();

    public void startInfiniteTimer();

    public void stopTimer();

    public void pauseTimer();

    public void resumeTimer();
}