package it.unibo.cosmocity.model.resources;

public class MedicineStacked extends StackedResource {

    public MedicineStacked(int qta) {
        super(qta);
    }

    @Override
    public void setQta(int qta) {
        this.qta = qta;
    }

}
