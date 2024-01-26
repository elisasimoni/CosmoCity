package it.unibo.cosmocity.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class View {
    protected final Stage stage;
    protected Scene scene;

    /**
     * Constructor for the View class.
     *
     * @param stage  The JavaFX stage associated with this view.
     * @param width  width of the scene.
     * @param height height of the scene.
     */
    public View(Stage stage, double width, double height) {
        this.stage = stage;
        initScene(width, height);
    }

    /**
     * Initializes the scene with the specified dimensions.
     *
     * @param width  width of the scene.
     * @param height height of the scene.
     */
    private void initScene(double width, double height) {
        this.scene = new Scene(createLayout(), width, height);
    }

    /**
     * Configures listeners for the view's resizing.
     * This method must be implemented by derived classes.
     */
    protected abstract void setupResizeListeners();

    /**
     * Creates the layout for the view.
     *
     * @return The view's layout as a javafx.scene.layout.Pane object.
     */
    protected abstract javafx.scene.layout.Pane createLayout();

    /**
     * Set the scene in the stage and shows it.
     */
    public void show() {
        stage.setScene(this.scene);
        stage.show();
    }

}
