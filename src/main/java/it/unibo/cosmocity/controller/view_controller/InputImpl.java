package it.unibo.cosmocity.controller.view_controller;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class InputImpl  implements Input{

    @Override
    public boolean isButtonClicked(Button button) {
        return button.isPressed();
    }

   
}
