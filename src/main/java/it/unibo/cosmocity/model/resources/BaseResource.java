package it.unibo.cosmocity.model.resources;

public abstract class BaseResource implements Resource {
    
    protected int qta;

    public BaseResource(int qta) {
        this.qta = qta;
    }

    public int getQta() {
        return qta;
    }
}