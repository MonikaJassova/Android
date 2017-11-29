package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.Meal;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.Packaging;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class MealCategory {
    private String id;
    private String name;
    private List<Meal> meals;
    private Packaging packaging;
    private String description;
    private Integer displaySeq;

    public MealCategory(){

    }

    public MealCategory(String id, String name, List<Meal> meals, Packaging packaging, String description, Integer displaySeq) {
        this.id = id;
        this.name = name;
        this.meals = meals;
        this.packaging = packaging;
        this.description = description;
        this.displaySeq = displaySeq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(Integer displaySeq) {
        this.displaySeq = displaySeq;
    }
}
