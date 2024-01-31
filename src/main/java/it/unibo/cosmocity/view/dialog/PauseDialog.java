package it.unibo.cosmocity.view.dialog;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;

public class PauseDialog implements PopUpDialog {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    boolean resume = true;

    /**
     * The function creates a pop-up GUI, shows it to the user, and sets the value of the "resume"
     * variable based on the user's response.
     */
    @Override
    public void show() {

        createPopUpGUI();
        alert.showAndWait();
        if (alert.getResult() != null && alert.getResult().getButtonData() == ButtonData.OK_DONE) {
            resume = true;
        } else {
            resume = false;
        }

    }

    @Override
    public Alert createPopUpGUI() {
        alert.setTitle("Game Paused");
        alert.setHeaderText("Do you want to resume?");

        return alert;
    }

    public boolean getResume() {
        return resume;
    }

}
