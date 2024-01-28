package it.unibo.cosmocity.controller.timer_controller;

import java.util.Optional;

public interface TimeHandler {

    public Optional<Integer> getCurrentTime();

    public void startInfiniteTimer();

    public void stopTimer();

    public void pauseTimer();

    public void resumeTimer();
}