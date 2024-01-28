package it.unibo.cosmocity.view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.model.utility.ImageManagerImpl;

public class CreateColonyPage extends ViewImpl {

    private static final String FONT = "Elephant";
    private final Screen screen = Screen.getPrimary();
    private final double screenWidth = screen.getBounds().getWidth();
    private final double screenHeight = screen.getBounds().getHeight();

    public CreateColonyPage(Stage stage, double width, double height) {
        super(stage, width, height);
    }

    @Override
    public Pane createGUI() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: darkBlue;");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        Text newGameText = new Text("NEW GAME");
        newGameText.setFont(Font.font(FONT, FontWeight.BOLD, 150));
        newGameText.setTextAlignment(TextAlignment.CENTER);
        newGameText.setFill(Color.WHITE);
        newGameText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(12)));
        vbox.getChildren().add(newGameText);

        Text colonyNameText = new Text("Colony name");
        colonyNameText.setFont(Font.font(FONT, FontWeight.NORMAL, 20));
        colonyNameText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(20)));
        colonyNameText.setFill(Color.WHITE);
        colonyNameText.setTextAlignment(TextAlignment.CENTER);
        vbox.getChildren().add(colonyNameText);

        TextField colonyNameField = new TextField();
        colonyNameField.setPrefWidth(200);
        colonyNameField.setMaxWidth(200);
        colonyNameField.setPadding(new Insets(10, 10, 10, 10));
        colonyNameField.prefWidthProperty().bind(vbox.widthProperty());
        vbox.getChildren().add(colonyNameField);

        Text chooseSettlersText = new Text("Choose the settlers (max 10)");
        chooseSettlersText.setFont(Font.font(FONT, FontWeight.NORMAL, 25));

        chooseSettlersText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(40)));
        chooseSettlersText.setFill(Color.WHITE);
        chooseSettlersText.setTextAlignment(TextAlignment.CENTER);
        vbox.getChildren().add(chooseSettlersText);

        Text mandatorySettlerlText = new Text("Mandatory:");
        mandatorySettlerlText.setFont(Font.font(FONT, FontWeight.NORMAL, 20));
        mandatorySettlerlText.styleProperty()
                .bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(40)));
        mandatorySettlerlText.setFill(Color.WHITE);
        mandatorySettlerlText.setTextAlignment(TextAlignment.CENTER);
        vbox.getChildren().add(mandatorySettlerlText);

        HBox hboxMandatorySettler = new HBox();
        hboxMandatorySettler.setAlignment(Pos.CENTER);
        hboxMandatorySettler.setSpacing(10);

        hboxMandatorySettler.getChildren().add(createImageControl("img/settler_icon/Farmer_Icon.png", "Farmer"));
        hboxMandatorySettler.getChildren()
                .add(createImageControl("img/settler_icon/Technician_Icon.png", "Technician"));
        hboxMandatorySettler.getChildren().add(createImageControl("img/settler_icon/Doctor_Icon.png", "Doctor"));
        hboxMandatorySettler.getChildren().add(createImageControl("img/settler_icon/Military_Icon.png", "Military"));
        vbox.getChildren().add(hboxMandatorySettler);

        Text optionalText = new Text("Optional:");
        optionalText.setFont(Font.font(FONT, FontWeight.NORMAL, 20));
        optionalText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(40)));
        optionalText.setFill(Color.WHITE);
        optionalText.setTextAlignment(TextAlignment.CENTER);
        vbox.getChildren().add(optionalText);
        HBox hboxOptionalSettler = new HBox();
        hboxOptionalSettler.setAlignment(Pos.CENTER);
        hboxOptionalSettler.setSpacing(10);
        hboxOptionalSettler.getChildren().add(createImageControl("img/settler_icon/Farmer_Icon.png", "Farmer"));
        hboxOptionalSettler.getChildren()
                .add(createImageControl("img/settler_icon/Technician_Icon.png", "Technician"));
        hboxOptionalSettler.getChildren().add(createImageControl("img/settler_icon/Doctor_Icon.png", "Doctor"));
        hboxOptionalSettler.getChildren().add(createImageControl("img/settler_icon/Military_Icon.png", "Military"));

        vbox.getChildren().add(hboxOptionalSettler);

        Button nextButton = createButton("Next");
        nextButton.maxHeightProperty().bind(scene.heightProperty());
        nextButton.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        vbox.getChildren().add(nextButton);
        vbox.maxHeightProperty().bind(scene.heightProperty());
        vbox.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        SceneController sceneController = new SceneController();
        nextButton.setOnAction(e -> {
            sceneController.nextSceneNavigator(new AssignSettler(stage, screenWidth * 0.8, screenHeight * 0.8));
        });
        root.setCenter(vbox);

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
        button.setFont(Font.font(FONT, FontWeight.BOLD, 18));
        return button;
    }

    private VBox createImageControl(String imageURL, String nameSettler) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        ImageManagerImpl imageManager = new ImageManagerImpl();
        Image settlerImage = imageManager.loadImage(imageURL);
        ImageView imageView = new ImageView(settlerImage);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Label nameSettlerText = new Label(nameSettler);
        nameSettlerText.setTextFill(Color.WHITE);
        nameSettlerText.setFont(Font.font("Elephant", FontWeight.BOLD, 15));
        vbox.getChildren().add(nameSettlerText);
        vbox.getChildren().add(imageView);

        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-background-color: #008000");
        plusButton.setTextFill(Color.WHITE);
        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-background-color: #FF0000");
        minusButton.setTextFill(Color.WHITE);
        Label counterLabel = new Label("0");
        counterLabel.setTextFill(Color.WHITE);

        plusButton.setOnAction(event -> {
            int count = Integer.parseInt(counterLabel.getText());
            counterLabel.setText(String.valueOf(count + 1));
        });

        minusButton.setOnAction(event -> {
            int count = Integer.parseInt(counterLabel.getText());
            if (count > 0) {
                counterLabel.setText(String.valueOf(count - 1));
            }
        });

        HBox controls = new HBox(minusButton, counterLabel, plusButton);
        controls.setAlignment(Pos.CENTER);
        controls.setSpacing(10);

        vbox.getChildren().add(controls);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refresh'");
    }

}
