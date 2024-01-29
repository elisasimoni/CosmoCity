package it.unibo.cosmocity.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class ViewImpl implements View {

    protected final Stage stage;
    protected Scene scene;

    /** {@inheritDoc} */
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
        this.scene.setRoot(this.createGUI());
    }

    /**
     * Creates the layout for the view.
     *
     * @return The view's layout as a javafx.scene.layout.Pane object.
     */
    public abstract Pane createGUI();

    /**
     * Set the scene in the stage and shows it.
     */
    public void show() {
        stage.setScene(this.scene);
        stage.show();
    }
}
