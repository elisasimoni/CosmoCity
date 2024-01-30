package it.unibo.cosmocity.view;

import it.unibo.cosmocity.model.utility.ImageManagerImpl;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class ViewPreLoadImpl implements View {

    protected final Stage stage;
    protected Scene scene;

    /** {@inheritDoc} */
    public ViewPreLoadImpl(Stage stage, double width, double height) {
        this.stage = stage;
       
    }

    /**
     * Set the scene in the stage and shows it.
     */
    public void show() {
        stage.setScene(this.scene);
        stage.getIcons().add(new ImageManagerImpl().loadImage("img/cosmocity_icon.png"));
        stage.show();
    }
}
