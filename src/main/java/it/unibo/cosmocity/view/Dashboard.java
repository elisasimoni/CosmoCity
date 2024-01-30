package it.unibo.cosmocity.view;

import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.model.utility.ImageManagerImpl;
import it.unibo.cosmocity.view.dialog.NewEventDialog;
import it.unibo.cosmocity.view.dialog.PauseDialog;
import it.unibo.cosmocity.view.dialog.SaveGameDialog;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import it.unibo.cosmocity.model.Sector.Status;
import it.unibo.cosmocity.model.event.RandomEvent;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

public class Dashboard extends ViewImpl implements DashboardView {

    private static final String GAME_TITLE = "CosmoCity - Colony Dashboard";
    private static final String FONT_FAMILY = "Elephant";
    private static final String BACKGROUND_COLOR_WHITE = "-fx-background-color: #ffffff";
    private static final String BACKGROUND_COLOR_DARK_BLUE = "-fx-background-color: darkBlue";

    private static final int PANE_GAP_10 = 10;
    private static final int SPACING_10 = 10;
    private static final int SPACING_20 = 20;

    private static final int FONT_SIZE_LABEL = 20;
    private static final int FONT_SIZE_30 = 30;

    private static final int BUTTON_FONT_SIZE = 15;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;

    private static final int INFO_VBOX_PADDING_TOP = 0;
    private static final int INFO_VBOX_PADDING_BOTTOM = 0;
    private static final int INFO_VBOX_PADDING_LEFT = 50;
    private static final int INFO_VBOX_PADDING_RIGHT = 50;

    private static final int MENU_BTN_BOX_PADDING_TOP = 50;
    private static final int MENU_BTN_BOX_PADDING_BOTTOM = 0;
    private static final int MENU_BTN_BOX_PADDING_LEFT = 50;
    private static final int MENU_BTN_BOX_PADDING_RIGHT = 50;

    private static final int SECTOR_WIDTH = 300;
    private static final int SECTOR_HEIGHT = 300;

    private static final int BACKGROUND_SECTOR_TITLE_WIDTH = 300;
    private static final int BACKGROUND_SECTOR_TITLE_HEIGHT = 30;

    private static final int CIRCLE_STATUS_RADIUS = 20;

    private static enum TextResources {
        TIME,
        POPULATION,
        FOOD,
        MEDICINE,
        WEAPONS,
        SCREW
    }

    private static enum TextButton {
        PAUSE,
        SAVE,
        RESOURCES,
        EXIT
    }

    private GridPane gridPane;
    private Label foodVal;
    private Label timeLabel;
    private Label medicineVal;
    private Label weaponVal;
    private Label screwVal;
    private Text nameColony;
    private Label population;
    Map<String, String> settlerSectorMap;
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();
    Circle statusCircleFarm;
    Circle statusCircleHospital;
    Circle statusCircleManufactory;
    Circle statusCircleMilitaryBase;

    public Dashboard(Stage stage, double width, double height) {
        super(stage, width, height);
        this.simulationController = simulationController;
    }

    @Override
    public void refresh() {
        createGUI();
    }

    @Override
    public Pane createGUI() {

        stage.setTitle(GAME_TITLE);
        final BorderPane root = new BorderPane();
        root.setStyle(BACKGROUND_COLOR_DARK_BLUE);

        final Button pauseButton = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.PAUSE.toString().toLowerCase())));
        pauseButton.setOnAction(e -> {
            new PauseDialog().show();
        });

        final Button saveButton = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.SAVE.toString().toLowerCase())));
        saveButton.setOnAction(e -> {
            SaveGameDialog saveGameDialog = new SaveGameDialog();
            saveGameDialog.show();
            if(saveGameDialog.isSaved()) {
                simulationController.saveSimulation();
            }else{
                sceneController.nextSceneNavigator(this);
            }
        });

        final Button Resources = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.RESOURCES.toString().toLowerCase())));
        Resources.setOnAction(e -> {
            new MoveResource();
        });

        final Button exitButton = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.EXIT.toString().toLowerCase())));

        final VBox menuBtnBox = new VBox(SPACING_20);
        menuBtnBox.getChildren().addAll(pauseButton, saveButton, Resources, exitButton);
        menuBtnBox.setPadding(new Insets(MENU_BTN_BOX_PADDING_TOP, MENU_BTN_BOX_PADDING_RIGHT,
                MENU_BTN_BOX_PADDING_BOTTOM, MENU_BTN_BOX_PADDING_LEFT));

        final VBox leftBox = new VBox(SPACING_20);
        leftBox.setAlignment(Pos.CENTER);
        leftBox.getChildren().add(menuBtnBox);

        root.setLeft(leftBox);

        final StackPane titlePane = new StackPane();
        nameColony = new Text("");
        nameColony.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_30));
        nameColony.setFill(Color.WHITE);
        titlePane.getChildren().add(nameColony);

        root.setTop(titlePane);

        // Timer
        final Text timerLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.TIME.toString().toLowerCase())));
        timerLabel.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        timerLabel.setFill(Color.WHITE);

        this.timeLabel = new Label("");
        timeLabel.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        timeLabel.setTextFill(Color.YELLOW);

        // Population
        final Text populationLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.POPULATION.toString().toLowerCase())));
        populationLabel.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        populationLabel.setFill(Color.WHITE);

        this.population = new Label("");
        population.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        population.setTextFill(Color.YELLOW);

        // Food
        final Text foodLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.FOOD.toString().toLowerCase())));
        foodLabel.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        foodLabel.setFill(Color.WHITE);

        this.foodVal = new Label();
        foodVal.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        foodVal.setTextFill(Color.YELLOW);

        // Medicine
        final Text medicineLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.MEDICINE.toString().toLowerCase())));
        medicineLabel.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        medicineLabel.setFill(Color.WHITE);

        this.medicineVal = new Label();
        medicineVal.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        medicineVal.setTextFill(Color.YELLOW);

        // Weapon
        final Text weaponLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.WEAPONS.toString().toLowerCase())));
        weaponLabel.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        weaponLabel.setFill(Color.WHITE);

        this.weaponVal = new Label();
        weaponVal.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        weaponVal.setTextFill(Color.YELLOW);

        // Screw
        final Text screwLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.SCREW.toString().toLowerCase())));
        screwLabel.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        screwLabel.setFill(Color.WHITE);

        this.screwVal = new Label();
        screwVal.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        screwVal.setTextFill(Color.YELLOW);

        final HBox popolationBox = new HBox(SPACING_10);
        popolationBox.getChildren().addAll(populationLabel, population);
        popolationBox.setAlignment(Pos.CENTER_RIGHT);

        final HBox timerBox = new HBox(SPACING_10);
        timerBox.getChildren().addAll(timerLabel, timeLabel);
        timerBox.setAlignment(Pos.CENTER_RIGHT);

        final HBox foodInfoBox = new HBox(SPACING_10);
        foodInfoBox.getChildren().addAll(foodLabel, foodVal);
        foodInfoBox.setAlignment(Pos.CENTER_RIGHT);

        final HBox medicineInfoBox = new HBox(SPACING_10);
        medicineInfoBox.getChildren().addAll(medicineLabel, medicineVal);
        medicineInfoBox.setAlignment(Pos.CENTER_RIGHT);

        final HBox weaponInfoBox = new HBox(SPACING_10);
        weaponInfoBox.getChildren().addAll(weaponLabel, weaponVal);
        weaponInfoBox.setAlignment(Pos.CENTER_RIGHT);

        final HBox screwInfoBox = new HBox(SPACING_10);
        screwInfoBox.getChildren().addAll(screwLabel, screwVal);
        screwInfoBox.setAlignment(Pos.CENTER_RIGHT);

        final VBox infoVBox = new VBox(SPACING_10);
        infoVBox.getChildren().addAll(timerBox, popolationBox, foodInfoBox, medicineInfoBox, weaponInfoBox,
                screwInfoBox);
        infoVBox.setAlignment(Pos.CENTER_RIGHT);
        infoVBox.setPadding(new Insets(INFO_VBOX_PADDING_TOP, INFO_VBOX_PADDING_RIGHT, INFO_VBOX_PADDING_BOTTOM,
                INFO_VBOX_PADDING_LEFT));

        StackPane.setAlignment(infoVBox, Pos.CENTER_RIGHT);

        final StackPane infoStackPane = new StackPane();
        infoStackPane.getChildren().add(infoVBox);

        root.setRight(infoStackPane);

        gridPane = new GridPane();
        gridPane.setHgap(PANE_GAP_10);
        gridPane.setVgap(PANE_GAP_10);
        gridPane.setAlignment(Pos.CENTER);

        // Create Farm Sector
        createSector("img\\dashbord_image\\corn_field.jpeg", gridPane, 0, 0,
                String.valueOf(WordUtils.capitalizeFully(SectorName.FARM.toString().toLowerCase())));
        statusCircleFarm = new Circle(CIRCLE_STATUS_RADIUS);

        // Create Hospital Sector
        createSector("img\\dashbord_image\\hospital.jpeg", gridPane, 1, 0,
                String.valueOf(WordUtils.capitalizeFully(SectorName.HOSPITAL.toString().toLowerCase())));
        statusCircleHospital = new Circle(CIRCLE_STATUS_RADIUS);

        // Create Manufactory Sector
        createSector("img\\dashbord_image\\manufactory.jpg", gridPane, 0, 1,
                String.valueOf(WordUtils.capitalizeFully(SectorName.MANUFACTORY.toString().toLowerCase())));
        statusCircleManufactory = new Circle(CIRCLE_STATUS_RADIUS);

        // Create Military Base Sector
        createSector("img\\dashbord_image\\security.jpeg", gridPane, 1, 1, String
                .valueOf(WordUtils
                        .capitalizeFully(SectorName.MILITARY_BASE.toString().replace("_", " ").toLowerCase())));
        statusCircleMilitaryBase = new Circle(CIRCLE_STATUS_RADIUS);

        root.setCenter(gridPane);

        return root;
    }

    public void updateTimeLabel(final long time) {
        Platform.runLater(() -> {
            timeLabel.setText(String.valueOf(time));
        });
    }

    public void updateResourceLabel(final Map<String, Integer> resources) {

        Platform.runLater(() -> {
            population.setText(String.valueOf(resources.get(
                    String.valueOf(WordUtils.capitalizeFully(TextResources.POPULATION.toString().toLowerCase())))));
            foodVal.setText(String.valueOf(resources
                    .get(String.valueOf(WordUtils.capitalizeFully(TextResources.FOOD.toString().toLowerCase())))));
            medicineVal.setText(String.valueOf(resources
                    .get(String.valueOf(WordUtils.capitalizeFully(TextResources.MEDICINE.toString().toLowerCase())))));
            weaponVal.setText(String.valueOf(resources
                    .get(String.valueOf(WordUtils.capitalizeFully(TextResources.WEAPONS.toString().toLowerCase())))));
            screwVal.setText(String.valueOf(resources
                    .get(String.valueOf(WordUtils.capitalizeFully(TextResources.SCREW.toString().toLowerCase())))));
        });
    }

    private void createSector(final String backgroundImagePath, final GridPane gridPane, final int colIndex, final int rowIndex,
            final String sectorName) {

        final Circle[][] statusCircles = new Circle[2][2];

        final ImageManagerImpl imageManager = new ImageManagerImpl();

        final Image backgroundImage = imageManager.loadImage(backgroundImagePath);
        final Text textNameSector = new Text();
        textNameSector.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        textNameSector.setText(sectorName);
        textNameSector.setFill(Color.WHITE);

        final Rectangle textBackground = new Rectangle(BACKGROUND_SECTOR_TITLE_WIDTH, BACKGROUND_SECTOR_TITLE_HEIGHT);
        final ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(SECTOR_WIDTH);
        backgroundImageView.setFitHeight(SECTOR_HEIGHT);

        statusCircles[colIndex][rowIndex] = new Circle(CIRCLE_STATUS_RADIUS);
        statusCircles[colIndex][rowIndex].setFill(Color.GREEN);
        statusCircles[colIndex][rowIndex].radiusProperty()
                .bind(Bindings.min(gridPane.widthProperty(), gridPane.heightProperty()).divide(SPACING_20));
        statusCircles[colIndex][rowIndex].setStroke(Color.BLACK);

        final StackPane sectorPane = new StackPane();
        StackPane.setAlignment(textNameSector, Pos.TOP_CENTER);
        StackPane.setAlignment(textBackground, Pos.TOP_CENTER);
        sectorPane.getChildren().addAll(backgroundImageView, statusCircles[colIndex][rowIndex], textBackground,
                textNameSector);

        gridPane.add(sectorPane, colIndex, rowIndex);

        if (colIndex == 0 && rowIndex == 0) {
            statusCircleFarm = statusCircles[colIndex][rowIndex];
        } else if (colIndex == 1 && rowIndex == 0) {
            statusCircleHospital = statusCircles[colIndex][rowIndex];
        } else if (colIndex == 0 && rowIndex == 1) {
            statusCircleManufactory = statusCircles[colIndex][rowIndex];
        } else if (colIndex == 1 && rowIndex == 1) {
            statusCircleMilitaryBase = statusCircles[colIndex][rowIndex];
        }

    }

    public void updateCirle(List<Status> statuses) {
        System.out.println("Status View: " + statuses);
        Platform.runLater(() -> {

            statusCircleFarm.setFill(statusColor(statuses.get(0)));
            statusCircleHospital.setFill(statusColor(statuses.get(1)));
            statusCircleManufactory.setFill(statusColor(statuses.get(2)));
            statusCircleMilitaryBase.setFill(statusColor(statuses.get(3)));
        });
    }

    private Color statusColor(final Status status) {
        if (status == Status.GREEN) {
            return Color.GREEN;
        } else if (status == Status.YELLOW) {
             
            return Color.YELLOW;
        } else {
              
            return Color.RED;
        }
    }

    /**
     * @param text
     * @return a button with text
     */
    private Button createButton(final String text) {
        final Button button = new Button(text);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setStyle(BACKGROUND_COLOR_WHITE);
        button.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, BUTTON_FONT_SIZE));
        return button;
    }

    public void updateSimulationInfo(final String colonyName) {
        Platform.runLater(() -> {
            nameColony.setText(colonyName);
        });
    }

    public void createRandomEvent(RandomEvent randomEvent) {
        System.out.println("Event View: " + randomEvent.getDemageResources());
        new NewEventDialog(randomEvent).show();
    }

    @Override
    public void updateTimeLabel(final String time) {
        Platform.runLater(() -> {
            timeLabel.setText(time);
        });

    }

}
