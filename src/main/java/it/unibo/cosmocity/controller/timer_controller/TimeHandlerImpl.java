package it.unibo.cosmocity.controller.timer_controller;

import java.util.Timer; 

public class TimeHandlerImpl  extends TimerObservable implements TimeHandler{

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(this, 0, 1000);
    }

}