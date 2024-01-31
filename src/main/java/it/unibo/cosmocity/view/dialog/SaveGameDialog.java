package it.unibo.cosmocity.view.dialog;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SaveGameDialog implements PopUpDialog {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    private boolean isSaved = false;

    @Override
    public void show() {
        createPopUpGUI();
        final Optional<ButtonType> btnPressed = alert.showAndWait();
        if (btnPressed.get() == alert.getButtonTypes().get(0)) {
            setSaved(true);
        } else {
            setSaved(false);

        }
    }

    
    /** 
     * @return Alert
     */
    @Override
    public Alert createPopUpGUI() {
        alert.setContentText("Do you want to save and exit?");
        final ButtonType fixDamageBtn = new ButtonType("Yes");
        final ButtonType getDamageBtn = new ButtonType("No");
        alert.getButtonTypes().setAll(fixDamageBtn, getDamageBtn);

        return alert;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(final boolean isSaved) {
        this.isSaved = isSaved;
    }

}
