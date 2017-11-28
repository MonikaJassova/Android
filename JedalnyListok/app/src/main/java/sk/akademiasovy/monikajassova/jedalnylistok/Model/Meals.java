package sk.akademiasovy.monikajassova.jedalnylistok.Model;

import java.util.List;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class Meals {
    private Integer version;
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(final List<Meal> meals) {
        this.meals = meals;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }
}
