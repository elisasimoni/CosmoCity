package it.unibo.cosmocity.model.settlers;

public interface SettlerFactory {
    /**
     * @param role
     * @param productionRate
     * @param consumtionRate
     * @return
     */
    public Settler createSettler(SettlerRole settlerRole);

}
