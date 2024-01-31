package it.unibo.cosmocity.view;

import it.unibo.cosmocity.model.utility.ImageManagerImpl;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class ViewImpl implements View {

    protected final Stage stage;
    protected Scene scene;

    /** {@inheritDoc} */
    protected ViewImpl(final Stage stage, final double width, final double height) {
        this.stage = stage;
        initScene(width, height);
    }

    /**
     * Set the scene in the stage and shows it.
     */
    public void show() {
        stage.setScene(this.scene);
        stage.getIcons().add(new ImageManagerImpl().loadImage("cosmocity_icon.png"));
        stage.show();
    }

    /**
     * Initializes the scene with dimensions.
     * Create the first layout
     *
     * @param width  width of the scene.
     * @param height height of the scene.
     */
    private void initScene(final double width, final double height) {
        this.scene = new Scene(new Pane(), width, height);
        this.scene.setRoot(this.createGUI());
    }
}
