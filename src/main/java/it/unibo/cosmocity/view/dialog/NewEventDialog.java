package it.unibo.cosmocity.view.dialog;

import java.util.Optional;

import it.unibo.cosmocity.model.event.Event;

import it.unibo.cosmocity.model.utility.AudioManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class NewEventDialog implements PopUpDialog {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    AudioManager audio = new AudioManager();
    boolean chosen = false;


    public NewEventDialog(Event event, boolean chose) {
        alert.setTitle(event.getName());
        alert.setHeaderText(event.getDescription());
    }


    @Override
    public void show() {
        createPopUpGUI();
        final Thread audioThread = new Thread(() -> {
            audio.play("bad.wav");
        });
        audioThread.start();
        Optional<ButtonType> btnPressed = alert.showAndWait();
        if (btnPressed.get() == alert.getButtonTypes().get(0)) {
            chosen = true;
        } else {
            chosen = false;

        }
    }

    
    /** 
     * @return Alert
     */
    public Alert createPopUpGUI() {

        alert.setContentText("Do you want to fix or get dameged?");
        ButtonType fixDamageBtn = new ButtonType("Fix");
        ButtonType getDamageBtn = new ButtonType("Ignore");
        alert.getButtonTypes().setAll(fixDamageBtn, getDamageBtn);
        
        return alert;
    }

    public boolean getChosen() {
        return chosen;
    }




}
