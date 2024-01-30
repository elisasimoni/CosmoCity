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
    private Map<String,String> settlerAssigned;
    private ComboBox<String> sectorDropdownMenu;
    SimulationController simulatorController = new SimulationController();

    public AssignSettler(Stage stage, double width, double height) {
        super(stage, width, height);
        

    }

    public void refresh() {
        createGUI();
    }

    @Override
    public Pane createGUI() {
        System.out.println(simulatorController.getSettlers());
        List<String> settlers = simulatorController.getSettlers();
        System.out.println(settlers);
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
                    .add(createSettlerAssignBox(settlerName));
        }

        Button startColonyButton = createButton("Start Colony");
        startColonyButton.maxHeightProperty().bind(scene.heightProperty());
        startColonyButton.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        vbox.getChildren().add(startColonyButton);
        vbox.maxHeightProperty().bind(scene.heightProperty());
        vbox.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        SceneController sceneController = new SceneController();
        
        startColonyButton.setOnAction(e -> {

            
            DifficultiesType difficulty = DifficultiesType.EASY;
            System.out.println("Difficulty: " +difficulty);
            simulatorController.modifyOptionalSettler(settlerAssigned);
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
