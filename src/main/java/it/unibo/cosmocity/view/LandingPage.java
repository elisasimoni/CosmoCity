package it.unibo.cosmocity.view;

import it.unibo.cosmocity.model.utility.AudioManager;
import it.unibo.cosmocity.model.utility.ImageManager;
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

public class LandingPage extends View{

    public LandingPage(Stage stage, double width, double height) {
        super(stage, 900, 700);
    }

    @Override
    protected void setupResizeListeners() {
        // Aggiungi gli ascoltatori per il ridimensionamento specifico della LandingPage se necessario
    }

    @Override
    protected Pane createLayout() {
        BorderPane root = new BorderPane();
        ImageManager imageManager = new ImageManager();
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

        root.setRight(menuBtnBox);

        AudioManager audioManager = new AudioManager();
        audioManager.play("audio/menu_music.mp3");

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
    
}
