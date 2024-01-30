package it.unibo.cosmocity.view;


import it.unibo.cosmocity.controller.view_controller.DashBoardController;

import it.unibo.cosmocity.model.utility.ImageManagerImpl;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import it.unibo.cosmocity.model.Sector.Status;

import java.util.List;
import java.util.Map;

public class Dashboard extends ViewImpl {
    private GridPane gridPane;
    private Label foodVal;
    private Label timeLabel;
    private Label medicineVal;
    private Label weaponVal;
    private Label screwVal;
    Circle statusCircleFarm;
    Circle statusCircleHospital;
    Circle statusCircleManufactory;
    Circle statusCircleMilitaryBase;
    

    public Dashboard(Stage stage, double width, double height) {
        super(stage, width, height);
    }

    @Override
    public void refresh() {
        createGUI();
    }


    @Override
    public Pane createGUI() {


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

        createSector("img\\dashbord_image\\corn_field.jpeg", gridPane, 0, 0);
        statusCircleFarm = new Circle(20);
        statusCircleFarm.setFill(Color.GREEN);
        StackPane.setAlignment(statusCircleFarm, Pos.BOTTOM_CENTER);
        gridPane.getChildren().add(statusCircleFarm);

        createSector("img\\dashbord_image\\hospital.jpeg", gridPane, 1, 0);
        statusCircleHospital = new Circle(20);
        statusCircleHospital.setFill(Color.GREEN);
        StackPane.setAlignment(statusCircleHospital, Pos.BOTTOM_CENTER);
        gridPane.getChildren().add(statusCircleHospital);

        createSector("img\\dashbord_image\\manufactory.jpg", gridPane, 0, 1);
        statusCircleManufactory = new Circle(20);
        statusCircleManufactory.setFill(Color.GREEN);
        StackPane.setAlignment(statusCircleManufactory, Pos.BOTTOM_CENTER);
        gridPane.getChildren().add(statusCircleManufactory);

        createSector("img\\dashbord_image\\security.jpeg", gridPane, 1, 1);
        statusCircleMilitaryBase = new Circle(20);
        statusCircleMilitaryBase.setFill(Color.GREEN);
        StackPane.setAlignment(statusCircleMilitaryBase, Pos.BOTTOM_CENTER);
        gridPane.getChildren().add(statusCircleMilitaryBase);

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
        
    }

    public void updateCirle(List<Status> statuses){
        statusCircleFarm.setFill(statusColor(statuses.get(1)));

    }
    private Color statusColor(Status status){
        if(status == Status.GREEN){
            return Color.GREEN;
        }else if(status == Status.YELLOW){
            return Color.YELLOW;
        }else{
            return Color.RED;
        }
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

}
