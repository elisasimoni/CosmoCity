package it.unibo.cosmocity.view.dialog;

import it.unibo.cosmocity.model.utility.AudioManager;
import javafx.scene.control.Alert;

public class NewSettlerDialog implements PopUpDialog {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
      AudioManager audioManager = new AudioManager();
        

    @Override
    public void show() {
        createPopUpGUI();
        alert.showAndWait();
    }

    public Alert createPopUpGUI() {
        audioManager.play("audio/good_event_sound.mp3");
        alert.setTitle("New settler unlocked!");
        alert.setHeaderText("Good news!");
        alert.setContentText("You have unlocked a new settler!");

        return alert;
    }

}
