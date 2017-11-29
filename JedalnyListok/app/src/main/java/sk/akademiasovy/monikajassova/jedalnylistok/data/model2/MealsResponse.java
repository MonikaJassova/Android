package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class MealsResponse {
    private int version;
    private List<Mealm> meals;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Mealm> getMeals() {
        return meals;
    }

    public void setMeals(List<Mealm> meals) {
        this.meals = meals;
    }
}
