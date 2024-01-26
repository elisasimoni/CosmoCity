package it.unibo.cosmocity.view;

import it.unibo.cosmocity.model.utility.AudioManager;
import it.unibo.cosmocity.model.utility.ImageManagerImpl;
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
import javafx.stage.Stage;


public class LandingPage extends ViewImpl {


     public LandingPage(Stage stage, double width, double height) {
        super(stage, width, height);
    }

    @Override
    public void setupResizeListeners() {
      // TODO document why this method is empty
    }

    @Override
    public Pane createLayout() {
        BorderPane root = new BorderPane();
        ImageManagerImpl imageManager = new ImageManagerImpl();
        Pane backgroundPane = new Pane();
        Image backgroundImage = imageManager.loadImage("img/menu_background_img.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundPane.getChildren().add(backgroundImageView);
        root.setCenter(backgroundPane);

        Text title = new Text("Cosmo\nCity");
        title.setFont(Font.font("Elephant", FontWeight.BOLD, 200));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFill(Color.WHITE);
        title.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(10)));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundPane, title);
        root.setCenter(stackPane);

        Button newGameBtn = createButton("New Game");
        Button loadGameBtn = createButton("Load Game");
        Button exitBtn = createButton("Exit");

        VBox menuBtnBox = new VBox(20);
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

        AudioManager audioManager = new AudioManager();
        audioManager.play("audio/menu_music.mp3");

        return root;
    }

    /**
     * @param text
     * @return a button with text
     */
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-color: #ffffff");
        button.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        return button;
    }

}

