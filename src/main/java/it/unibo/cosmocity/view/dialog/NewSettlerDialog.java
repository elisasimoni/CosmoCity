package it.unibo.cosmocity.view.dialog;

import it.unibo.cosmocity.model.utility.AudioManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class NewSettlerDialog implements PopUpDialog {


    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    AudioManager audioManager = new AudioManager();

    public NewSettlerDialog(String name) {
        alert.setHeaderText("You have unlocked: " + name.toUpperCase());
    }

    @Override
    public void show() {
        createPopUpGUI();
        alert.showAndWait();
    }

    public Alert createPopUpGUI() {
        audioManager.play("audio/good_event_sound.mp3");
        alert.setTitle("New settler unlocked!");
        alert.setContentText("You have unlocked a new settler!");
        ChoiceBox<String> sectors = new ChoiceBox<>();
        sectors.getItems().addAll("Production", "Hospital", "Security", "Manufacturing");
        GridPane grid = new GridPane();
        grid.add(new Label("Choose a sector:"), 0, 0);
        grid.add(sectors, 1, 0);
        alert.getDialogPane().setContent(grid);
        return alert;
    }

}
