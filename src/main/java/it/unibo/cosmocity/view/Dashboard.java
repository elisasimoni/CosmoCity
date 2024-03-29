package it.unibo.cosmocity.view;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.text.WordUtils;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.model.event.GoodEvent;
import it.unibo.cosmocity.model.event.RandomEvent;
import it.unibo.cosmocity.model.sector.Sector.SectorName;
import it.unibo.cosmocity.model.sector.Sector.Status;
import it.unibo.cosmocity.model.utility.ImageManagerImpl;
import it.unibo.cosmocity.view.dialog.GameOverDialog;
import it.unibo.cosmocity.view.dialog.NewEventDialog;
import it.unibo.cosmocity.view.dialog.NewSettlerDialog;
import it.unibo.cosmocity.view.dialog.SaveGameDialog;
import javafx.application.Platform;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

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
    private static final int MENU_BTN_BOX_PADDING_TOP = 10;
    private static final int MENU_BTN_BOX_PADDING_BOTTOM = 0;

    private static final int MENU_BTN_BOX_PADDING_LEFT = 50;
    private static final int MENU_BTN_BOX_PADDING_RIGHT = 50;

    private static final int SECTOR_WIDTH = 300;
    private static final int SECTOR_HEIGHT = 300;

    private static final int BACKGROUND_SECTOR_TITLE_WIDTH = 300;

    private static final int BACKGROUND_SECTOR_TITLE_HEIGHT = 30;

    private static final int CIRCLE_STATUS_RADIUS = 15;

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
    private Circle statusCircleWorkshop;
    private Circle statusCircleMilitaryBase;
    private HBox settlerHospital;
    private HBox settlerWorkshop;
    private HBox settlerMilitaryBase;
    private HBox settlerFarm;
    private boolean isPause = false;

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
            isPause = true;
            pauseSimulation();

        });

        final Button saveButton = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.SAVE.toString().toLowerCase())));
        saveButton.setOnAction(e -> {
            final SaveGameDialog saveGameDialog = new SaveGameDialog();
            final FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Game");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
            final File file = fileChooser.showSaveDialog(stage);
            simulationController.saveSimulation(file);

        });

        final Button resources = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.RESOURCES.toString().toLowerCase())));
        resources.setOnAction(e -> {
            new MoveResource(simulationController);
        });

        final Button exitButton = createButton(
                String.valueOf(WordUtils.capitalizeFully(TextButton.EXIT.toString().toLowerCase())));
        exitButton.setOnAction(e -> {
            new SceneController().nextSceneNavigator(new LandingPage(stage, 900, 700, simulationController));
        });

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
        final HBox circleFarm = new HBox(SPACING_10);
        statusCircleFarm = new Circle(CIRCLE_STATUS_RADIUS);
        statusCircleFarm.setFill(Color.GREEN);
        statusCircleFarm.setStroke(Color.BLACK);
        final Text textFarm = new Text("Farm: ");
        textFarm.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        textFarm.setFill(Color.WHITE);
        circleFarm.getChildren().addAll(textFarm, statusCircleFarm);

        final HBox circleHospital = new HBox(SPACING_10);
        statusCircleHospital = new Circle(CIRCLE_STATUS_RADIUS);
        statusCircleHospital.setFill(Color.GREEN);
        statusCircleHospital.setStroke(Color.BLACK);
        final Text textHospital = new Text("Hospital: ");
        textHospital.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        textHospital.setFill(Color.WHITE);
        circleHospital.getChildren().addAll(textHospital, statusCircleHospital);
        final HBox circleWorkshop = new HBox(SPACING_10);
        statusCircleWorkshop = new Circle(CIRCLE_STATUS_RADIUS);
        statusCircleWorkshop.setFill(Color.GREEN);
        statusCircleWorkshop.setStroke(Color.BLACK);
        final Text textWorkshop = new Text("Workshop: ");
        textWorkshop.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        textWorkshop.setFill(Color.WHITE);
        circleWorkshop.getChildren().addAll(textWorkshop, statusCircleWorkshop);
        final HBox circleMilitaryBase = new HBox(SPACING_10);
        statusCircleMilitaryBase = new Circle(CIRCLE_STATUS_RADIUS);
        statusCircleMilitaryBase.setFill(Color.GREEN);
        statusCircleMilitaryBase.setStroke(Color.BLACK);
        final Text textMilitaryBase = new Text("Military Base: ");
        textMilitaryBase.setFont(Font.font(FONT_FAMILY, FONT_SIZE_LABEL));
        textMilitaryBase.setFill(Color.WHITE);
        circleMilitaryBase.getChildren().addAll(textMilitaryBase, statusCircleMilitaryBase);
        settlerHospital = new HBox(SPACING_10);
        settlerWorkshop = new HBox(SPACING_10);
        settlerMilitaryBase = new HBox(SPACING_10);
        settlerFarm = new HBox(SPACING_10);
        infoVBox.getChildren().addAll(circleFarm, settlerFarm, circleHospital, settlerHospital, circleWorkshop,
                settlerWorkshop, circleMilitaryBase, settlerMilitaryBase);

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

        // Create Hospital Sector
        createSector("dashbord_image/hospital.jpeg", gridPane, 1, 0,
                String.valueOf(WordUtils.capitalizeFully(SectorName.HOSPITAL.toString().toLowerCase())));

        // Create Workshop Sector
        createSector("dashbord_image/manufactory.jpg", gridPane, 0, 1,
                String.valueOf(WordUtils.capitalizeFully(SectorName.WORKSHOP.toString().toLowerCase())));

        // Create Military Base Sector
        createSector("dashbord_image/security.jpeg", gridPane, 1, 1, String
                .valueOf(WordUtils
                        .capitalizeFully(SectorName.MILITARY_BASE.toString().replace("_", " ").toLowerCase())));

        root.setCenter(gridPane);

        return root;
    }

    public void settlerToSectorUpdate() {
        settlerSectorMap = this.simulationController.getSimulation().getSettlers().stream()
                .filter(settler -> settler.getSectorAssigned() != null)
                .collect(Collectors.toMap(s -> s.getClass().getSimpleName(),
                        s -> s.getSectorAssigned()));

        Platform.runLater(() -> {
            settlerFarm.getChildren().clear();
            settlerHospital.getChildren().clear();
            settlerWorkshop.getChildren().clear();
            settlerMilitaryBase.getChildren().clear();
            settlerSectorMap.forEach((k, v) -> {
                final Text text = new Text(k);
                text.setFont(Font.font(FONT_FAMILY, 15));
                text.setFill(Color.YELLOW);
                if (v.equals("Farm")) {
                    settlerFarm.getChildren().add(text);
                } else if (v.equals("Hospital")) {
                    settlerHospital.getChildren().add(text);
                } else if (v.equals("Workshop")) {
                    settlerWorkshop.getChildren().add(text);
                } else if (v.equals("Military Base")) {
                    settlerMilitaryBase.getChildren().add(text);
                }
            });
        });

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

        statusCircleFarm.setFill(statusColor(statuses.get(0)));
        statusCircleHospital.setFill(statusColor(statuses.get(1)));
        statusCircleWorkshop.setFill(statusColor(statuses.get(3)));
        statusCircleMilitaryBase.setFill(statusColor(statuses.get(4)));
    }

    public void updateSimulationInfo(final String colonyName) {
        Platform.runLater(() -> {
            nameColony.setText(colonyName);
        });
    }

    @Override
    public boolean createRandomEvent(final RandomEvent randomEvent) {
        final NewEventDialog eventDiag = new NewEventDialog(randomEvent, false);
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

        final StackPane sectorPane = new StackPane();
        StackPane.setAlignment(textNameSector, Pos.TOP_CENTER);
        StackPane.setAlignment(textBackground, Pos.TOP_CENTER);
        sectorPane.getChildren().addAll(backgroundImageView, textBackground,
                textNameSector);

        gridPane.add(sectorPane, colIndex, rowIndex);

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
            new GameOverDialog().show();
            System.exit(0);

        });
    }

    @Override
    public boolean pauseSimulation() {
        return this.isPause;
    }

    public void setPause(final boolean isPause) {
        this.isPause = isPause;
    }

    public boolean getPause() {
        return this.isPause;
    }

}