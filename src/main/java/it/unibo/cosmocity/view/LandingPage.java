package it.unibo.cosmocity.view;

import it.unibo.cosmocity.controller.SimulationController;
import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.model.utility.ImageManagerImpl;
import it.unibo.cosmocity.view.dialog.LoadGameDialog;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LandingPage extends ViewImpl implements LandingPageView {

    private final Screen screen = Screen.getPrimary();
    private final double screenWidth = screen.getBounds().getWidth();
    private final double screenHeight = screen.getBounds().getHeight();
    private final SimulationController simulationController;

    public LandingPage(final Stage stage, final double width, final double height,
            final SimulationController simulationController) {
        super(stage, width, height);
        this.simulationController = simulationController;

    }

    @Override
    public Pane createGUI() {
        final BorderPane root = new BorderPane();
        final ImageManagerImpl imageManager = new ImageManagerImpl();
        final Pane backgroundPane = new Pane();
        final Image backgroundImage = imageManager.loadImage("menu_background_img.png");
        final ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundPane.getChildren().add(backgroundImageView);
        root.setCenter(backgroundPane);

        final Text title = new Text("Cosmo\nCity");
        title.setFont(Font.font("Elephant", FontWeight.BOLD, 200));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFill(Color.WHITE);
        title.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(10)));
        final StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundPane, title);
        root.setCenter(stackPane);

        final Button newGameBtn = createButton("New Game");
        final Button loadGameBtn = createButton("Load Game");
        final Button exitBtn = createButton("Exit");

        final VBox menuBtnBox = new VBox(20);
        menuBtnBox.getChildren().addAll(newGameBtn, loadGameBtn, exitBtn);
        menuBtnBox.setPadding(new Insets(0, 50, 0, 0));
        menuBtnBox.setAlignment(Pos.CENTER);

        stage.widthProperty().addListener(
                (observable, oldValue, newValue) -> backgroundImageView.setFitWidth(newValue.doubleValue()));
        stage.heightProperty().addListener(
                (observable, oldValue, newValue) -> backgroundImageView.setFitHeight(newValue.doubleValue()));

        menuBtnBox.maxHeightProperty().bind(scene.heightProperty());
        menuBtnBox.prefWidthProperty().bind(scene.widthProperty().divide(2.5));

        root.setRight(menuBtnBox);

        newGameBtn.setOnAction(e -> startSimulation());

        loadGameBtn.setOnAction(e -> loadSimulation());

        exitBtn.setOnAction(e -> exitSimulation());

        return root;
    }

    @Override
    public void startSimulation() {
        final SceneController sceneController = new SceneController();
        sceneController.nextSceneNavigator(
                new CreateColonyPage(stage, screenWidth * 0.5, screenHeight * 0.9, simulationController));
    }

    @Override
    public void loadSimulation() {
        this.stage.close();
        try {
            simulationController.loadSimulation();
        } catch (final Exception e) {
            new LoadGameDialog().show();
        }
    }

    @Override
    public void exitSimulation() {
        stage.close();
        simulationController.exitSimulation();
    }

    /**
     * @param text
     * @return a button with text
     */
    private Button createButton(final String text) {
        final Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-color: #ffffff");
        button.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        return button;
    }

}
