package it.unibo.cosmocity.model.resources;

/**
 * Generic resource abstract class 
 */
public abstract class BaseResource implements Resource {
    
    protected int qta;

    /**
     * @param qta
     */
    protected BaseResource(int qta) {
        this.qta = qta;
    }

    public int getQta() {
        return qta;
    }
}