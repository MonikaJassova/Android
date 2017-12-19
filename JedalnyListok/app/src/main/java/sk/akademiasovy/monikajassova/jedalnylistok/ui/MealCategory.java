package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.Meal;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategoryMeal;

/**
 * Created by monika.jassova on 12/1/2017.
 */

public class MealCategory extends ExpandableGroup<MealCategoryMeal> {
    public MealCategory(sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory mc) {
        super(mc.getName(), mc.getMeals());
    }
}
