package it.unibo.cosmocity.model.sector;

import java.util.ArrayList;
import java.util.List;

import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.sector.Sector.Status;
/*
 * A little manager to handle the sector status
 */
public class SectorManager {
    private List<StackedResource> resourcesList;
    private static final int POPULATION_INDEX_GOOD = 75;
    private static final int POPULATION_INDEX_MEDIUM = 50;


    /**
     * @param resourcesList
     */
    public SectorManager(List<StackedResource> resourcesList) {
        this.resourcesList = resourcesList;
    }

    /**
     * @return the resourcesList
     *         check the status of the sector
     *         if the status is red, the sector is not working
     *         if the status is yellow, the sector is working but not at the best
     *         if the status is green, the sector is working at the best
     */
    public List<Status> checkStatus() {

        List<Status> listOfStatus = new ArrayList<>();
        int populationVal = resourcesList.get(0).getQta();

        for (StackedResource stackedResource : resourcesList) {
            if (stackedResource.getQta() == 0) {
                listOfStatus.add(Status.RED);
            } else {
                double val = (stackedResource.getQta() * 100) / populationVal;
                if (val >= POPULATION_INDEX_GOOD) {
                    listOfStatus.add(Status.GREEN);
                } else if (val >= POPULATION_INDEX_MEDIUM && val < POPULATION_INDEX_GOOD) {
                    listOfStatus.add(Status.YELLOW);

                } else {
                    listOfStatus.add(Status.RED);
                }
            }

        }
        return listOfStatus;
    }

}
