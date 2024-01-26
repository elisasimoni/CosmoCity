package it.unibo.cosmocity.view;

import javafx.scene.layout.Pane;

public interface View {

    Pane createLayout();

    void setupResizeListeners();


    void show();
}
