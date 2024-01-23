package it.unibo.cosmocity;

import it.unibo.cosmocity.model.settlers.FarmerFactory;
import it.unibo.cosmocity.model.settlers.SettlerFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.beans.binding.Bindings;

public class CosmoCity extends Application {

    public static void main(final String[] args) {
        SettlerFactory factory = new FarmerFactory();
        Character farmer = factory.createCharacter("John Doe", 30, 10);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Immagine di sfondo
        Pane backgroundPane = new Pane();
        Image backgroundImage = new Image(
                "C:\\Users\\Elisa\\Desktop\\ProjectsOOP\\CosmoCity\\src\\main\\java\\it\\unibo\\resources\\img\\menu_background_img.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundPane.getChildren().add(backgroundImageView);
        root.setCenter(backgroundPane);

        // Testo
        Text title = new Text("Cosmo\nCity");
        title.setFont(Font.font("Elephant", FontWeight.BOLD, 200));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFill(Color.WHITE);
        // Bindings for text size
        title.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().divide(10)));
        // Pannello per sovrapporre il testo all'immagine
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundPane, title);
        root.setCenter(stackPane);

        // Pulsanti del menu
        Button newGameBtn = new Button("New Game");
        Button loadGameBtn = new Button("Load Game");
        Button exitBtn = new Button("Exit");
        newGameBtn.setPrefWidth(300);
        loadGameBtn.setPrefWidth(300);
        exitBtn.setPrefWidth(300);
        newGameBtn.setPrefHeight(50);
        loadGameBtn.setPrefHeight(50);
        exitBtn.setPrefHeight(50);
        newGameBtn.setStyle("-fx-background-color: #ffffff");
        loadGameBtn.setStyle("-fx-background-color: #ffffff");
        exitBtn.setStyle("-fx-background-color: #ffffff");
        loadGameBtn.setPrefHeight(50);
        exitBtn.setPrefHeight(50);
        newGameBtn.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        loadGameBtn.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        exitBtn.setFont(Font.font("Elephant", FontWeight.BOLD, 18));

        VBox menuBtnBox = new VBox(20);
        menuBtnBox.getChildren().addAll(newGameBtn, loadGameBtn, exitBtn);
        menuBtnBox.setPadding(new Insets(0, 50, 0, 0));
        menuBtnBox.setAlignment(Pos.CENTER);

        root.setRight(menuBtnBox);

        // Configurazione della scena
        Scene scene = new Scene(root, 900, 700);

        // Gestione del ridimensionamento

        primaryStage.widthProperty().addListener(
                (observable, oldValue, newValue) -> backgroundImageView.setFitWidth(newValue.doubleValue()));
        primaryStage.heightProperty().addListener(
                (observable, oldValue, newValue) -> backgroundImageView.setFitHeight(newValue.doubleValue()));

        menuBtnBox.maxHeightProperty().bind(scene.heightProperty());
        menuBtnBox.prefWidthProperty().bind(scene.widthProperty().divide(2.5)); // Regola secondo le tue esigenze

        primaryStage.setTitle("CosmoCity");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
