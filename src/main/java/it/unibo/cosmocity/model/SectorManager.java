package it.unibo.cosmocity.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.cosmocity.model.Sector.Status;
import it.unibo.cosmocity.model.resources.StackedResource;

public class SectorManager {
    private List<StackedResource> resourcesList;

    public SectorManager(List<StackedResource> resourcesList) {
        this.resourcesList = resourcesList;
    }

    public List<Status> checkStatus() {
        
        List<Status> listOfStatus = new ArrayList<>();
        int populationVal = resourcesList.get(0).getQta();

        for (StackedResource stackedResource : resourcesList) {
            if (stackedResource.getQta() == 0) {
                listOfStatus.add(Status.RED);
            } else {
                double val = (stackedResource.getQta() * 100) / populationVal;
                if (val >= 75) {
                    listOfStatus.add(Status.GREEN);
                } else if (val >= 50 && val < 75) {
                    listOfStatus.add(Status.YELLOW);

                } else {
                    listOfStatus.add(Status.RED);
                }
            }

        }
        return listOfStatus;
    }

}
