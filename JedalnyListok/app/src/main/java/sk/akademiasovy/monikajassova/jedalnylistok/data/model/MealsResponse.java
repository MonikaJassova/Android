package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class MealsResponse {
    private int version;
    private List<Meal> meals;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
