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
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.controller.view_controller.AssignSettlerController;

public class AssignSettler extends ViewPreLoadImpl {

    AssignSettlerController assignSettlerController;
    private final Screen screen = Screen.getPrimary();
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

        stage.setTitle("CosmoCity - Assign Settler");
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: darkBlue;");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        Text newGameText = new Text("Assign optional settlers to the sector");
        newGameText.setFont(Font.font("Elephant", FontWeight.BOLD, 100));
        newGameText.setTextAlignment(TextAlignment.CENTER);
        newGameText.setFill(Color.WHITE);
        newGameText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(20)));
        vbox.getChildren().add(newGameText);
        ;
        for (String settlerName : translator.translateListToOptionalSettlerList(simulatorController.getSettlers())) {
            vbox.getChildren().add(createSettlerAssignBox(settlerName));
        }

        Button startColonyButton = createButton("Start Colony");
        startColonyButton.maxHeightProperty().bind(scene.heightProperty());
        startColonyButton.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        vbox.getChildren().add(startColonyButton);
        vbox.maxHeightProperty().bind(scene.heightProperty());
        vbox.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        

        startColonyButton.setOnAction(e -> {
            sendSimulation();
        });
        root.setCenter(vbox);

        return root;
    }

    private void sendSimulation() {
        this.assignSettlerController.sendSimulation(this.settlerAssigned);

        this.stage.close();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-color: #ffffff");
        button.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        return button;
    }

    private HBox createSettlerAssignBox(String settlerName) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        Text settlerText = new Text(settlerName);
        settlerText.setFont(Font.font("Elephant", FontWeight.NORMAL, 20));
        settlerText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(40)));
        settlerText.setFill(Color.WHITE);
        settlerText.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().add(settlerText);

        ObservableList<String> sectorsOption = FXCollections
                .observableArrayList(assignSettlerController.getSectorOptions());

        sectorDropdownMenu = new ComboBox<>(sectorsOption);
        sectorDropdownMenu.setPromptText("Select a sector");
        sectorDropdownMenu.setStyle("-fx-background-color: #ffffff");
        sectorDropdownMenu.setPrefWidth(200);
        sectorDropdownMenu.setPrefHeight(50);

        sectorDropdownMenu.setOnAction(e -> {
            settlerAssigned.put(settlerName, sectorDropdownMenu.getValue());
        });

        hbox.getChildren().add(sectorDropdownMenu);

        return hbox;
    }

}
