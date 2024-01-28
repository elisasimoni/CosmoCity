package it.unibo.cosmocity.view;

import javafx.scene.layout.Pane;

public interface View {

    Pane createGUI();

    void initLogic();

    void refresh();

    void show();
}
