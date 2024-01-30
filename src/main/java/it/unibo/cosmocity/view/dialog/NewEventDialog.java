package it.unibo.cosmocity.view.dialog;

import java.util.Optional;

import it.unibo.cosmocity.model.event.Event;

import it.unibo.cosmocity.model.utility.AudioManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class NewEventDialog implements PopUpDialog {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    AudioManager audioManager = new AudioManager();


    public NewEventDialog(Event event) {
        alert.setTitle(event.getName());
        alert.setHeaderText(event.getDescription());
    }


    @Override
    public void show() {
        createPopUpGUI();
        Optional<ButtonType> btnPressed = alert.showAndWait();
        if (btnPressed.get() == alert.getButtonTypes().get(0)) {
            //audioManager.play("good_event_sound.mp3");
        } else {
            //audioManager.play("bad_event_sound.mp3");

        }
    }

    public Alert createPopUpGUI() {

        alert.setContentText("Do you want to fix or get dameged?");
        ButtonType fixDamageBtn = new ButtonType("Fix");
        ButtonType getDamageBtn = new ButtonType("Ignore");
        alert.getButtonTypes().setAll(fixDamageBtn, getDamageBtn);
        
        return alert;
    }




}
