package it.unibo.cosmocity.model.resources;

public class ScrewStacked extends StackedResource {

    public ScrewStacked(int qta) {
        super(qta);
    }

    @Override
    public void setQta(int qta) {
        this.qta = qta;
    }

}
