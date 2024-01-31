package it.unibo.cosmocity.view.dialog;

import javafx.scene.control.Alert;

/**
 * Interface for generic pop up dialog.
 */
public interface PopUpDialog {

    /**
     *  Show the pop up dialog
     */
    public void show();

    /**
     * @return the GUI of the pop up dialog
     */
    public Alert createPopUpGUI();
    
}
