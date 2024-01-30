package it.unibo.cosmocity.view.dialog;

import java.util.Optional;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.model.event.Event;
import it.unibo.cosmocity.model.utility.AudioManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SaveGameDialog implements PopUpDialog{
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);




    @Override
    public void show() {
        createPopUpGUI();
        Optional<ButtonType> btnPressed = alert.showAndWait();
        if (btnPressed.get() == alert.getButtonTypes().get(0)) {
            new SimulationController().saveSimulation();
        } else {
            new SimulationController().exitSimulation();

        }
    }

    public Alert createPopUpGUI() {

        alert.setContentText("Do you want to save and exit?");
        ButtonType fixDamageBtn = new ButtonType("Yes");
        ButtonType getDamageBtn = new ButtonType("No");
        alert.getButtonTypes().setAll(fixDamageBtn, getDamageBtn);
        
        return alert;
    }




}
