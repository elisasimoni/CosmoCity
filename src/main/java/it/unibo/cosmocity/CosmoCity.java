package it.unibo.cosmocity;

import javafx.application.Application;
import javafx.stage.Stage;
import it.unibo.cosmocity.view.LandingPage;
import it.unibo.cosmocity.view.Dashboard;

import java.util.ArrayList;
import java.util.List;

import it.unibo.cosmocity.controller.timer_controller.TimeHandlerImpl;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.BaseSettlerImpl;
import it.unibo.cosmocity.model.settlers.Doctor;
import it.unibo.cosmocity.model.settlers.Gunsmith;
import it.unibo.cosmocity.model.settlers.Military;
import it.unibo.cosmocity.view.AssignSettler;


public class CosmoCity extends Application {



    public static void main(final String[] args) {
        launch(args);
    }


    public void start(Stage stage) {
        LandingPage landingPage = new LandingPage(stage, 900, 700);
        landingPage.show();
    }
}