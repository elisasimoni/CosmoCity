package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.view.View;

public class SceneController {

    public void nextSceneNavigator(View nextView) {
        nextView.show();

    }

    public void previousSceneNavigator(View previousView) {
        previousView.show();
    }

    public void dialogPaneNavigator(View nextView) {
        nextView.show();
    }



}
