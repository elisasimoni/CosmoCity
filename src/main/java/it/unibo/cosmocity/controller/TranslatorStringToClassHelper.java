package it.unibo.cosmocity.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.MedicineStacked;
import it.unibo.cosmocity.model.resources.Population;
import it.unibo.cosmocity.model.resources.ScrewStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
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

/**
 * The TranslatorStringToClassHelper class is a helper class used to translate a
 * string format to a
 * class format and vice versa.
 */
public class TranslatorStringToClassHelper {

    private static final String FARM = "Farm";
    private static final String HOSPITAL = "Hospital";
    private static final String MILITARY_BASE = "Military base";
    private static final String WORKSHOP = "Workshop";
    private static final String DOCTOR = "Doctor";
    private static final String FARMER = "Farmer";
    private static final String TECHNICIAN = "Technician";
    private static final String MILITARY = "Military";
    private static final String COOK = "Cook";
    private static final String GUNSMITH = "Gunsmith";
    private static final String CHEMIST = "Chemist";
    private static final String BLACKSMITH = "Blacksmith";
    private static final String POPULATION = "Population";
    private static final String FOOD_STACKED = "FoodStacked";
    private static final String MEDICINE_STACKED = "MedicineStacked";
    private static final String SCREW_STACKED = "ScrewStacked";
    private static final String WEAPONS_STACKED = "WeaponsStacked";

    /**
     * The function translates a list of settler names into a list of corresponding
     * settler objects.
     * 
     * @param settlers A list of strings representing the names of settlers.
     * @return The method is returning a List of BaseSettler objects.
     */
    public List<BaseSettler> translateSettler(final List<String> settlers) {
        return settlers.stream().map(settler -> switch (settler) {
            case DOCTOR -> new Doctor();
            case FARMER -> new Farmer();
            case TECHNICIAN -> new Technician();
            case MILITARY -> new Military();
            case COOK -> new Cook();
            case GUNSMITH -> new Gunsmith();
            case CHEMIST -> new Chemist();
            case BLACKSMITH -> new Blacksmith();
            default -> throw new IllegalArgumentException("Invalid settler name");
        }).collect(Collectors.toList());
    }

    /**
     * The function takes a map of resource names and their corresponding values,
     * and returns a list of
     * StackedResource objects based on the resource names and values.
     * 
     * @param resources The `resources` parameter is a `Map` that maps `String` keys
     *                  to `Integer`
     *                  values. Each key represents the name of a resource, and each
     *                  value represents the quantity of
     *                  that resource.
     * @return The method `translateResources` returns a `List` of `StackedResource`
     *         objects.
     */
    public List<StackedResource> translateResources(final Map<String, Integer> resources) {
        return resources.entrySet().stream().map(entry -> {
            final String resourceName = entry.getKey();
            final int resourceValue = entry.getValue();
            switch (resourceName) {
                case POPULATION:
                    return new Population(resourceValue);
                case FOOD_STACKED:
                    return new FoodStacked(resourceValue);
                case MEDICINE_STACKED:
                    return new MedicineStacked(resourceValue);
                case SCREW_STACKED:
                    return new ScrewStacked(resourceValue);
                case WEAPONS_STACKED:
                    return new WeaponsStacked(resourceValue);
                default:
                    throw new IllegalArgumentException("Invalid resource name");
            }
        }).collect(Collectors.toList()).stream()
                .sorted(Comparator.comparing(Object::toString)) // Ordina in base al nome
                .collect(Collectors.toList());
        // alfabetical order

    }

    /**
     * The function takes in two lists of settlers, one of type MandatorySettler and
     * one of type
     * SimpleSettler, and returns a map where the keys are the class names of the
     * settlers and the
     * values are the sectors assigned to them.
     * 
     * @param settlersM A list of MandatorySettler objects.
     * @param settlersS The parameter "settlersS" is a List of objects of type
     *                  SimpleSettler.
     * @return The method is returning a Map<String, String> object.
     */
    public Map<String, String> translateSettlerToMap(final List<MandatorySettler> settlersM,
            final List<SimpleSettler> settlersS) {
        final Map<String, String> settlerMap = new HashMap<>();
        settlersM.stream()
                .forEach(settler -> settlerMap.put(settler.getClass().getSimpleName(), settler.getSectorAssigned()));
        settlersS.stream()
                .forEach(settler -> settlerMap.put(settler.getClass().getSimpleName(), settler.getSectorAssigned()));
        return settlerMap;

    }

    /**
     * The function takes two lists of settlers (one of type MandatorySettler and
     * one of type
     * SimpleSettler), combines them into a single list of type BaseSettler, and
     * returns the combined
     * list.
     * 
     * @param settlersM A list of objects of type MandatorySettler.
     * @param settlersS A list of objects of type SimpleSettler.
     * @return The method is returning a List of BaseSettler objects.
     */
    public List<BaseSettler> translateSettlerToListFromMapMO(final List<MandatorySettler> settlersM,
            final List<SimpleSettler> settlersS) {
        final List<BaseSettler> settlerNew = new ArrayList<>();
        settlersM.stream().forEach(settlerNew::add);
        settlersS.stream().forEach(settlerNew::add);
        return settlerNew;

    }

    /**
     * The function translates a map of settler names to their corresponding sectors
     * into a list of
     * MandatorySettler objects.
     * 
     * @param settlerAndSector A map that contains the settler name as the key and
     *                         the sector name as
     *                         the value.
     * @return The method is returning a List of MandatorySettler objects.
     */
    public List<MandatorySettler> translateSettlerToMapMandatory(final Map<String, String> settlerAndSector) {
        return settlerAndSector.entrySet().stream().map(entry -> {
            final String settlerName = entry.getKey();
            switch (settlerName) {
                case DOCTOR:
                    return new Doctor();
                case FARMER:
                    return new Farmer();
                case GUNSMITH:
                    return new Gunsmith();
                case MILITARY:
                    return new Military();
                case BLACKSMITH:
                    return new Blacksmith();
                default:
                    throw new IllegalArgumentException("Invalid settler name");
            }
        }).collect(Collectors.toList());

    }

    /**
     * The function translates a map of settler names and sectors into a list of
     * corresponding settler
     * objects.
     * 
     * @param settlerAndSector A map that contains settler names as keys and sector
     *                         names as values.
     * @return The method is returning a List of SimpleSettler objects.
     */
    public List<SimpleSettler> translateSettlerToMapOptional(final Map<String, String> settlerAndSector) {
        return settlerAndSector.entrySet().stream().map(entry -> {
            final String settlerName = entry.getKey();
            switch (settlerName) {
                case COOK:
                    return new Cook();
                case TECHNICIAN:
                    return new Technician();
                case CHEMIST:
                    return new Chemist();
                default:
                    throw new IllegalArgumentException("Invalid settler name");
            }
        }).collect(Collectors.toList());

    }

    /**
     * The function translates a list of settler names into a list of corresponding
     * settler objects.
     * 
     * @param settlers A list of strings representing the names of settlers.
     * @return The method is returning a list of MandatorySettler objects.
     */
    public List<MandatorySettler> translateMandatorySettler(final List<String> settlers) {
        return settlers.stream().map(settler -> switch (settler) {
            case DOCTOR -> new Doctor();
            case FARMER -> new Farmer();
            case GUNSMITH -> new Gunsmith();
            case MILITARY -> new Military();
            case BLACKSMITH -> new Blacksmith();
            default -> throw new IllegalArgumentException("Invalid settler name");
        }).collect(Collectors.toList());
    }

    /**
     * The function translates a list of settler names into a list of corresponding
     * settler objects.
     * 
     * @param settlers A list of strings representing the names of settlers.
     * @return The method is returning a List of SimpleSettler objects.
     */
    public List<SimpleSettler> translateSimpleSettler(final List<String> settlers) {
        return settlers.stream().map(settler -> switch (settler) {
            case COOK -> new Cook();
            case TECHNICIAN -> new Technician();
            case CHEMIST -> new Chemist();
            default -> throw new IllegalArgumentException("Invalid settler name");
        }).collect(Collectors.toList());
    }

    /**
     * The function translates a DifficultyType enum value into a corresponding
     * string representation.
     * 
     * @param difficulty The parameter "difficulty" is of type DifficultiesType.
     * @return The method is returning a string representation of the difficulty
     *         level.
     */
    public String translateDifficultyToString(final DifficultiesType difficulty) {
        return switch (difficulty) {
            case EASY -> "EASY";
            case MEDIUM -> "MEDIUM";
            case HARD -> "HARD";
        };
    }

    /**
     * The function takes a list of BaseSettler objects and returns a list of their
     * corresponding
     * string names.
     * 
     * @param settlers A list of objects of type BaseSettler.
     * @return The method is returning a List of Strings.
     */
    public List<String> translateSettlerToString(final List<BaseSettler> settlers) {
        return settlers.stream().map(settler -> switch (settler.getClass().getSimpleName()) {
            case DOCTOR -> DOCTOR;
            case FARMER -> FARMER;
            case TECHNICIAN -> TECHNICIAN;
            case MILITARY -> MILITARY;
            case COOK -> COOK;
            case GUNSMITH -> GUNSMITH;
            case CHEMIST -> CHEMIST;
            case BLACKSMITH -> BLACKSMITH;
            default -> throw new IllegalArgumentException("Invalid settler name");
        }).collect(Collectors.toList());
    }

    /**
     * The function takes a map of settlers and their corresponding counts, and
     * returns a list of the
     * settlers' names.
     * 
     * @param settlers The parameter "settlers" is a Map<String, Integer> where the
     *                 keys are the names
     *                 of settlers and the values are their corresponding values.
     * @return The method is returning a List of Strings.
     */
    public List<String> translateSettlerToListFromMap(final Map<String, Integer> settlers) {
        return settlers.entrySet().stream().map(entry -> {
            final String settlerName = entry.getKey();
            switch (settlerName) {
                case DOCTOR:
                    return DOCTOR;
                case FARMER:
                    return FARMER;
                case TECHNICIAN:
                    return TECHNICIAN;
                case MILITARY:
                    return MILITARY;
                case COOK:
                    return COOK;
                case GUNSMITH:
                    return GUNSMITH;
                case CHEMIST:
                    return CHEMIST;
                case BLACKSMITH:
                    return BLACKSMITH;
                default:
                    throw new IllegalArgumentException("Invalid settler name");
            }
        }).collect(Collectors.toList());
    }

    /**
     * The function translates a list of StackedResource objects into a map of
     * resource names and their
     * corresponding quantities.
     * 
     * @param resources A list of StackedResource objects.
     * @return The method is returning a `Map<String, Integer>`.
     */
    public Map<String, Integer> translateResourceToMap(final List<StackedResource> resources) {

        return resources.stream().map(resource -> {
            final String resourceName = resource.getClass().getSimpleName();
            final int resourceValue = resource.getQta();
            switch (resourceName) {
                case POPULATION:
                    return Map.entry(POPULATION, resourceValue);
                case FOOD_STACKED:
                    return Map.entry(FOOD_STACKED, resourceValue);
                case MEDICINE_STACKED:
                    return Map.entry(MEDICINE_STACKED, resourceValue);
                case SCREW_STACKED:
                    return Map.entry(SCREW_STACKED, resourceValue);
                case WEAPONS_STACKED:
                    return Map.entry(WEAPONS_STACKED, resourceValue);
                default:
                    throw new IllegalArgumentException("Invalid resource name");
            }
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    /**
     * The function translates a difficulty name into a corresponding
     * DifficultiesType enum value.
     * 
     * @param difficulty The parameter "difficulty" is a String that represents the
     *                   difficulty level of
     *                   a task or a game.
     * @return The method is returning a DifficultiesType enum value.
     */
    public DifficultiesType translateDifficulty(final String difficulty) {
        return switch (difficulty) {
            case "EASY" -> DifficultiesType.EASY;
            case "MEDIUM" -> DifficultiesType.MEDIUM;
            case "HARD" -> DifficultiesType.HARD;
            default -> throw new IllegalArgumentException("Invalid difficulty name");
        };
    }

    /**
     * The function takes a list of stacked resources and returns the corresponding
     * sector based on the
     * type of resource.
     * 
     * @param stackedResources A list of StackedResource objects.
     * @return The method is returning a string representation of the sectors
     *         corresponding to the
     *         given list of stacked resources.
     */
    public String fromResourceToSector(final List<StackedResource> stackedResources) {
        return stackedResources.stream().map(resource -> switch (resource.getClass().getSimpleName()) {
            case MEDICINE_STACKED -> HOSPITAL;
            case FOOD_STACKED -> FARM;
            case WEAPONS_STACKED -> MILITARY_BASE;
            case SCREW_STACKED -> WORKSHOP;
            default -> throw new IllegalArgumentException("Invalid resource name");
        }).toString();
    }

    /**
     * The function translates a list of settler names into a list of optional
     * settler names.
     * 
     * @param settler The parameter "settler" is a List of Strings that represents a
     *                list of settlers.
     * @return The method is returning a List of Strings.
     */
    public List<String> translateListToOptionalSettlerList(final List<String> settler) {
        final List<String> settlerOpt = new ArrayList();
        settler.stream().map(settlerName -> switch (settlerName) {
            case COOK -> settlerOpt.add(COOK);
            case TECHNICIAN -> settlerOpt.add(TECHNICIAN);
            case CHEMIST -> settlerOpt.add(CHEMIST);
            default -> "do nothing";
        }).collect(Collectors.toList());
        return settlerOpt;
    }

    /**
     * Support function to create a resource
     * 
     * @param resourceName
     * @param quantity
     * @return
     */
    public StackedResource createResourceFromNameAndQta(final String resourceName, final int quantity) {
        switch (resourceName) {
            case SCREW_STACKED:
                return new ScrewStacked(quantity);
            case WEAPONS_STACKED:
                return new WeaponsStacked(quantity);
            case MEDICINE_STACKED:
                return new MedicineStacked(quantity);
            case FOOD_STACKED:
                return new FoodStacked(quantity);
            case POPULATION:
                return new Population(quantity);
            default:
                return null;
        }

    }

    /**
     * The function filters a list of settlers to only include instances of
     * SimpleSettler and returns a
     * new list containing those instances.
     * 
     * @param settlers The "settlers" parameter is a List of BaseSettler objects.
     * @return The method is returning a list of SimpleSettler objects.
     */
    public List<SimpleSettler> getOptionalSettlerFromAMixedList(final List<BaseSettler> settlers) {
        return settlers.stream()
                .filter(settler -> settler instanceof SimpleSettler)
                .map(settler -> (SimpleSettler) settler)
                .collect(Collectors.toList());
    }

    /**
     * The function filters a list of settlers to only include instances of the
     * MandatorySettler class
     * and returns a new list containing only those instances.
     * 
     * @param settlers The "settlers" parameter is a List of objects of type
     *                 BaseSettler.
     * @return The method is returning a list of MandatorySettler objects.
     */
    public List<MandatorySettler> getMandatorySettlerFromAMixedList(final List<BaseSettler> settlers) {
        return settlers.stream()
                .filter(settler -> settler instanceof MandatorySettler)
                .map(settler -> (MandatorySettler) settler)
                .collect(Collectors.toList());
    }

    /**
   * @param resourceName
   * @param quantity
   * @return the resource created from the name and quantity
   */
  public StackedResource createResource(final String resourceName, final int quantity) {
    switch (resourceName) {
      case POPULATION:
        return new Population(quantity);
      case SCREW_STACKED:
        return new ScrewStacked(quantity);
      case WEAPONS_STACKED:
        return new WeaponsStacked(quantity);
      case MEDICINE_STACKED:
        return new MedicineStacked(quantity);
      case FOOD_STACKED:
        return new FoodStacked(quantity);
      default:
        return null;
    }
  }

  /**
   * @param settlerName
   * @param sector
   * @return the settler created from the name and sector
   */
  public BaseSettler createSettler(final String settlerName, final String sector) {
    switch (settlerName) {
      case CHEMIST:
        final Chemist chemist = new Chemist();
        chemist.setSectorAssigned(sector);
        return chemist;
      case DOCTOR:
        return new Doctor();
      case FARMER:
        return new Farmer();
      case MILITARY:
        return new Military();
      case GUNSMITH:
        return new Gunsmith();
      case TECHNICIAN:
        final Technician technician = new Technician();
        technician.setSectorAssigned(sector);
        return technician;
      case BLACKSMITH:
        return new Blacksmith();
      case COOK:
        final Cook cook = new Cook();
        cook.setSectorAssigned(sector);
        return cook;
      default:
        return null;
    }
  }

}