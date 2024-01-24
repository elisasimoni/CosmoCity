package it.unibo.cosmocity;

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
import javafx.scene.paint.Color;
import javafx.beans.binding.Bindings;
import java.awt.image.BufferedImage;

import it.unibo.cosmocity.model.utility.AudioManager;
import it.unibo.cosmocity.model.utility.ImageManager;
import it.unibo.cosmocity.view.LandingPage;

public class CosmoCity extends Application {

    public static void main(final String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
       LandingPage landingPage = new LandingPage(primaryStage);
        landingPage.show();
        

    }
}
