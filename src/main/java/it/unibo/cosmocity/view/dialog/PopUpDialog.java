package it.unibo.cosmocity.view.dialog;

import javafx.scene.control.Alert;

/**
 * Interface for generic pop up dialog.
 */
public interface PopUpDialog {

    public void show();

    public Alert createPopUpGUI();
    
}
