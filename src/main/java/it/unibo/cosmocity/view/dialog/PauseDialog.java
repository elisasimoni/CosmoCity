package it.unibo.cosmocity.view.dialog;

import it.unibo.cosmocity.model.utility.AudioManager;
import javafx.scene.control.Alert;

public class PauseDialog implements PopUpDialog {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    @Override
    public void show() {
        createPopUpGUI();
        alert.showAndWait();
    }

    @Override
    public Alert createPopUpGUI() {
        alert.setTitle("Game Paused");
        alert.setHeaderText("Do you want to resume?");

        return alert;
    }

}
