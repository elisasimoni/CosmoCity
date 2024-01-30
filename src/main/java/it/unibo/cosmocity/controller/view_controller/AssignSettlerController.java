package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.model.settlers.BaseSettler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssignSettlerController {
    private final Map<String, Long> settlersMap = new HashMap<>();
    private final List<String> settlers;

    public AssignSettlerController(List<String> settlers) {
        this.settlers = settlers;

    }


    /**
     * @return a list of settlers names
     * @implNote the list is sorted
     */
    public List<String> getSettlersNames() {
        return settlersMap.keySet().stream()
                                   .sorted()
                                   .collect(Collectors.toList());
    }

    public long getSettlerQuantity(String settlerName) {
        return settlersMap.getOrDefault(settlerName, 0L);
    }

    public List<String> getSectorOptions() {
        return List.of("Farm", "Hospital", "Military Base", "Officina");
    }
}
