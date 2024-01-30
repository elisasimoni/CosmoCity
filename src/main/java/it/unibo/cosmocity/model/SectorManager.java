package it.unibo.cosmocity.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.model.Sector.Status;
import it.unibo.cosmocity.model.resources.Population;
import it.unibo.cosmocity.model.resources.StackedResource;

public class SectorManager {
    private List<StackedResource> resourcesList;
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();

    public List<Status> checkStatus(List<StackedResource> eventsResources) {
        List<Status> listOfStatus = new ArrayList<>();
        int populationVal = resourcesList.get(0).getQta();
        for (StackedResource stackedResource : eventsResources) {
            double val = (stackedResource.getQta() * 100) / populationVal;
            if (val >= 75) {
                listOfStatus.add(Status.GREEN);
            } else if (val >= 50 && val < 75) {
                listOfStatus.add(Status.YELLOW);

            } else {
                listOfStatus.add(Status.RED);
            }
        }
        return listOfStatus;
    }

}
