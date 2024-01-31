package it.unibo.cosmocity.view;

public interface LandingPageView {
    
    /**
     *  Start a new simulation
     */
    void startSimulation();

    /**
     *  Load a previous simulation
     */
    void loadSimulation();

    /**
     *  Exit from the application
     */
    void exitSimulation();

}
