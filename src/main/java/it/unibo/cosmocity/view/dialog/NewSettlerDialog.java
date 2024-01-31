package it.unibo.cosmocity.view.dialog;

import it.unibo.cosmocity.model.event.GoodEvent;
import it.unibo.cosmocity.model.utility.AudioManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class NewSettlerDialog implements PopUpDialog {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    AudioManager audio = new AudioManager();

    public NewSettlerDialog(final GoodEvent event) {
        alert.setTitle(event.getName());
        alert.setHeaderText("You have recived a new settler" + "\n" + event.getDescription() + "\n"
                + event.getSettler().getClass().getSimpleName());
    }

    @Override
    public void show() {
        createPopUpGUI();

    }

    
    /** 
     * @return Alert
     */
    public Alert createPopUpGUI() {
        final Thread audioThread = new Thread(() -> {
            audio.play("good.wav");
        });
        audioThread.start();

        alert.setContentText("Recive this kind blessing");
        final ButtonType yesBtn = new ButtonType("HURRAY!");
        alert.getButtonTypes().setAll(yesBtn);

        return alert;
    }

}
