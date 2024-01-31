package it.unibo.cosmocity.view;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.model.event.GoodEvent;
import it.unibo.cosmocity.model.event.RandomEvent;
import it.unibo.cosmocity.model.sector.Sector.SectorName;
import it.unibo.cosmocity.model.sector.Sector.Status;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.utility.ImageManagerImpl;
import it.unibo.cosmocity.view.dialog.GameOverDialog;
import it.unibo.cosmocity.view.dialog.NewEventDialog;
import it.unibo.cosmocity.view.dialog.NewSettlerDialog;
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

public class Dashboard extends ViewImpl implements DashboardView {

    private enum TextResources {
        TIME,
        POPULATION,
        FOOD,
        MEDICINE,
        WEAPONS,
        SCREW
    }

    private enum TextButton {
        PAUSE,
        SAVE,
        RESOURCES,
        EXIT
    }

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

    private GridPane gridPane;
    private Label foodVal;
    private Label timeLabel;
    private Label medicineVal;
    private Label weaponVal;
    private Label screwVal;
    private Text nameColony;
    private Label population;
    Map<String, String> settlerSectorMap;
    private final SimulationController simulationController;
    private final SceneController sceneController = new SceneController();
    private Circle statusCircleFarm;
    private Circle statusCircleHospital;
    private Circle statusCircleManufactory;
    private Circle statusCircleMilitaryBase;

    public Dashboard(final Stage stage, final double width, final double height,
            final SimulationController simulationController) {
        super(stage, width, height);
        this.simulationController = simulationController;
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

        Button saveButton = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.SAVE.toString().toLowerCase())));
        saveButton.setOnAction(e -> {
            final SaveGameDialog saveGameDialog = new SaveGameDialog();
            saveGameDialog.show();
            if (saveGameDialog.isSaved()) {
                simulationController.saveSimulation();
            } else {
                sceneController.nextSceneNavigator(this);
            }
        });

        final Button resources = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.RESOURCES.toString().toLowerCase())));
        resources.setOnAction(e -> {
            new MoveResource(simulationController);
        });

        final Button exitButton = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.EXIT.toString().toLowerCase())));

        final VBox menuBtnBox = new VBox(SPACING_20);
        menuBtnBox.getChildren().addAll(pauseButton, saveButton, resources, exitButton);
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

        final Text timerLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.TIME.toString().toLowerCase())));
        timerLabel.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        timerLabel.setFill(Color.WHITE);

        this.timeLabel = new Label("");
        timeLabel.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        timeLabel.setTextFill(Color.YELLOW);

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

        this.foodVal = new Label("");
        foodVal.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        foodVal.setTextFill(Color.YELLOW);

        // Medicine
        final Text medicineLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.MEDICINE.toString().toLowerCase())));
        medicineLabel.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        medicineLabel.setFill(Color.WHITE);

        this.medicineVal = new Label("");
        medicineVal.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        medicineVal.setTextFill(Color.YELLOW);

        // Weapon
        final Text weaponLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.WEAPONS.toString().toLowerCase())));
        weaponLabel.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        weaponLabel.setFill(Color.WHITE);

        this.weaponVal = new Label("");
        weaponVal.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, FONT_SIZE_LABEL));
        weaponVal.setTextFill(Color.YELLOW);

        // Screw
        final Text screwLabel = new Text(
                String.valueOf(WordUtils.capitalizeFully(TextResources.SCREW.toString().toLowerCase())));
        screwLabel.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        screwLabel.setFill(Color.WHITE);

        this.screwVal = new Label("");
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
        createSector("dashbord_image/corn_field.jpeg", gridPane, 0, 0,
                String.valueOf(WordUtils.capitalizeFully(SectorName.FARM.toString().toLowerCase())));
        statusCircleFarm = new Circle(CIRCLE_STATUS_RADIUS);

        // Create Hospital Sector
        createSector("dashbord_image/hospital.jpeg", gridPane, 1, 0,
                String.valueOf(WordUtils.capitalizeFully(SectorName.HOSPITAL.toString().toLowerCase())));
        statusCircleHospital = new Circle(CIRCLE_STATUS_RADIUS);

        // Create Manufactory Sector
        createSector("dashbord_image/manufactory.jpg", gridPane, 0, 1,
                String.valueOf(WordUtils.capitalizeFully(SectorName.MANUFACTORY.toString().toLowerCase())));
        statusCircleManufactory = new Circle(CIRCLE_STATUS_RADIUS);

        // Create Military Base Sector
        createSector("dashbord_image/security.jpeg", gridPane, 1, 1, String
                .valueOf(WordUtils
                        .capitalizeFully(SectorName.MILITARY_BASE.toString().replace("_", " ").toLowerCase())));
        statusCircleMilitaryBase = new Circle(CIRCLE_STATUS_RADIUS);
        statusCircleMilitaryBase.setFill(Color.RED);

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
            foodVal.setText(resources.get("FoodStacked").toString());
            medicineVal.setText(resources.get("MedicineStacked").toString());
            weaponVal.setText(resources.get("WeaponsStacked").toString());
            screwVal.setText(resources.get("ScrewStacked").toString());
        });
    }

    public void updateCirle(final List<Status> statuses) {

        statusCircleFarm.setFill(statusColor(statuses.get(1)));
        statusCircleHospital.setFill(statusColor(statuses.get(2)));
        statusCircleManufactory.setFill(statusColor(statuses.get(3)));
        statusCircleMilitaryBase.setFill(statusColor(statuses.get(4)));
    }

    public void updateSimulationInfo(final String colonyName) {
        Platform.runLater(() -> {
            nameColony.setText(colonyName);
        });
    }

    @Override
    public boolean createRandomEvent(final RandomEvent randomEvent) {
        NewEventDialog eventDiag = new NewEventDialog(randomEvent, false);
        eventDiag.show();
        return eventDiag.getChosen();

    }

    @Override
    public void createGoodEvent(final GoodEvent goodEvent) {
        new NewSettlerDialog(goodEvent).show();
    }

    @Override
    public void updateTimeLabel(final String time) {
        Platform.runLater(() -> {
            timeLabel.setText(time);
        });

    }

    /**
     * @param backgroundImagePath
     * @param gridPane
     * @param colIndex
     * @param rowIndex
     * @param sectorName
     *                            Create a sector with the name and the background
     *                            image and circle status
     */
    private void createSector(final String backgroundImagePath, final GridPane gridPane, final int colIndex,
            final int rowIndex,
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

    /**
     * @param status
     * @return the color of the circle
     */
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

    /*
     * Game Over
     * recall the GameOverDialog
     */
    @Override
    public void showGameOver() {
        Platform.runLater(() -> {
            new SceneController().nextSceneNavigator(new LandingPage(stage, 900, 700, simulationController));
            new GameOverDialog().show();

        });
    }

}