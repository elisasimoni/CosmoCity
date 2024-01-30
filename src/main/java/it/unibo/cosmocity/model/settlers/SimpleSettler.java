package it.unibo.cosmocity.model.settlers;

import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Food;
import it.unibo.cosmocity.model.resources.Medicine;
import it.unibo.cosmocity.model.resources.Screw;
import it.unibo.cosmocity.model.resources.Weapons;

public abstract class SimpleSettler implements BaseSettler {

    /**
     * Appetite of the settler
     */
    private int appetite;

    private String sectorAssigned;

    /**
     * @return the appetite of the settler
     */
    public int getAppetite() {
        return this.appetite;
    }

    /**
     * @return the sector assigned to the settler
     */
    public String getSectorAssigned (){
        return this.sectorAssigned;
    }

    /**
     * 
     * @param sectorAssigned 
     * the sector assigned to the settler
     */
    public void setSectorAssigned(String getSectorAssigned){
        this.sectorAssigned = getSectorAssigned;
    }

    public abstract BaseResource getProductedResource();

    /**
     * @return the resource produced by the settler
     */
    public BaseResource setProductedResource(int qtaWorkshop, int qtaMilitaryBase, int qtaHospital, int qtaFarm) {
        switch(this.sectorAssigned){
            case "Workshop":
                return new Screw(qtaWorkshop);
            case "Military Base":
                return new Weapons(qtaMilitaryBase);
            case "Hospital":
                return new Medicine(qtaHospital);
            case "Farm":
                return new Food(qtaFarm);
            default:
                return null;
                
        }
    }


}
