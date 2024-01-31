package it.unibo.cosmocity.view.dialog;

import javafx.scene.control.Alert;

public class PauseDialog implements PopUpDialog {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    boolean resume = false;
    long id;
    public PauseDialog() {
        this.id = id;
    }
    @Override
    public void show() {
        createPopUpGUI();
        alert.show();
        if (alert.getResult().getText().equals("OK")) {
            resume = true;
        }else{
            
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
