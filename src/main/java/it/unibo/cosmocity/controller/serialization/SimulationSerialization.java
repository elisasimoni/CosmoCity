package it.unibo.cosmocity.controller.serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.Simulation;
import it.unibo.cosmocity.model.resources.BaseResource;
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
import it.unibo.cosmocity.model.settlers.Military;
import it.unibo.cosmocity.model.settlers.Technician;
import java.nio.file.Path;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.*;

public class SimulationSerialization implements Serialization {

  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public void serialize(Object object) {
    try {
      Simulation simulation = (Simulation) object;
      List<Map<String, Object>> settlersToJson = new ArrayList<>();
      List<Map<String, Object>> resourcesToJson = new ArrayList<>();

      for (BaseSettler settler : simulation.getSettlers()) {
        Map<String, Object> settlerJson = new HashMap<>();
        settlerJson.put("settlerName", settler.getClass().getSimpleName());
        settlerJson.put("sector", settler.getSectorAssigned());
        settlersToJson.add(settlerJson);
      }

      for (BaseResource resource : simulation.getResources()) {
        Map<String, Object> resourceJson = new HashMap<>();
        resourceJson.put("resourceName", resource.getClass().getSimpleName());
        resourceJson.put("quantity", resource.getQta());
        resourcesToJson.add(resourceJson);
      }

      Map<String, Object> jsonMap = new HashMap<>();
      jsonMap.put("colonyName", simulation.getColonyName());
      jsonMap.put("settlers", settlersToJson);
      jsonMap.put("resources", resourcesToJson);
      jsonMap.put("difficulty", simulation.getDifficulty());
      jsonMap.put("startTime", simulation.getStartTime());

      System.out.println(mapper.writeValueAsString(jsonMap));
      try (Writer writer = Files.newBufferedWriter(Path.of("/it/unibo/asset/saves/Colony.json"))) {
        writer.write(mapper.writeValueAsString(jsonMap));
      } catch (IOException e) {
        e.printStackTrace();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Simulation deserialize() {
    try {
      InputStream inputStream = SimulationSerialization.class
          .getResourceAsStream("/it/unibo/asset/saves/Colony.json");
      System.out.println(inputStream);
      if (inputStream != null) {

        String jsonContent = new String(inputStream.readAllBytes());
        System.out.println(jsonContent);
        Map<String, Object> jsonMap = mapper.readValue(
            jsonContent,
            new TypeReference<>() {
            });

        String colonyName = (String) jsonMap.get("colonyName");
        List<Map<String, Object>> settlersJsonList = (List<Map<String, Object>>) jsonMap.get(
            "settlers");
        List<Map<String, Object>> resourcesJsonList = (List<Map<String, Object>>) jsonMap.get(
            "resources");
        String difficultyString = (String) jsonMap.get("difficulty");
        DifficultiesType difficulty = DifficultiesType.valueOf(
            difficultyString);
        Number startTimeNumber = (Number) jsonMap.get("startTime");
        long startTime = startTimeNumber.longValue();

        List<BaseSettler> settlers = new ArrayList<>();
        for (Map<String, Object> settlerJson : settlersJsonList) {
          String settlerName = (String) settlerJson.get("settlerName");
          String sector = (String) settlerJson.get("sector");
          BaseSettler settler = createSettler(settlerName, sector);
          settlers.add(settler);
        }

        List<StackedResource> resources = new ArrayList<>();
        for (Map<String, Object> resourceJson : resourcesJsonList) {
          String resourceName = (String) resourceJson.get("resourceName");
          int quantity = (int) resourceJson.get("quantity");
          StackedResource resource = createResource(resourceName, quantity);

          resources.add(resource);
        }

        return new Simulation(
            colonyName,
            settlers,
            resources,
            difficulty,
            startTime);
      }
    } catch (IOException e) {
      System.out.println("Error while loading the game");
      e.printStackTrace();
    }

    return null;
  }

  private StackedResource createResource(String resourceName, int quantity) {
    switch (resourceName) {
      case "Population":
        return new Population(quantity);
      case "ScrewStacked":
        return new ScrewStacked(quantity);
      case "WeaponsStacked":
        return new WeaponsStacked(quantity);
      case "MedicineStacked":
        return new MedicineStacked(quantity);
      case "FoodStacked":
        return new FoodStacked(quantity);
      default:
        return null;
    }
  }

  private BaseSettler createSettler(String settlerName, String sector) {
    switch (settlerName) {
      case "Chemist":
        Chemist chemist = new Chemist();
        chemist.setSectorAssigned(sector);
        return chemist;
      case "Doctor":
        return new Doctor();
      case "Farmer":
        return new Farmer();
      case "Military":
        return new Military();
      case "Gunsmith":
        return new Gunsmith();
      case "Technician":
        Technician technician = new Technician();
        technician.setSectorAssigned(sector);
        return technician;
      case "Blacksmith":
        return new Blacksmith();
      case "Cook":
        Cook cook = new Cook();
        cook.setSectorAssigned(sector);
        return cook;
      default:
        return null;
    }
  }
}
