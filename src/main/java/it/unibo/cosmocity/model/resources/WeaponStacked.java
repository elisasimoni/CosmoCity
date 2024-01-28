package it.unibo.cosmocity.model.resources;

public class WeaponStacked extends StackedResource {
    
        public WeaponStacked(int qta) {
            super(qta);
        }
    
        public void setQta(int qta) {
            this.qta = qta;
        }
    
}
