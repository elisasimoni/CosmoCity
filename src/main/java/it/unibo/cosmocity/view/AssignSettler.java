package it.unibo.cosmocity.view;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.view_controller.AssignSettlerController;
import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.Doctor;
import it.unibo.cosmocity.model.settlers.Gunsmith;
import it.unibo.cosmocity.model.settlers.Military;

public class AssignSettler extends ViewImpl {

    AssignSettlerController assignSettlerController;
    private final Screen screen = Screen.getPrimary();
    private final double screenWidth = screen.getBounds().getWidth();
    private final double screenHeight = screen.getBounds().getHeight();
    List<BaseSettler> settlers;

    public AssignSettler(Stage stage, double width, double height) {
        super(stage, width, height);

    }

    public void refresh() {
        createGUI();
    }

    @Override
    public Pane createGUI() {
        SimulationController simulatorController = new SimulationController();
        assignSettlerController = new AssignSettlerController(settlers);
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

        for (String settlerName : assignSettlerController.getSettlersNames()) {
            vbox.getChildren()
                    .add(createSettlerAssignBox(settlerName, assignSettlerController.getSettlerQuantity(settlerName)));
        }

        Button startColonyButton = createButton("Start Colony");
        startColonyButton.maxHeightProperty().bind(scene.heightProperty());
        startColonyButton.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        vbox.getChildren().add(startColonyButton);
        vbox.maxHeightProperty().bind(scene.heightProperty());
        vbox.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        SceneController sceneController = new SceneController();
        
        startColonyButton.setOnAction(e -> {

            String colonyName = "Pino";
            List<String> settlers = new ArrayList<>();
            
            Map<String, Integer> resources = Map.of("Population", 5, "Food", 10, "Medicine", 4,"Weapons",4, "Screw", 12);;
            DifficultiesType difficulty = DifficultiesType.EASY;
            System.out.println("Difficulty: " +difficulty);
            simulatorController.updateSimulation(colonyName, settlers, resources, difficulty );
            this.stage.close();

        });
        root.setCenter(vbox);

        return root;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-color: #ffffff");
        button.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        return button;
    }

    private HBox createSettlerAssignBox(String settlerName, long settlerQta) {
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

        ComboBox<String> sectorDropdownMenu = new ComboBox<>(sectorsOption);
        sectorDropdownMenu.setPromptText("Select a sector");
        sectorDropdownMenu.setStyle("-fx-background-color: #ffffff");
        sectorDropdownMenu.setPrefWidth(200);
        sectorDropdownMenu.setPrefHeight(50);

        ComboBox<String> qtaSettlerDropdownMenu = new ComboBox<>();
        for (int i = 0; i < settlerQta; i++) {
            qtaSettlerDropdownMenu.getItems().add(String.valueOf(i + 1));
        }

        qtaSettlerDropdownMenu.setPromptText("Number of settlers");
        qtaSettlerDropdownMenu.setStyle("-fx-background-color: #ffffff");
        qtaSettlerDropdownMenu.setPrefWidth(200);
        qtaSettlerDropdownMenu.setPrefHeight(50);
        hbox.getChildren().add(sectorDropdownMenu);
        hbox.getChildren().add(qtaSettlerDropdownMenu);

        return hbox;
    }

}
