package it.unibo.cosmocity.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.Diff;

import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.Difficulty;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.MedicineStacked;
import it.unibo.cosmocity.model.resources.ScrewStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.resources.WeaponsStacked;
import it.unibo.cosmocity.model.settlers.BaseSettler;
import it.unibo.cosmocity.model.settlers.Blacksmith;
import it.unibo.cosmocity.model.settlers.Cook;
import it.unibo.cosmocity.model.settlers.Doctor;
import it.unibo.cosmocity.model.settlers.Farmer;
import it.unibo.cosmocity.model.settlers.Gunsmith;
import it.unibo.cosmocity.model.settlers.Military;
import it.unibo.cosmocity.model.settlers.Pharmacist;
import it.unibo.cosmocity.model.settlers.Technician;

public class TranslatorStringToClassHelper {
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
            case "Pharmacist" -> new Pharmacist();
            case "Blacksmith" -> new Blacksmith();
            default -> throw new IllegalArgumentException("Invalid settler name");
        }).collect(Collectors.toList());
    }

    /**
     * @param resources
     * @return a list of stacked resources
     */
    public List<StackedResource> translateResource(Map<String, Integer> resources) {
        return resources.entrySet().stream().map(entry -> {
            String resourceName = entry.getKey();
            int resourceValue = entry.getValue();
            switch (resourceName) {
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
            case "Pharmacist" -> "Pharmacist";
            case "Blacksmith" -> "Blacksmith";
            default -> throw new IllegalArgumentException("Invalid settler name");
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

    public String fromResourceToSector(List<StackedResource> stackedResources){
        return stackedResources.stream().map(resource -> switch(resource.getClass().getSimpleName()){
            case "MedicineStacked" -> "Hospital";
            case "FoodStacked" -> "Farm";
            case "WeaponsStacked" -> "Military base";
            case "ScrewStacked" -> "Workshop";
            default -> throw new IllegalArgumentException("Invalid resource name");
        }).toString();
    }
}