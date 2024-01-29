package it.unibo.cosmocity.controller.view_controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.Diff;

import it.unibo.cosmocity.controller.TranslatorStringToClassHelper;
import it.unibo.cosmocity.model.DifficultiesType;
import it.unibo.cosmocity.model.resources.BaseResource;
import it.unibo.cosmocity.model.resources.FoodStacked;
import it.unibo.cosmocity.model.resources.MedicineStacked;
import it.unibo.cosmocity.model.resources.Population;
import it.unibo.cosmocity.model.resources.ScrewStacked;
import it.unibo.cosmocity.model.resources.StackedResource;
import it.unibo.cosmocity.model.resources.WeaponsStacked;
import it.unibo.cosmocity.model.settlers.BaseSettler;

public class CreateColonyController {

    private String colonyName;
    private List<BaseSettler> settlers;
    private List<StackedResource> resources;
    private DifficultiesType difficulty;
    private TranslatorStringToClassHelper translator = new TranslatorStringToClassHelper();

    public String saveColonyName(String colonyName) {
        this.colonyName = colonyName;
        return this.colonyName;
    }

    public List<BaseSettler> saveSettlers(List<String> settlers) {
       this.settlers = translator.translateSettler(settlers);
         return this.settlers;
    }

    public List<StackedResource> saveResources(Map<String, Integer> resources) {
        this.resources = translator.translateResources(resources);
        return this.resources;
    }

    public List<StackedResource> saveDifficulty(String difficulty) {

        switch (difficulty) {
            case "EASY":
                this.resources = List.of(new Population(DifficultiesType.EASY.getDifficulty()), new FoodStacked(DifficultiesType.EASY.getDifficulty()),
                        new ScrewStacked(DifficultiesType.EASY.getDifficulty()),
                        new WeaponsStacked(DifficultiesType.EASY.getDifficulty()), new MedicineStacked(DifficultiesType.EASY.getDifficulty()));
                break;
            case "MEDIUM":
                this.resources = List.of(new Population(DifficultiesType.MEDIUM.getDifficulty()), new FoodStacked(DifficultiesType.MEDIUM.getDifficulty()),
                        new ScrewStacked(DifficultiesType.MEDIUM.getDifficulty()),
                        new WeaponsStacked(DifficultiesType.MEDIUM.getDifficulty()), new MedicineStacked(DifficultiesType.MEDIUM.getDifficulty()));
                break;
            case "HARD":
                this.resources = List.of(new Population(DifficultiesType.HARD.getDifficulty()), new FoodStacked(DifficultiesType.HARD.getDifficulty()),
                        new ScrewStacked(DifficultiesType.HARD.getDifficulty()),
                        new WeaponsStacked(DifficultiesType.HARD.getDifficulty()), new MedicineStacked(DifficultiesType.HARD.getDifficulty()));
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty");
        }
        
        return this.resources;
    }

}
