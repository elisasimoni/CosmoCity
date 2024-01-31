package it.unibo.cosmocity.view;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.controller.view_controller.AssignSettlerController;

public class AssignSettler extends ViewPreLoadImpl {

    private static final String GAME_TITLE = "CosmoCity - Assign Settler";
    private static final String FONT_FAMILY = "Elephant";
    private static final String BACKGROUND_COLOR_WHITE = "-fx-background-color: #ffffff";
    private static final String BACKGROUND_COLOR_DARK_BLUE = "-fx-background-color: darkBlue";

    private static final String STRING_CONCAT = "-fx-font-size:";

    private static final String NEW_GAME_TEXT = "Assign optional settlers to the sector";
    private static final String START_COLONY_BTN_TEXT = "Start Colony";

    private static final String SECTOR_DROPDOWN_MENU_TEXT = "Select a sector";
    private static final int SECTOR_DROPDOWN_MENU_WIDTH = 200;
    private static final int SECTOR_DROPDOWN_MENU_HEIGHT = 50;
    
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


    AssignSettlerController assignSettlerController;
    List<String> settlers = new ArrayList<>();
    private Map<String, String> settlerAssigned = new HashMap<>();
    private ComboBox<String> sectorDropdownMenu;
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    SimulationController simulatorController;

    public AssignSettler(Stage stage, double width, double height, SimulationController simulatorController) {
        super(stage, width, height);
        this.simulatorController = simulatorController;
        this.settlers = this.simulatorController.getSettlers();
        assignSettlerController = new AssignSettlerController(settlers, this.simulatorController);
        this.scene = new Scene(new Pane(), width, height);
        this.scene.setRoot(this.createGUI());

    }

    public void refresh() {
        createGUI();
    }

    @Override
    public Pane createGUI() {

        stage.setTitle(GAME_TITLE);
        BorderPane root = new BorderPane();
        root.setStyle(BACKGROUND_COLOR_DARK_BLUE);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(SPACING_30);

        Text newGameText = new Text(NEW_GAME_TEXT);
        newGameText.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, NEW_GAME_TEXT_FONT_SIZE));
        newGameText.setTextAlignment(TextAlignment.CENTER);
        newGameText.setFill(Color.WHITE);
        newGameText.styleProperty().bind(Bindings.concat(STRING_CONCAT, stage.widthProperty().divide(DIVIDE_20)));
        vbox.getChildren().add(newGameText);
        ;
        for (String settlerName : translator.translateListToOptionalSettlerList(simulatorController.getSettlers())) {
            vbox.getChildren().add(createSettlerAssignBox(settlerName));
        }

        Button startColonyButton = createButton(START_COLONY_BTN_TEXT);
        startColonyButton.maxHeightProperty().bind(scene.heightProperty());
        startColonyButton.prefWidthProperty().bind(scene.widthProperty().divide(DIVIDE_HALF));
        vbox.getChildren().add(startColonyButton);
        vbox.maxHeightProperty().bind(scene.heightProperty());
        vbox.prefWidthProperty().bind(scene.widthProperty().divide(DIVIDE_HALF));
        

        startColonyButton.setOnAction(e -> {
            sendSimulation();

        });
        root.setCenter(vbox);

        return root;
    }

    private void sendSimulation() {
        this.stage.close();
        simulatorController.modifyOptionalSettler(settlerAssigned);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(BTN_WIDTH);
        button.setPrefHeight(BTN_HEIGHT);
        button.setStyle(BACKGROUND_COLOR_WHITE);
        button.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BTN_FONT_SIZE));
        return button;
    }

    private HBox createSettlerAssignBox(String settlerName) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(SPACING_10);
        Text settlerText = new Text(settlerName);
        settlerText.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, FONT_SIZE_20));
        settlerText.styleProperty().bind(Bindings.concat(STRING_CONCAT, stage.widthProperty().divide(DIVIDE_40)));
        settlerText.setFill(Color.WHITE);
        settlerText.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().add(settlerText);

        ObservableList<String> sectorsOption = FXCollections
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
