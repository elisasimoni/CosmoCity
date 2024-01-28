package it.unibo.cosmocity.model.resources;

public class FoodStacked extends StackedResource{

    public FoodStacked(int qta) {
        super(qta);
    }

    public void setQta(int qta) {
        this.qta = qta;
    }
    
}
