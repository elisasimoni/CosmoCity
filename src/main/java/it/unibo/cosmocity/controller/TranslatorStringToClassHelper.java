package it.unibo.cosmocity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.Food;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.Medicine;
import it.unibo.cosmocity.model.resources.MedicineStacked;
import it.unibo.cosmocity.model.resources.Population;
import it.unibo.cosmocity.model.resources.Screw;
import it.unibo.cosmocity.model.resources.ScrewStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.resources.Weapons;
import it.unibo.cosmocity.model.resources.WeaponsStacked;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.Blacksmith;
import it.unibo.cosmocity.model.settlers.Chemist;
import it.unibo.cosmocity.model.settlers.Cook;
import it.unibo.cosmocity.model.settlers.Doctor;
import it.unibo.cosmocity.model.settlers.Farmer;
import it.unibo.cosmocity.model.settlers.Gunsmith;
import it.unibo.cosmocity.model.settlers.MandatorySettler;
import it.unibo.cosmocity.model.settlers.Military;
import it.unibo.cosmocity.model.settlers.SimpleSettler;
import it.unibo.cosmocity.model.settlers.Technician;
import java.util.ArrayList;

public class TranslatorStringToClassHelper {

    private static Enum SettlerType;

    public TranslatorStringToClassHelper() {

    }

    /**
     * @param settlers
     * @return a list of settlers
     */
    public List<BaseSettler> translateSettler(List<String> settlers) {
        return settlers.stream().map(settler -> switch (settler) {
            case "Doctor" -> new Doctor();
            case "Farmer" -> new Farmer();
            case "Technician" -> new Technician();
            case "Military" -> new Military();
            case "Cook" -> new Cook();
            case "Gunsmith" -> new Gunsmith();
            case "Chemist" -> new Chemist();
            case "Blacksmith" -> new Blacksmith();
            default -> throw new IllegalArgumentException("Invalid settler name");
        }).collect(Collectors.toList());
    }

    /**
     * @param resources
     * @return a list of stacked resources
     */
    public List<StackedResource> translateResources(Map<String, Integer> resources) {
        return resources.entrySet().stream().map(entry -> {
            String resourceName = entry.getKey();
            int resourceValue = entry.getValue();
            switch (resourceName) {
                case "Population":
                    return new Population(resourceValue);
                case "Food":
                    return new FoodStacked(resourceValue);
                case "Medicine":
                    return new MedicineStacked(resourceValue);
                case "Screw":
                    return new ScrewStacked(resourceValue);
                case "Weapons":
                    return new WeaponsStacked(resourceValue);
                default:
                    throw new IllegalArgumentException("Invalid resource name");
            }
        }).collect(Collectors.toList());

    }

    /**
     * @param settlersM
     * @param settlersS
     * @return a map of settlers with sector assigned
     */
    public Map<String, String> translateSettlerToMap(List<MandatorySettler> settlersM, List<SimpleSettler> settlersS) {
        Map<String, String> settlerMap = new HashMap<>();
        settlersM.stream()
                .forEach(settler -> settlerMap.put(settler.getClass().getSimpleName(), settler.getSectorAssigned()));
        settlersS.stream()
                .forEach(settler -> settlerMap.put(settler.getClass().getSimpleName(), settler.getSectorAssigned()));
        return settlerMap;

    }

    public List<BaseSettler> translateSettlerToListFromMapMO(List<MandatorySettler> settlersM,
            List<SimpleSettler> settlersS) {
        List<BaseSettler> settlerNew = new ArrayList();
        settlersM.stream().forEach(settlerNew::add);
        settlersS.stream().forEach(settlerNew::add);
        return settlerNew;

    }

    public List<MandatorySettler> translateSettlerToMapMandatory(Map<String, String> settlerAndSector) {
        return settlerAndSector.entrySet().stream().map(entry -> {
            String settlerName = entry.getKey();
            String settlerSector = entry.getValue();
            switch (settlerName) {
                case "Doctor":
                    return new Doctor();
                case "Farmer":
                    return new Farmer();
                case "Gunsmith":
                    return new Gunsmith();
                case "Military":
                    return new Military();
                case "Blacksmith":
                    return new Blacksmith();
                default:
                    throw new IllegalArgumentException("Invalid settler name");
            }
        }).collect(Collectors.toList());

    }

    public List<SimpleSettler> translateSettlerToMapOptional(Map<String, String> settlerAndSector) {
        return settlerAndSector.entrySet().stream().map(entry -> {
            String settlerName = entry.getKey();
            String settlerSector = entry.getValue();
            switch (settlerName) {
                case "Cook":
                    return new Cook();
                case "Technician":
                    return new Technician();
                case "Chemist":
                    return new Chemist();
                default:
                    throw new IllegalArgumentException("Invalid settler name");
            }
        }).collect(Collectors.toList());

    }

    public List<MandatorySettler> translateMandatorySettler(List<String> settlers) {
        return settlers.stream().map(settler -> switch (settler) {
            case "Doctor" -> new Doctor();
            case "Farmer" -> new Farmer();
            case "Gunsmith" -> new Gunsmith();
            case "Military" -> new Military();
            case "Blacksmith" -> new Blacksmith();
            default -> throw new IllegalArgumentException("Invalid settler name");
        }).collect(Collectors.toList());
    }

    public List<SimpleSettler> translateSimpleSettler(List<String> settlers) {
        return settlers.stream().map(settler -> switch (settler) {
            case "Cook" -> new Cook();
            case "Technician" -> new Technician();
            case "Chemist" -> new Chemist();
            default -> throw new IllegalArgumentException("Invalid settler name");
        }).collect(Collectors.toList());
    }

    /**
     * @param difficulty
     * @return
     */
    public String translateDifficultyToString(DifficultiesType difficulty) {
        return switch (difficulty) {
            case EASY -> "EASY";
            case MEDIUM -> "MEDIUM";
            case HARD -> "HARD";
        };
    }

    /**
     * @param settlers
     * @return a list of settlers
     */
    public List<String> translateSettlerToString(List<BaseSettler> settlers) {
        return settlers.stream().map(settler -> switch (settler.getClass().getSimpleName()) {
            case "Doctor" -> "Doctor";
            case "Farmer" -> "Farmer";
            case "Technician" -> "Technician";
            case "Military" -> "Military";
            case "Cook" -> "Cook";
            case "Gunsmith" -> "Gunsmith";
            case "Chemist" -> "Chemist";
            case "Blacksmith" -> "Blacksmith";
            default -> throw new IllegalArgumentException("Invalid settler name");
        }).collect(Collectors.toList());
    }

    public List<String> translateSettlerToListFromMap(Map<String, Integer> settlers) {

        return settlers.entrySet().stream().map(entry -> {
            String settlerName = entry.getKey();
            int settlerValue = entry.getValue();
            switch (settlerName) {
                case "Doctor":
                    return "Doctor";
                case "Farmer":
                    return "Farmer";
                case "Technician":
                    return "Technician";
                case "Military":
                    return "Military";
                case "Cook":
                    return "Cook";
                case "Gunsmith":
                    return "Gunsmith";
                case "Chemist":
                    return "Chemist";
                case "Blacksmith":
                    return "Blacksmith";
                default:
                    throw new IllegalArgumentException("Invalid settler name");
            }
        }).collect(Collectors.toList());

    }

    /**
     * @param resources
     * @return a list of stacked resources
     */
    public Map<String, Integer> translateResourceToMap(List<StackedResource> resources) {

        return resources.stream().map(resource -> {
            String resourceName = resource.getClass().getSimpleName();
            int resourceValue = resource.getQta();
            switch (resourceName) {
                case "Population":
                    return Map.entry("Population", resourceValue);
                case "FoodStacked":
                    return Map.entry("Food", resourceValue);
                case "MedicineStacked":
                    return Map.entry("Medicine", resourceValue);
                case "ScrewStacked":
                    return Map.entry("Screw", resourceValue);
                case "WeaponsStacked":
                    return Map.entry("Weapons", resourceValue);
                default:
                    throw new IllegalArgumentException("Invalid resource name");
            }
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    /**
     * @param difficulty
     * @return
     */
    public DifficultiesType translateDifficulty(String difficulty) {
        return switch (difficulty) {
            case "EASY" -> DifficultiesType.EASY;
            case "MEDIUM" -> DifficultiesType.MEDIUM;
            case "HARD" -> DifficultiesType.HARD;
            default -> throw new IllegalArgumentException("Invalid difficulty name");
        };
    }

    public String fromResourceToSector(List<StackedResource> stackedResources) {
        return stackedResources.stream().map(resource -> switch (resource.getClass().getSimpleName()) {
            case "MedicineStacked" -> "Hospital";
            case "FoodStacked" -> "Farm";
            case "WeaponsStacked" -> "Military base";
            case "ScrewStacked" -> "Workshop";
            default -> throw new IllegalArgumentException("Invalid resource name");
        }).toString();
    }

    public List<String> translateListToOptionalSettlerList(List<String> settler) {
        List<String> settlerOpt = new ArrayList();
        settler.stream().map(settlerName -> switch (settlerName) {
            case "Cook" -> settlerOpt.add("Cook");
            case "Technician" -> settlerOpt.add("Technician");
            case "Chemist" -> settlerOpt.add("Chemist");
            default -> "do nothing";
        }).collect(Collectors.toList());
        return settlerOpt;
    }

    /**
     * Support function to create a resource
     * @param resourceName
     * @param quantity
     * @return
     */
    public StackedResource createResourceFromNameAndQta(String resourceName, int quantity) {
        switch (resourceName) {
            case "ScrewStacked":
                return new ScrewStacked(quantity);
            case "WeaponsStacked":
                return new WeaponsStacked(quantity);
            case "MedicineStacked":
                return new MedicineStacked(quantity);
            case "FoodStacked":
                return new FoodStacked(quantity);
            case "Population":
                return new Population(quantity);
            default:
                return null;
        }

    }
}