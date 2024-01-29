package it.unibo.cosmocity.view.dialog;

import javafx.scene.control.Alert;

public class GameOverDialog implements PopUpDialog{

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void show() {
        createPopUpGUI();
        alert.showAndWait();
    }

    @Override
    public Alert createPopUpGUI() {
        alert.setTitle("Game Over :(");
        alert.setHeaderText("You lost");
        alert.setContentText("You colony failed to survive");
        return alert;
    }
    
    
}
