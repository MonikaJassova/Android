package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import java.util.Arrays;
import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.CategoryMeal;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.ServingSize;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.Mealm;

/**
 * Created by monika.jassova on 12/1/2017.
 */

public class MealCategoryDataFactory {
    public static List<MealCategory> makeMealCategories() {
        return Arrays.asList(makePolievka(), makePizza());
    }

    public static MealCategory makePizza() {
        return new MealCategory("Pizze", makePizze());
    }

    private static List<Mealm> makePizze() {
        Mealm p1 = new Mealm("3", "margherita", new ServingSize("450g", 4.20), new CategoryMeal());
        Mealm p2 = new Mealm("4", "el bacone", new ServingSize("550g", 6.50), new CategoryMeal());
        Mealm p3 = new Mealm("5", "studentska", new ServingSize("500g", 5.10), new CategoryMeal());

        return Arrays.asList(p1, p2, p3);
    }

    public static MealCategory makePolievka() {
        return new MealCategory("Polievky", makePolievky());
    }

    public static List<Mealm> makePolievky() {
        Mealm queen = new Mealm("1", "cesnacka", new ServingSize("150g", 2.20), new CategoryMeal());
        Mealm styx = new Mealm("2", "brokolicova", new ServingSize("150g", 2.30), new CategoryMeal());

        return Arrays.asList(queen, styx);
    }



}
