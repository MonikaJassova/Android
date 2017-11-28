package sk.akademiasovy.monikajassova.jedalnylistok.Model;

import java.util.List;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class MealCategory {
    private String id;
    private String name;
    private List<Meal> meals;
    private Packaging packaging;
    private String description;
    private Integer displaySeq;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(final List<Meal> meals) {
        this.meals = meals;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(final Packaging packaging) {
        this.packaging = packaging;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(final Integer displaySeq) {
        this.displaySeq = displaySeq;
    }
}
