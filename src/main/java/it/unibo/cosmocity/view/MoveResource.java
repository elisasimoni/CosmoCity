package it.unibo.cosmocity.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.controller.view_controller.AssignSettlerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;

public class MoveResource {

    private static final String SECTOR_DROPDOWN_MENU_TEXT = "Select a sector";
    private static final int SECTOR_DROPDOWN_MENU_WIDTH = 200;
    private static final int SECTOR_DROPDOWN_MENU_HEIGHT = 50;
    private static final String GAME_TITLE = "CosmoCity - Assign Settler";
    private static final String FONT_FAMILY = "Elephant";
    private static final String BACKGROUND_COLOR_WHITE = "-fx-background-color: #ffffff";
    private static final String BACKGROUND_COLOR_DARK_BLUE = "-fx-background-color: darkBlue";
    private static final int NEW_GAME_TEXT_FONT_SIZE = 100;
    private static final int FONT_SIZE_20 = 20;
    private static final int BTN_FONT_SIZE = 15;
    private static final int BTN_WIDTH = 300;
    private static final int BTN_HEIGHT = 50;
    private static final int DIVIDE_20 = 20;
    private static final int DIVIDE_40 = 40;
    private static final double DIVIDE_HALF = 2.5;

    private static final int SPACING_10 = 10;
    private static final int SPACING_30 = 30;

    private static final String STRING_CONCAT = "-fx-font-size:";
    private final AssignSettlerController assignSettlerController;
    List<String> settlers = new ArrayList<>();

    private ComboBox<String> sectorDropdownMenu;
    private final TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    SimulationController simulatorController;
    private final Map<String, String> settlerAssigned = new HashMap<>();

    public MoveResource(final SimulationController simulatorController) {
        this.simulatorController = simulatorController;
        this.settlers = this.simulatorController.getSettlers();
        assignSettlerController = new AssignSettlerController(settlers, this.simulatorController);
        doing();
    }

    private void doing() {
        final Alert moveSettlerAlert = createSettlerAssignmentAlert(assignSettlerController);
        final ButtonType startColonyButtonType = new ButtonType("Return to dashboard", ButtonBar.ButtonData.OK_DONE);
        moveSettlerAlert.getButtonTypes().add(startColonyButtonType);

        moveSettlerAlert.showAndWait();

        if (moveSettlerAlert.getResult() == startColonyButtonType) {
            simulatorController.modifyOptionalSettlerDuringSim(settlerAssigned);
        }
    }

    private Alert createSettlerAssignmentAlert(final AssignSettlerController assignSettlerController) {
        final Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("CosmoCity - Assign Settler");
        alert.initModality(Modality.APPLICATION_MODAL);

        final BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: darkBlue;");

        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15); // Reduced spacing

        final Text newGameText = new Text("Assign settlers to the sector");
        newGameText.setFont(Font.font("Elephant", 50)); // Reduced font size
        newGameText.setTextAlignment(TextAlignment.CENTER);
        newGameText.setFill(Color.WHITE);
        vbox.getChildren().add(newGameText);

        for (final String settlerName : translator.translateListToOptionalSettlerList(simulatorController.getSettlers())) {
            vbox.getChildren().add(createSettlerAssignBox(settlerName));
        }

        root.setCenter(vbox);

        alert.getDialogPane().setContent(root);

        return alert;
    }

    private HBox createSettlerAssignBox(final String settlerName) {
        final HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(SPACING_10);
        final Text settlerText = new Text(settlerName);
        settlerText.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, FONT_SIZE_20));
        settlerText.setTextAlignment(TextAlignment.CENTER);
        settlerText.setFill(Color.WHITE);
        hbox.getChildren().add(settlerText);

        final ObservableList<String> sectorsOption = FXCollections
                .observableArrayList(assignSettlerController.getSectorOptions());

        sectorDropdownMenu = new ComboBox<>(sectorsOption);
        sectorDropdownMenu.setPromptText(SECTOR_DROPDOWN_MENU_TEXT);
        sectorDropdownMenu.setStyle(BACKGROUND_COLOR_WHITE);
        sectorDropdownMenu.setPrefWidth(SECTOR_DROPDOWN_MENU_WIDTH);
        sectorDropdownMenu.setPrefHeight(SECTOR_DROPDOWN_MENU_HEIGHT);

        sectorDropdownMenu.setOnAction(e -> {
            settlerAssigned.put(settlerName, sectorDropdownMenu.getValue());
        });

        hbox.getChildren().add(sectorDropdownMenu);

        return hbox;
    }
}
