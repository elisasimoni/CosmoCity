package it.unibo.cosmocity.view.dialog;

import javafx.scene.control.Alert;

public class LoadGameDialog implements PopUpDialog{

    Alert alert = new Alert(Alert.AlertType.ERROR);

    @Override
    public void show() {
       createPopUpGUI();
       alert.showAndWait();
        
    }

    @Override
    public Alert createPopUpGUI() {
        alert.setTitle("Error");
        alert.setHeaderText("No save file found");
        alert.setContentText("Please create a new game");
        return alert;
    }


    
}
