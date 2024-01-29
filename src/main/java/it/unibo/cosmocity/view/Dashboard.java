package it.unibo.cosmocity.view;


import it.unibo.cosmocity.controller.view_controller.DashBoardController;

import it.unibo.cosmocity.controller.view_controller.SceneController;

import it.unibo.cosmocity.model.utility.ImageManagerImpl;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Map;

public class Dashboard extends ViewImpl {
    private GridPane gridPane;
    private Label foodVal;
    private Label timeLabel;
    private Label medicineVal;
    private Label weaponVal;
    private Label screwVal;

    public Dashboard(Stage stage, double width, double height) {
        super(stage, width, height);

    }

    @Override
    public void refresh() {
        createGUI();
    }


    @Override
    public Pane createGUI() {

        SceneController sceneController = new SceneController();

        stage.setTitle("CosmoCity - Colony Dashboard");
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: darkBlue;");

        Button newGameBtn = createButton("Pause");
        Button loadGameBtn = createButton("Save");
        Button settingResources = createButton("Setting Resources");
        Button exitButton = createButton("Exit");

        VBox menuBtnBox = new VBox(20);
        menuBtnBox.getChildren().addAll(newGameBtn, loadGameBtn, settingResources, exitButton);
        menuBtnBox.setPadding(new Insets(0, 50, 0, 0));

        VBox leftBox = new VBox(20);
        leftBox.setAlignment(Pos.CENTER);
        leftBox.getChildren().add(menuBtnBox);

        root.setLeft(leftBox);

        StackPane titlePane = new StackPane();
        Text titleColony = new Text("Colony Name");
        titleColony.setFont(Font.font("Elephant", FontWeight.BOLD, 30));
        titleColony.setFill(Color.WHITE);
        titlePane.getChildren().add(titleColony);

        root.setTop(titlePane);

        String foodText = "Food: ";
        String mediceneText = "Medicine: ";
        String weaponsText = "Weapons: ";
        String screwText = "Screw: ";

        Text timerLabel = new Text("Tempo:");
        timerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        timerLabel.setFill(Color.WHITE);
        this.timeLabel = new Label("Valore Dinamico");
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        timeLabel.setTextFill(Color.YELLOW);

        Text foodLabel = new Text(foodText);
        foodLabel.setFont(Font.font("Arial", 20));
        foodLabel.setFill(Color.WHITE);

        this.foodVal = new Label("Valore Dinamico");
        foodVal.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        foodVal.setTextFill(Color.YELLOW);

        Text medicineLabel = new Text(mediceneText);
        medicineLabel.setFont(Font.font("Arial", 20));
        medicineLabel.setFill(Color.WHITE);

        this.medicineVal = new Label("Valore Dinamico");
        medicineVal.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        medicineVal.setTextFill(Color.YELLOW);

        Text weaponLabel = new Text(weaponsText);
        weaponLabel.setFont(Font.font("Arial", 20));
        weaponLabel.setFill(Color.WHITE);

        this.weaponVal = new Label("Valore Dinamico");
        weaponVal.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        weaponVal.setTextFill(Color.YELLOW);

        Text screwLabel = new Text(screwText);
        screwLabel.setFont(Font.font("Arial", 20));
        screwLabel.setFill(Color.WHITE);

        this.screwVal = new Label("Valore Dinamico");
        screwVal.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        screwVal.setTextFill(Color.YELLOW);

        HBox timerBox = new HBox(10);
        timerBox.getChildren().addAll(timerLabel, timeLabel);

        HBox foodInfoBox = new HBox(10);
        foodInfoBox.getChildren().addAll(foodLabel, foodVal);

        HBox medicineInfoBox = new HBox(10);
        medicineInfoBox.getChildren().addAll(medicineLabel, medicineVal);

        HBox weaponInfoBox = new HBox(10);
        weaponInfoBox.getChildren().addAll(weaponLabel, weaponVal);

        HBox screwInfoBox = new HBox(10);
        screwInfoBox.getChildren().addAll(screwLabel, screwVal);

        VBox infoVBox = new VBox(10);
        infoVBox.getChildren().addAll(foodInfoBox, medicineInfoBox, weaponInfoBox, screwInfoBox, timerBox);
        infoVBox.setAlignment(Pos.CENTER_RIGHT);

        StackPane.setAlignment(infoVBox, Pos.CENTER_RIGHT);

        StackPane infoStackPane = new StackPane();
        infoStackPane.getChildren().add(infoVBox);

        root.setRight(infoStackPane);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Aggiungi i settori alla griglia
        createSector("img\\dashbord_image\\corn_field.jpeg", gridPane, 0, 0);

        createSector("img\\dashbord_image\\hospital.jpeg", gridPane, 1, 0);
        createSector("img\\dashbord_image\\manufactory.jpg", gridPane, 0, 1);
        createSector("img\\dashbord_image\\security.jpeg", gridPane, 1, 1);

        root.setCenter(gridPane);

        return root;
    }

    public void updateTimeLabel(long time) {
        System.out.println("Time View: " + time);
        Platform.runLater(() -> {
            timeLabel.setText(String.valueOf(time));
        });
    }

    public void updateResourceLabel(Map<String, Integer> resources) {

        Platform.runLater(() -> {
            foodVal.setText(String.valueOf(resources.get("Food")));
            medicineVal.setText(String.valueOf(resources.get("Medicine")));
            weaponVal.setText(String.valueOf(resources.get("Weapons")));
            screwVal.setText(String.valueOf(resources.get("Screw")));
        });
    }

    private void createSector(String backgroundImagePath, GridPane gridPane, int colIndex, int rowIndex) {
        ImageManagerImpl imageManager = new ImageManagerImpl();

        Image backgroundImage = imageManager.loadImage(backgroundImagePath);

        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(300);
        backgroundImageView.setFitHeight(300);

        StackPane sectorPane = new StackPane();
        sectorPane.getChildren().add(backgroundImageView);

        Circle statusCircle = new Circle(20);

        statusCircle.setFill(Color.GREEN);
        StackPane.setAlignment(statusCircle, Pos.BOTTOM_CENTER);

        sectorPane.getChildren().add(statusCircle);

        gridPane.add(sectorPane, colIndex, rowIndex);
    }

    /**
     * @param text
     * @return a button with text
     */
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(150);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-color: #ffffff");
        button.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        return button;
    }

    private HBox createResourceLine(String resourceName, long resourceQta) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        Text resourceText = new Text(resourceName);
        resourceText.setFont(Font.font("Elephant", FontWeight.NORMAL, 20));
        resourceText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(40)));
        resourceText.setFill(Color.WHITE);
        resourceText.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().add(resourceText);
        Text resourceQtaText = new Text(String.valueOf(resourceQta));
        resourceQtaText.setFont(Font.font("Elephant", FontWeight.NORMAL, 20));
        resourceQtaText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(40)));
        resourceQtaText.setFill(Color.WHITE);
        resourceQtaText.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().add(resourceQtaText);
        return hbox;
    }


}
