package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.view.View;

/**
 * The SceneController class is responsible for navigating between different
 * views in the
 * application.
 */
public class SceneController {

    /**
     * @param nextView
     */
    public void nextSceneNavigator(View nextView) {
        nextView.show();

    }

    /**
     * @param previousView
     */
    public void previousSceneNavigator(View previousView) {
        previousView.show();
    }

}
