package it.unibo.cosmocity.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class ViewImpl implements View {

    protected final Stage stage;
    protected Scene scene;

    /**
     * Constructor for the View class.
     *
     * @param stage  The JavaFX stage associated with this view.
     * @param width  width of the scene.
     * @param height height of the scene.
     */
    public ViewImpl(Stage stage, double width, double height) {
        this.stage = stage;
        initScene(width, height);
    }

    /**
     * Initializes the scene with the specified dimensions.
     * Create the first layout
     *
     * @param width  width of the scene.
     * @param height height of the scene.
     */
    private void initScene(double width, double height) {
        this.scene = new Scene(new Pane(), width, height);
        this.scene.setRoot(this.createLayout());
    }

    /**
     * Configures listeners for the view's resizing.
     * This method must be implemented by derived classes.
     */
    public abstract void setupResizeListeners();

    /**
     * Creates the layout for the view.
     *
     * @return The view's layout as a javafx.scene.layout.Pane object.
     */
    public abstract javafx.scene.layout.Pane createLayout();

    /**
     * Set the scene in the stage and shows it.
     */
    public void show() {
        stage.setScene(this.scene);
        stage.show();
    }
}
