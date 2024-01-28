package it.unibo.cosmocity.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.cosmocity.controller.view_controller.AssignSettlerController;
import it.unibo.cosmocity.controller.view_controller.SceneController;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.Doctor;
import it.unibo.cosmocity.model.settlers.Gunsmith;
import it.unibo.cosmocity.model.settlers.Military;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class AssignSettler extends ViewImpl {



    public AssignSettler(Stage stage, double width, double height) {
        super(stage, width, height);

    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refresh'");
    }

    @Override
    public void initLogic() {
        

    }

    @Override
    public Pane createGUI() {
        SceneController sceneController = new SceneController();
        AssignSettlerController assignSettlerController = new AssignSettlerController();
        stage.setTitle("CosmoCity - Assign Settler");
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: darkBlue;");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        Text newGameText = new Text("Assign optional settlers\n to the sector");
        newGameText.setFont(Font.font("Elephant", FontWeight.BOLD, 150));
        newGameText.setTextAlignment(TextAlignment.CENTER);
        newGameText.setFill(Color.WHITE);
        newGameText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(20)));
        vbox.getChildren().add(newGameText);
        List<BaseSettler> settlers = new ArrayList<>();
        settlers.add(new Military());
        settlers.add(new Doctor());
        settlers.add(new Gunsmith());
        settlers.add(new Gunsmith());
        System.out.println(new Gunsmith().getProductedResource().getClass());
        List<Sector> sectors = new ArrayList<>();
        sectors.add(new Sector("Farm"));
        sectors.add(new Sector("Hospital"));
        sectors.add(new Sector("Military Base"));
        sectors.add(new Sector("Officina"));

        Map<String, Long> settlersMap = new HashMap<>();

        for (BaseSettler settler : settlers) {
            long number = settlers.stream().filter(s -> s.getClass().equals(settler.getClass())).count();
            settlersMap.put(settler.getClass().getName().replaceAll("it.unibo.cosmocity.model.settlers.", ""), number);
        }
        settlersMap.entrySet().stream()
            .distinct()
            .map(entry -> Map.entry(entry.getKey(), entry.getValue()))
            .forEach(entry -> {
                vbox.getChildren().add(createSettlerAssignBox(entry.getKey(), entry.getValue(), sectors));
            });


        Button startColonyButton = createButton("Start Colony");
        startColonyButton.maxHeightProperty().bind(scene.heightProperty());
        startColonyButton.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        vbox.getChildren().add(startColonyButton);
        vbox.maxHeightProperty().bind(scene.heightProperty());
        vbox.prefWidthProperty().bind(scene.widthProperty().divide(2.5));
        startColonyButton.setOnAction(e -> {
            sceneController.nextSceneNavigator(new Dashboard(stage, 1024, 1080));
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
        button.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        return button;
    }

    private HBox createSettlerAssignBox(String settlerName, long settlerQta, List<Sector> sectors) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        Text settlerText = new Text(settlerName);
        settlerText.setFont(Font.font("Elephant", FontWeight.NORMAL, 20));
        settlerText.styleProperty().bind(Bindings.concat("-fx-font-size: ", stage.widthProperty().divide(40)));
        settlerText.setFill(Color.WHITE);
        settlerText.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().add(settlerText);
        ObservableList<String> sectorsOption = sectors.stream().map((Sector s) -> s.getName())
                .collect(Collectors.toList()).stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        ComboBox<String> sectorDropdownMenu = new ComboBox<>(sectorsOption);
        sectorDropdownMenu.setPromptText("Select a sector");
        sectorDropdownMenu.setStyle("-fx-background-color: #ffffff");
        sectorDropdownMenu.setPrefWidth(200);
        sectorDropdownMenu.setPrefHeight(50);

        ComboBox<String> qtaSettlerDropdownMenu = new ComboBox<String>();
        for (int i = 0; i < settlerQta; i++) {
            qtaSettlerDropdownMenu.getItems().add(String.valueOf(i + 1));
        }
        qtaSettlerDropdownMenu.setPromptText("Number of settlers");
        qtaSettlerDropdownMenu.setStyle("-fx-background-color: #ffffff");
        qtaSettlerDropdownMenu.setPrefWidth(200);
        qtaSettlerDropdownMenu.setPrefHeight(50);
        hbox.getChildren().add(sectorDropdownMenu);
        hbox.getChildren().add(qtaSettlerDropdownMenu);

        return hbox;
    }

}
