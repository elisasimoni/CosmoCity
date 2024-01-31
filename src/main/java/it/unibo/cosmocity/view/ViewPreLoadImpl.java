package it.unibo.cosmocity.view;

import it.unibo.cosmocity.model.utility.ImageManagerImpl;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
 * This interface is used to create the GUI of the view
 * different implementation to create a pre loaded view
 */
 
public abstract class ViewPreLoadImpl implements View {

    protected final Stage stage;
    protected Scene scene;

    /** {@inheritDoc} */
    protected ViewPreLoadImpl(final Stage stage, final double width, final double height) {
        this.stage = stage;
       
    }

    /**
     * Set the scene in the stage and shows it.
     */
    public void show() {
        stage.setScene(this.scene);
        stage.getIcons().add(new ImageManagerImpl().loadImage("cosmocity_icon.png"));
        stage.show();
    }
}
