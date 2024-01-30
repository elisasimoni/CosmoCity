package it.unibo.cosmocity.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.view_controller.AssignSettlerController;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.Doctor;
import it.unibo.cosmocity.model.settlers.Gunsmith;
import it.unibo.cosmocity.model.settlers.Military;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;

public class MoveResource {

    private AssignSettlerController assignSettlerController;

    public MoveResource() {
        List<BaseSettler> settlers = new ArrayList<>();
        settlers.add(new Military());
        settlers.add(new Doctor());
        settlers.add(new Gunsmith());
        settlers.add(new Gunsmith());
        assignSettlerController = new AssignSettlerController(settlers);
        doing();
    }

    private void doing() {
        Alert moveSettlerAlert = createSettlerAssignmentAlert(assignSettlerController);
        ButtonType startColonyButtonType = new ButtonType("Start Colony", ButtonBar.ButtonData.OK_DONE);
        moveSettlerAlert.getButtonTypes().add(startColonyButtonType);

        moveSettlerAlert.showAndWait();

        // Handle button click if needed
        if (moveSettlerAlert.getResult() == startColonyButtonType) {
            SimulationController simulatorController = new SimulationController();
            //simulatorController.startSimulation(List.of("Gunsmith", "Doctor"), Map.of("Medicine", 5, "Food", 5));
        }
    }

    private Alert createSettlerAssignmentAlert(AssignSettlerController assignSettlerController) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("CosmoCity - Assign Settler");
        alert.initModality(Modality.APPLICATION_MODAL);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: darkBlue;");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15); // Reduced spacing

        Text newGameText = new Text("Assign settlers to the sector");
        newGameText.setFont(Font.font("Elephant", 50)); // Reduced font size
        newGameText.setTextAlignment(TextAlignment.CENTER);
        newGameText.setFill(Color.WHITE);
        vbox.getChildren().add(newGameText);

        for (String settlerName : assignSettlerController.getSettlersNames()) {
            vbox.getChildren().add(createSettlerAssignBox(settlerName, assignSettlerController.getSettlerQuantity(settlerName)));
        }

        // Removed "Return to Dashboard" button

        root.setCenter(vbox);

        alert.getDialogPane().setContent(root);

        return alert;
    }

    private HBox createSettlerAssignBox(String settlerName, long settlerQta) {
        HBox hbox = new HBox();
        hbox.setSpacing(5); // Reduced spacing
        Text settlerText = new Text(settlerName);
        settlerText.setFont(Font.font("Elephant", 15)); // Reduced font size
        settlerText.setFill(Color.WHITE);
        settlerText.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().add(settlerText);

        ComboBox<String> sectorDropdownMenu = new ComboBox<>();
        sectorDropdownMenu.setPromptText("Sector");
        sectorDropdownMenu.setStyle("-fx-background-color: #ffffff");
        sectorDropdownMenu.setPrefWidth(100); // Reduced width

        ComboBox<String> qtaSettlerDropdownMenu = new ComboBox<>();
        for (int i = 0; i < settlerQta; i++) {
            qtaSettlerDropdownMenu.getItems().add(String.valueOf(i + 1));
        }
        qtaSettlerDropdownMenu.setPromptText("Quantity");
        qtaSettlerDropdownMenu.setStyle("-fx-background-color: #ffffff");
        qtaSettlerDropdownMenu.setPrefWidth(100); // Reduced width
        hbox.getChildren().add(sectorDropdownMenu);
        hbox.getChildren().add(qtaSettlerDropdownMenu);

        return hbox;
    }
}
