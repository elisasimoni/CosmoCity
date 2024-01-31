package it.unibo.cosmocity.view;

import javafx.scene.layout.Pane;
/*
 * This interface is used to create the GUI of the view
 */
public interface View {    
    /**
     * @return the GUI of the view
     */
    Pane createGUI();

    /**
     *  Show the view
     */
    void show();
}
