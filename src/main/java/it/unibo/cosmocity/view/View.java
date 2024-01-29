package it.unibo.cosmocity.view;

import javafx.scene.layout.Pane;

public interface View {    
    Pane createGUI();

    void refresh();

    void show();
}
