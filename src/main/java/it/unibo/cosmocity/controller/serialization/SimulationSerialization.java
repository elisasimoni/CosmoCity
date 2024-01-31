package it.unibo.cosmocity.controller.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class SimulationSerialization implements Serialization {

  private final ObjectMapper mapper = new ObjectMapper();
  /*
   * Serialization of a save
   * simulation 
   * 
   */
  @Override
  public void serialize(final Object object) {
    final Simulation simulation = (Simulation) object;
    final List<Map<String, Object>> settlersToJson = new ArrayList<>();
    final List<Map<String, Object>> resourcesToJson = new ArrayList<>();

    for (final BaseSettler settler : simulation.getSettlers()) {
      final Map<String, Object> settlerJson = new HashMap<>();
      settlerJson.put("settlerName", settler.getClass().getSimpleName());
      settlerJson.put("sector", settler.getSectorAssigned());
      settlersToJson.add(settlerJson);
    }

    for (final BaseResource resource : simulation.getResources()) {
      final Map<String, Object> resourceJson = new HashMap<>();
      resourceJson.put("resourceName", resource.getClass().getSimpleName());
      resourceJson.put("quantity", resource.getQta());
      resourcesToJson.add(resourceJson);
    }

    final Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put("colonyName", simulation.getColonyName());
    jsonMap.put("settlers", settlersToJson);
    jsonMap.put("resources", resourcesToJson);
    jsonMap.put("difficulty", simulation.getDifficulty());

    try (
      Writer writer = Files.newBufferedWriter(
        Path.of("/it/unibo/asset/saves/Colony.json")
      )
    ) {
      writer.write(mapper.writeValueAsString(jsonMap));
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }


  /*
   * Deserialize a save file and return a simulation
   */
  @Override
  public Simulation deserialize() {
    try {
      final InputStream inputStream =
        SimulationSerialization.class.getResourceAsStream(
            "/it/unibo/asset/saves/Colony.json"
          );
      if (inputStream != null) {
        final String jsonContent = new String(inputStream.readAllBytes());

        final Map<String, Object> jsonMap = mapper.readValue(
          jsonContent,
          new TypeReference<>() {}
        );

        final String colonyName = (String) jsonMap.get("colonyName");
        final List<Map<String, Object>> settlersJsonList = mapper.readValue(
          mapper.writeValueAsString(jsonMap.get("settlers")),
          new TypeReference<>() {}
        );

        final List<Map<String, Object>> resourcesJsonList = mapper.readValue(
          mapper.writeValueAsString(jsonMap.get("resources")),
          new TypeReference<List<Map<String, Object>>>() {}
        );
        final String difficultyString = (String) jsonMap.get("difficulty");
        final DifficultiesType difficulty = DifficultiesType.valueOf(
          difficultyString
        );

        final List<BaseSettler> settlers = new ArrayList<>();
        for (final Map<String, Object> settlerJson : settlersJsonList) {
          final String settlerName = (String) settlerJson.get("settlerName");
          final String sector = (String) settlerJson.get("sector");
          final BaseSettler settler = createSettler(settlerName, sector);
          settlers.add(settler);
        }

        final List<StackedResource> resources = new ArrayList<>();
        for (final Map<String, Object> resourceJson : resourcesJsonList) {
          final String resourceName = (String) resourceJson.get("resourceName");
          final int quantity = (int) resourceJson.get("quantity");
          final StackedResource resource = createResource(resourceName, quantity);

          resources.add(resource);
        }

        return new Simulation(
          colonyName,
          settlers,
          resources,
          difficulty
        );
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * @param resourceName
   * @param quantity
   * @return the resource created from the name and quantity
   */
  private StackedResource createResource(final String resourceName, final int quantity) {
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

  /**
   * @param settlerName
   * @param sector
   * @return the settler created from the name and sector
   */
  private BaseSettler createSettler(final String settlerName, final String sector) {
    switch (settlerName) {
      case "Chemist":
        final Chemist chemist = new Chemist();
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
        final Technician technician = new Technician();
        technician.setSectorAssigned(sector);
        return technician;
      case "Blacksmith":
        return new Blacksmith();
      case "Cook":
        final Cook cook = new Cook();
        cook.setSectorAssigned(sector);
        return cook;
      default:
        return null;
    }
  }
}
