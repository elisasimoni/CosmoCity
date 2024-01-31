package it.unibo.cosmocity.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.util.HashMap;
import java.util.Map;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.view_controller.CreateColonyController;
import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.model.utility.ImageManagerImpl;

public class CreateColonyPage extends ViewImpl implements CreateColonyPageView {

    private static final int PADDING_PANE_TOP = 20;
    private static final int PADDING_PANE_BOTTOM = 20;
    private static final int PADDING_PANE_LEFT = 20;
    private static final int PADDING_PANE_RIGHT = 20;

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;

    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 100;

    private static final int FONT_SIZE_20 = 20;
    private static final int FONT_SIZE_15 = 15;

    private static final int SPACING_SMALL = 10;
    private static final int SPACING_MEDIUM = 30;

    private static final int COLONY_NAME_FILE_WIDTH = 200;
    private static final int COLONY_NAME_FILED_TOP = 20;
    private static final int COLONY_NAME_FILED_BOTTOM = 20;
    private static final int COLONY_NAME_FILED_LEFT = 20;
    private static final int COLONY_NAME_FILED_RIGHT = 20;

    private static final int VBOX_TOP = 10;
    private static final int VBOX_BOTTOM = 30;
    private static final int VBOX_LEFT = 30;
    private static final int VBOX_RIGHT = 0;

    private static final String FONT_TEXT = "Elephant";
    private static final String BACKGROUND_COLOR_WHITE = "-fx-background-color: #ffffff";
    private static final String BACKGROUND_COLOR_DARK_BLUE = "-fx-background-color: darkBlue";
    private static final String BACKGROUND_COLOR_RED = "-fx-background-color: #FF0000";
    private static final String BACKGROUND_COLOR_GREEN = "-fx-background-color: #008000";

    private static final String WARNING_TEXT_LIMIT_SETTLER_NUMBER = "You can choose max 10 settlers";
    private static final String WARNING_TEXT_CHECK_ALL_FIELDS = "Please fill all fields";

    private static final String MANDATORY_SETTLER_LABEL = "Mandatory Settlers";
    private static final String COLONY_NAME_LABEL = "Colony name";
    private static final String DIFFICULY_LABEL = "Colony name";
    private static final String NEXT_BUTTON_TEXT = "Next";
    private static final String PLUS_LABEL = "+";
    private static final String MINUS_LABEL = "-";
    private static final String BASE_LABEL = "0";

    private static final int MAXIMUM_SETTLER_NUMBER = 10;

    private final SceneController sceneController = new SceneController();
    private final CreateColonyController createColonyController;
    private SimulationController simulatorController;
    private Map<String, Integer> selectedSettlers = new HashMap<>();
    private Button nextButton;
    private Text colonyNameText;
    private ComboBox<String> difficultyComboBox;
    private Text warningText;

    public CreateColonyPage(Stage stage, double width, double height, SimulationController simulatorController) {
        super(stage, width, height);
        this.simulatorController = simulatorController;
        this.createColonyController = new CreateColonyController(this, this.simulatorController);
    }

    @Override
    public Pane createGUI() {
        BorderPane root = new BorderPane();
        root.setStyle(BACKGROUND_COLOR_DARK_BLUE);
        root.setPadding(new Insets(PADDING_PANE_TOP, PADDING_PANE_RIGHT, PADDING_PANE_BOTTOM, PADDING_PANE_LEFT));

        // Creazione della HBox per colonyNameText e colonyNameField
        HBox colonyNameBox = new HBox();
        colonyNameBox.setAlignment(Pos.CENTER);
        colonyNameBox.setSpacing(SPACING_SMALL);

        colonyNameText = new Text(COLONY_NAME_LABEL);
        colonyNameText.setFont(Font.font(FONT_TEXT, FontWeight.NORMAL, FONT_SIZE_20));
        colonyNameText.setFill(Color.WHITE);

        TextField colonyNameField = new TextField();
        colonyNameField.setPrefWidth(COLONY_NAME_FILE_WIDTH);
        colonyNameField.setMaxWidth(COLONY_NAME_FILE_WIDTH);
        colonyNameField.setPadding(new Insets(COLONY_NAME_FILED_TOP, COLONY_NAME_FILED_RIGHT, COLONY_NAME_FILED_BOTTOM,
                COLONY_NAME_FILED_LEFT));
        colonyNameField.prefWidthProperty().bind(colonyNameBox.widthProperty());

        colonyNameBox.getChildren().addAll(colonyNameText, colonyNameField);

        HBox difficultyBox = new HBox();
        difficultyBox.setAlignment(Pos.CENTER);
        difficultyBox.setSpacing(SPACING_SMALL);

        Text difficultyText = new Text(DIFFICULY_LABEL);
        difficultyText.setFont(Font.font(FONT_TEXT, FontWeight.NORMAL, FONT_SIZE_20));
        difficultyText.setFill(Color.WHITE);

        difficultyComboBox = difficultyChooser();

        difficultyBox.getChildren().addAll(difficultyText, difficultyComboBox);

        HBox hboxContainer = new HBox();
        hboxContainer.getChildren().addAll(colonyNameBox, difficultyBox);
        hboxContainer.setSpacing(SPACING_MEDIUM);
        hboxContainer.setAlignment(Pos.CENTER);

        root.setTop(hboxContainer);

        // Grid Settler
        HBox hboxSettlers = new HBox();
        hboxSettlers.setAlignment(Pos.CENTER);
        hboxSettlers.setSpacing(SPACING_SMALL);

        GridPane settlerGrid = new GridPane();
        settlerGrid.setAlignment(Pos.CENTER);
        settlerGrid.setHgap(SPACING_SMALL);
        settlerGrid.setVgap(SPACING_SMALL);

        // HBox for Mandatory Settler
        HBox hboxMandatorySettler = createHBoxSettler();
        hboxMandatorySettler.setAlignment(Pos.CENTER);
        hboxMandatorySettler.setSpacing(SPACING_SMALL);

        Text mandatorySetText = new Text(MANDATORY_SETTLER_LABEL);
        mandatorySetText.setFont(Font.font(FONT_TEXT, FontWeight.BOLD, FONT_SIZE_15));
        mandatorySetText.setFill(Color.WHITE);

        hboxMandatorySettler.getChildren().addAll(mandatorySetText,
                createImageControl("settler_icon/Farmer_Icon.png", "Farmer"),
                createImageControl("settler_icon/Gunsmith_Icon.png", "Blacksmith"),
                createImageControl("settler_icon/Doctor_Icon.png", "Doctor"),
                createImageControl("settler_icon/Military_Icon.png", "Military"),
                createImageControl("settler_icon/Blacksmith_Icon.png", "Gunsmith"));

        // HBox for Optional Settler
        HBox hboxOptionalSettler = createHBoxSettler();
        hboxOptionalSettler.setAlignment(Pos.CENTER);
        hboxOptionalSettler.setSpacing(SPACING_SMALL);

        Text optionalSetText = new Text("Optional Settlers");
        optionalSetText.setFont(Font.font(FONT_TEXT, FontWeight.BOLD, FONT_SIZE_15));
        optionalSetText.setFill(Color.WHITE);

        hboxOptionalSettler.getChildren().addAll(optionalSetText,
                createImageControl("settler_icon/Cook_Icon.png", "Cook"),
                createImageControl("settler_icon/Technician_Icon.png", "Technician"),
                createImageControl("settler_icon/Chemist_Icon.png", "Chemist"));

        settlerGrid.add(hboxMandatorySettler, 0, 0);
        settlerGrid.add(hboxOptionalSettler, 0, 1);

        root.setCenter(settlerGrid);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(SPACING_MEDIUM);
        vbox.setPadding(new Insets(VBOX_TOP, VBOX_RIGHT,VBOX_BOTTOM, VBOX_LEFT));

        nextButton = createButton(NEXT_BUTTON_TEXT);
        nextButton.maxHeightProperty().bind(scene.heightProperty());
        nextButton.prefWidthProperty().bind(scene.widthProperty().divide(2.5));

        warningText = new Text("");
        warningText.setFont(Font.font(FONT_TEXT, FontWeight.NORMAL, FONT_SIZE_20));

        vbox.getChildren().addAll(warningText, nextButton);

        nextButton.setOnAction(e -> {
            if (checkForm()) {
                createColonyController.createColony(colonyNameField.getText(), this.selectedSettlers,
                        difficultyComboBox.getValue());
                sceneController.nextSceneNavigator(new AssignSettler(stage, 900, 700, this.simulatorController));
            }
        });

        vbox.maxHeightProperty().bind(scene.heightProperty());
        vbox.prefWidthProperty().bind(scene.widthProperty().divide(2.5));

        root.setBottom(vbox);

        return root;
    }

    private HBox createHBoxSettler(String... settlerData) {
        HBox hboxSettler = new HBox();
        hboxSettler.setAlignment(Pos.CENTER);
        hboxSettler.setSpacing(SPACING_SMALL);

        for (int i = 0; i < settlerData.length; i += 2) {
            hboxSettler.getChildren().add(createImageControl(settlerData[i], settlerData[i + 1]));
        }

        return hboxSettler;
    }

    /**
     * @param text
     * @return a button with text
     */
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setStyle(BACKGROUND_COLOR_WHITE);
        button.setFont(Font.font(FONT_TEXT, FontWeight.BOLD, FONT_SIZE_15));
        return button;
    }

    private boolean checkForm() {
        if (colonyNameText.getText().isEmpty() || selectedSettlers.isEmpty()
                || difficultyComboBox.getValue().isEmpty() || selectedSettlers.size() == MAXIMUM_SETTLER_NUMBER) {
            if (selectedSettlers.size() >= MAXIMUM_SETTLER_NUMBER) {
                displayWarning(WARNING_TEXT_LIMIT_SETTLER_NUMBER);
                return false;
            }
            displayWarning(WARNING_TEXT_CHECK_ALL_FIELDS);

            return false;
        } else {
            displayWarning("");
            return true;
        }

    }

    private VBox createImageControl(String imageURL, String nameSettler) {
        VBox vbox = new VBox();
        vbox.setSpacing(SPACING_SMALL);
        ImageManagerImpl imageManager = new ImageManagerImpl();
        Image settlerImage = imageManager.loadImage(imageURL);
        ImageView imageView = new ImageView(settlerImage);
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        Label nameSettlerText = new Label(nameSettler);
        nameSettlerText.setTextFill(Color.WHITE);
        nameSettlerText.setFont(Font.font(FONT_TEXT, FontWeight.BOLD, FONT_SIZE_15));
        vbox.getChildren().add(nameSettlerText);
        vbox.getChildren().add(imageView);

        Button plusButton = new Button(PLUS_LABEL);
        plusButton.setStyle(BACKGROUND_COLOR_GREEN);
        plusButton.setTextFill(Color.WHITE);
        Button minusButton = new Button(MINUS_LABEL);
        minusButton.setStyle(BACKGROUND_COLOR_RED);
        minusButton.setTextFill(Color.WHITE);
        Label counterLabel = new Label(BASE_LABEL);
        counterLabel.setTextFill(Color.WHITE);

        plusButton.setOnAction(event -> {
            int count = Integer.parseInt(counterLabel.getText());
            counterLabel.setText(String.valueOf(count + 1));
            selectedSettlers.put(nameSettler, count + 1);
        });

        minusButton.setOnAction(event -> {
            int count = Integer.parseInt(counterLabel.getText());
            if (count > 0) {
                counterLabel.setText(String.valueOf(count - 1));
                selectedSettlers.put(nameSettler, count - 1);
            }
        });

        HBox controls = new HBox(minusButton, counterLabel, plusButton);
        controls.setAlignment(Pos.CENTER);
        controls.setSpacing(SPACING_SMALL);

        vbox.getChildren().add(controls);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    private ComboBox<String> difficultyChooser() {
        ObservableList<String> difficultyOptions = FXCollections.observableArrayList(
                "EASY",
                "MEDIUM",
                "HARD");

        ComboBox<String> comboBox = new ComboBox<>(difficultyOptions);
        comboBox.setValue("MEDIUM");
        comboBox.setStyle(BACKGROUND_COLOR_WHITE);
        comboBox.setPrefWidth(200);
        comboBox.setPrefHeight(30);

        return comboBox;
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refresh'");
    }

    @Override
    public void displayWarning(String warningMessage) {
        warningText.setText(warningMessage);
    }

}
