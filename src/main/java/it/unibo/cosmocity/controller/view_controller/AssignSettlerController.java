package it.unibo.cosmocity.controller.view_controller;

import it.unibo.cosmocity.model.settlers.BaseSettler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssignSettlerController {
    private final Map<String, Long> settlersMap = new HashMap<>();
    private final List<BaseSettler> settlers;

    public AssignSettlerController(List<BaseSettler> settlers) {
        this.settlers = settlers;
        assignSettler();
    }

    private void assignSettler() {
        settlersMap.clear();
        for (BaseSettler settler : settlers) {
            long number = settlers.stream()
                                  .filter(s -> s.getClass()
                                                .equals(settler.getClass()))
                                  .count();
            settlersMap.put(settler.getClass().getSimpleName(), number);
        }
    }

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
