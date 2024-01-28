package it.unibo.cosmocity.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.cosmocity.controller.view_controller.AssignSettlerController;
import it.unibo.cosmocity.controller.view_controller.DashBoardController;
import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.Doctor;
import it.unibo.cosmocity.model.settlers.Gunsmith;
import it.unibo.cosmocity.model.settlers.Military;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Dashboard extends ViewImpl {
    

    public Dashboard(Stage stage, double width, double height) {
        super(stage, width, height);
    }

    @Override
    public void refresh() {
       createGUI();
    }

    @Override
    public void initLogic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initLogic'");
    }

    @Override
    public Pane createGUI() {
        SceneController sceneController = new SceneController();
        DashBoardController dashBoardController = new DashBoardController();
        stage.setTitle("CosmoCity - Colony Dashboard");
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: darkBlue;");

        VBox vboxCenter = new VBox();
        vboxCenter.setAlignment(Pos.CENTER);
        vboxCenter.setSpacing(30);

        Text newGameText = new Text("La mia super Colonia");
        newGameText.setFont(Font.font("Elephant", FontWeight.BOLD, 150));
        newGameText.setTextAlignment(TextAlignment.CENTER);
        newGameText.setFill(Color.WHITE);
        newGameText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(20)));
        vboxCenter.getChildren().add(newGameText);

    
        VBox vboxLeft = new VBox();
        vboxLeft.setAlignment(Pos.CENTER);
        vboxLeft.setSpacing(30);

        Label timeLabel = new Label("Time: "+ dashBoardController.updateTime());
        VBox vboxRight = new VBox();
        vboxRight.setAlignment(Pos.CENTER);
        vboxRight.setSpacing(30);

        Text resourceText = new Text("Resources:");
        resourceText.setFont(Font.font("Elephant", FontWeight.BOLD, 80));
        resourceText.setTextAlignment(TextAlignment.CENTER);
        resourceText.setFill(Color.WHITE);
        resourceText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(20)));
        vboxRight.getChildren().add(resourceText);
    
        vboxRight.getChildren().add(createResourceLine("Population", 100));
        vboxRight.getChildren().add(createResourceLine("Food", 100));
        vboxRight.getChildren().add(createResourceLine("Medicine", 100));
        vboxRight.getChildren().add(createResourceLine("Screw", 100));
        vboxRight.getChildren().add(createResourceLine("Weapons", 100));
       
        Button menuButton = createButton("Start Colony");
        menuButton.maxHeightProperty().bind(scene.heightProperty());
        menuButton.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        vboxLeft.getChildren().add(menuButton);
        vboxLeft.maxHeightProperty().bind(scene.heightProperty());
        vboxLeft.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        

        Button startColonyButton = createButton("Start Colony");
        startColonyButton.maxHeightProperty().bind(scene.heightProperty());
        startColonyButton.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        vboxCenter.getChildren().add(startColonyButton);
        vboxCenter.maxHeightProperty().bind(scene.heightProperty());
        vboxCenter.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        root.setLeft(vboxLeft);
        root.setCenter(vboxCenter);
        root.setRight(vboxRight);
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
