package it.unibo.cosmocity.model.resources;

public class WeaponsStacked extends StackedResource {

    public WeaponsStacked(int qta) {
        super(qta);
    }

    @Override
    public void setQta(int qta) {
        this.qta = qta;
    }

}
