package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class MealCategoriesResponse {
    private int version;
    private List<MealCategory> mealCategories;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<MealCategory> getMealCategories() {
        return mealCategories;
    }

    public void setMealCategories(List<MealCategory> mealCategories) {
        this.mealCategories = mealCategories;
    }
}
