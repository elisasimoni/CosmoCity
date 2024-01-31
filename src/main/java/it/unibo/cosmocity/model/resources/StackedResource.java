package it.unibo.cosmocity.model.resources;
/*
 * Generic resource to stacked resource type
 */
public class StackedResource extends BaseResource{

    public StackedResource(int qta) {
        super(qta);
    }

    /**
     * @param qta
     * set the quantity of the resource
     */
    public void setQta(int qta) {
        this.qta = qta;
    }
    
    
}
