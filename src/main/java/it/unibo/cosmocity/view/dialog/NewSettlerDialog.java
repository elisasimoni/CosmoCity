package it.unibo.cosmocity.view.dialog;

import javafx.scene.control.Alert;

public class NewSettlerDialog implements PopUpDialog {
    Alert alert = new Alert(Alert.AlertType.WARNING);

    @Override
    public void show() {
        createPopUpGUI();
        alert.showAndWait();
    }

    public Alert createPopUpGUI() {

        alert.setTitle("New settler unlocked!");
        alert.setHeaderText("Good news!");
        alert.setContentText("You have unlocked a new settler!");

        return alert;
    }

}
