package it.unibo.cosmocity.controller.timer_controller;

interface Observer {
    /**
     * @return the time elapsed since the start of the simulation
     */
    public void update(long time);
}