package it.unibo.cosmocity.model.settlers;

public class Settler {

    private String role;
    private double productionRate;
    private double consumtionRate;

    /**
     * @param role
     */
    public Settler(final String role, final double productionRate, final double consumtionRate) {
        this.role = role;
        this.productionRate = productionRate;
        this.consumtionRate = consumtionRate;
    }

    /**
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     * @return
     */
    public double getProductionRate() {
        return productionRate;
    }

    public double getConsumtionRate() {
        return consumtionRate;
    }

    /**
     * @param productionRate
     */
    public void setProductionRate(double productionRate) {
        this.productionRate = productionRate;
    }

    /**
     * @param consumtionRate
     */
    public void setConsumtionRate(double consumtionRate) {
        this.consumtionRate = consumtionRate;
    }

}
